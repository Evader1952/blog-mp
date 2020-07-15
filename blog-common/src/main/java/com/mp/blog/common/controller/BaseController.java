package com.mp.blog.common.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mp.blog.common.utils.DataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 初始
 * @author duchong
 * @date 2019-06-11 9:41
 **/
public abstract class BaseController {

    /**
     * 字符集类型
     * */
    protected static final String UTF_8 = "utf-8";

    /**
     * 字符集类型
     * */
    private static final String GBK = "GBK";

    private static final String AUTHORIZATION = "authorization";

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取request
     *
     * @return
     */
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    /**
     * 获取response
     *
     * @return
     */
    protected HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }


    /**
     * 获取response
     *
     * @param params
     */
    protected void writeUtf8Response(Map<String,Object> params) throws IOException {
        HttpServletResponse httpResponse = getResponse();
        httpResponse.setContentType("text/html");
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("text/html;charset=utf-8");
        httpResponse.getWriter().write(JSON.toJSONString(params));
    }

    /**
     * 获取session
     *
     * @return
     */
    protected HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }

    /**
     * 获取authorization
     *
     * @return
     * */
    protected String getAuthorization() throws IOException {
        String authorization = getRequest().getHeader(AUTHORIZATION);
        getAuthorization(authorization);
        return authorization;
    }

    /**
     * 获取authorization
     *
     * @return
     * */
    protected void getAuthorization(String authorization) throws IOException {
        if (authorization == null || "".equals(authorization) || "null".equals(authorization) || "undefined".equals(authorization)) {
            HttpServletResponse response = getResponse();
            response.setStatus(401);
            response.setHeader("WWW-authenticate", "Basic realm=\"请输入密码\"");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("对不起你没有权限！！");
            return ;
        }
    }

    /**
     * 验证authorization
     *
     * @return
     * */
    protected boolean verifyAuthorization(String authorization,String uPwd) throws IOException {
        String userAndPass = new String(new BASE64Decoder().decodeBuffer(authorization.split(" ")[1]));
        if (!userAndPass.equals(uPwd)) {
            HttpServletResponse response = getResponse();
            response.setStatus(401);
            response.setHeader("WWW-authenticate", "Basic realm=\"请输入密码\"");
            response.getWriter().print("对不起你没有权限！！");
            return false;
        }
        return true;
    }


    /**
     * 获取客户端的IP地址
     *
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }

        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 获取refererUrl
     */
    public String getRefererUrl(HttpServletRequest request) {
        return request.getHeader("referer");
    }

    /**
     *
     * @param request
     *            请求
     * @return 返回请求的数据流
     * @throws IOException
     */
    public String parseRequestString(HttpServletRequest request) throws IOException {
        String inputLine;
        String notityXml = "";
        while ((inputLine = request.getReader().readLine()) != null) {
            notityXml += inputLine;
        }
        request.getReader().close();
        return notityXml;
    }

    /**
     * 获取请求里的参数
     *
     * @param request
     * @return
     */
    public static Map<String, String> getAllRequestParam(HttpServletRequest request){
        Map<String, String> map = new HashMap<>();
        Enumeration request_BodyNames = request.getParameterNames();
        while (request_BodyNames.hasMoreElements()) {
            String name = (String) request_BodyNames.nextElement();
            String value = request.getParameter(name);
            map.put(name, value);
        }
        return map;
    }

    /**
     * 获取请求里的参数
     *
     * @param request
     * @return
     */
    public static Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }


    /**
     * 转换成JSON字符串
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        return JSONObject.toJSONString(object);
    }

    /**
     * 空判断
     *
     * @param object
     * @return
     */
    public static Boolean isEmpty(Object object) {
        return DataUtil.isEmpty(object);
    }

    /**
     * 非空判断
     *
     * @param object
     * @return
     */
    public static Boolean isNotEmpty(Object object) {
        return DataUtil.isNotEmpty(object);
    }
}
