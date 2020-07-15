package com.mp.blog.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/9/5.
 */
public class IPUtil {
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if(ip != null && ip.length()>0 && !"unknown".equalsIgnoreCase(ip)){
            return ip;
        }
        ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() ==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() ==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() ==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
