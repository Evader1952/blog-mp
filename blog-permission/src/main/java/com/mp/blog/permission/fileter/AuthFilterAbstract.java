package com.mp.blog.permission.fileter;

import com.mp.blog.permission.service.TokenStoreResolver;
import com.mp.blog.permission.service.UserPermissionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * @author tuchuan
 * @description
 * @date 2019/3/15 10:50
 */
@WebFilter(urlPatterns = "/*")
public class AuthFilterAbstract extends JwtAuthenticationFilter {

    @Autowired
    @Qualifier(value = "userPermissionResolver")
    private UserPermissionResolver userPermissionResolver;

    @Autowired
    @Qualifier(value = "webTokenResolver")
    private TokenStoreResolver tokenStoreResolver;

    @Override
    public UserPermissionResolver getUserPermissionResolver() {
        return userPermissionResolver;
    }

    @Override
    public TokenStoreResolver getTokenStoreResolver() {
        return tokenStoreResolver;
    }

    @Override
    protected UsernamePasswordToken createUsernamePasswordToken(HttpServletRequest request) {
        return super.createUsernamePasswordToken(request);
    }

    @Override
    protected Boolean isLoginUrl(HttpServletRequest request) {
        return pathMatcher.match("/web/user/login", request.getServletPath());
    }

    @Override
    protected Boolean isLogoutUrl(HttpServletRequest request) {
        return pathMatcher.match("/logout", request.getServletPath());
    }

    @Override
    protected boolean isProtectedUrl(HttpServletRequest request) {
        return pathMatcher.match("/web/**", request.getServletPath())
                && !pathMatcher.match("/web/user/login", request.getServletPath());
    }

}
