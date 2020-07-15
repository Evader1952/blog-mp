package com.mp.blog.common.ratelimit.aop;


import com.google.common.util.concurrent.RateLimiter;
import com.mp.blog.common.common.Response;
import com.mp.blog.common.ratelimit.anonation.RateLimit;
import com.mp.blog.common.utils.IpUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
@Aspect
public class LimitAspect {

    private static Map<String, RateLimiter> caches = new ConcurrentHashMap<>();


    @Pointcut("@annotation(com.mp.blog.common.ratelimit.anonation.RateLimit)")
    public void ServiceAspect() {

    }

    @Around("ServiceAspect()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        RateLimit rateLimit = ((MethodSignature) signature).getMethod().getAnnotation(RateLimit.class);
        if (rateLimit != null) {
            String key = signature.getName();
            if (rateLimit.ipLimit() && getIp() != null) {
                key += getIp();
            }
            RateLimiter rateLimiter = null;
            if (!caches.containsKey(key)) {
                rateLimiter = RateLimiter.create(rateLimit.permitsPerSecond());
                caches.put(key, rateLimiter);
            } else {
                rateLimiter = caches.get(key);
            }
            Boolean flag = rateLimiter.tryAcquire();
            Object obj = null;
            try {
                if (flag) {
                    obj = joinPoint.proceed();
                } else {
                    return Response.fail("五秒钟之后再试！");
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return obj;
        } else {
            return Response.fail("未知错误");
        }
    }

    private String getIp() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            return IpUtils.getIpAddress(request);
        } catch (Exception e) {
            return null;
        }

    }
}
