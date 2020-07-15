package com.mp.blog.common.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author tuchuan
 * @description
 * @date 2019/3/16 11:12
 */
public class AopUtils {

    public static <T extends Annotation> T getAnnonaction(ProceedingJoinPoint pjp, Class<T> classz){
        String methodName=pjp.getSignature().getName();
        Method method = null;
        try {
            Class<?> classTarget=pjp.getTarget().getClass();
            Class<?>[] par=((MethodSignature) pjp.getSignature()).getParameterTypes();
            method=classTarget.getMethod(methodName, par);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return method.getAnnotation(classz);
    }
}
