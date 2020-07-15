package com.mp.blog.permission.fileter;


import com.mp.blog.common.utils.RequestContext;
import com.mp.blog.permission.service.TokenStoreResolver;
import com.mp.blog.permission.service.UserPermissionResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public abstract class JwtAuthenticationFilter extends OncePerRequestFilter {

    protected static final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,x-requested-with,Authorization");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        System.err.println(request.getRequestURI());
        if (request.getMethod().equalsIgnoreCase("options")) {
            filterChain.doFilter(request, response);
        } else {
            LoginUser user = null;
            try {
                if (isProtectedUrl(request)) {
                    request = JwtUtil.validateTokenAndAddUserIdToHeader(request, getTokenStoreResolver());
                    user = getUserPermissionResolver().getLoginUser(JwtUtil.getCurrentUserId(request));
                }
                if (isLoginUrl(request)) {
                    user = getUserPermissionResolver().doAuthInfo(createUsernamePasswordToken(request));
                }
                if (user != null) {
                    RequestContext.RequestUser requestUser = new RequestContext.RequestUser();
                    requestUser.setUsername(user.getUsername());
                    requestUser.setId(user.getUserId());
                    requestUser.setPassword(user.getPassword());
                    requestUser.setSalt(user.getSalt());
                    requestUser.setRoleTypes(user.getRoleTypes());
                    requestUser.setType(user.getType());
                    RequestContext.setCurrentUser(requestUser);
                    if (!isLogoutUrl(request)) {
                        JwtUtil.refreshAndAddTokenToResponseHeader(request, response, user.getUserId(), user.getUsername(), getTokenStoreResolver());
                    }
                }
            } catch (UserPermissionResolver.AuthenticationException e) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=utf-8");
                if (e.getCode() != null) {
                    response.getWriter().write("{\"code\":" + e.getCode() + ",\"msg\":\"" + e.getMessage() + "\"}");
                } else {
                    response.getWriter().write("{\"code\":40015,\"msg\":\"" + e.getMessage() + "\"}");
                }
                return;
            } catch (UserPermissionResolver.AuthorizationException e) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write("{\"code\":40100,\"msg\":\"" + e.getMessage() + "\"}");
                return;
            } catch (Exception e) {
                log.error("Jwt鉴权出现异常：{}", e);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write("{\"code\":40015,\"msg\":\"登录信息已过期\"}");
                return;
            }
            filterChain.doFilter(request, response);
            try {
                if (isLogoutUrl(request)) {
                    JwtUtil.logoutToken(request, getTokenStoreResolver());
                }
            } catch (Exception e) {
                log.error("exception:{}", e);
            }
        }
    }


    public abstract UserPermissionResolver getUserPermissionResolver();

    public abstract TokenStoreResolver getTokenStoreResolver();

    protected UsernamePasswordToken createUsernamePasswordToken(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return new UsernamePasswordToken(username, password);
    }

    protected abstract Boolean isLoginUrl(HttpServletRequest request);

    protected abstract Boolean isLogoutUrl(HttpServletRequest request);

    protected abstract boolean isProtectedUrl(HttpServletRequest request);

}