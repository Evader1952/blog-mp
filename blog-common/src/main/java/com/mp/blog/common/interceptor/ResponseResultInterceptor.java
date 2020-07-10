//package com.mp.blog.common.interceptor;
//
//import com.mp.blog.common.annotation.ResponseResult;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//
///**
// * @author duchong
// * @description 通用返回接口封装
// * @date 2019-12-6 18:03:40
// */
//@Slf4j
//public class ResponseResultInterceptor implements HandlerInterceptor {
//
//    private static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) {
//        if (handler instanceof HandlerMethod){
//            final HandlerMethod handlerMethod = (HandlerMethod) handler;
//            final Class<?> clazz = handlerMethod.getBeanType();
//            final Method method = handlerMethod.getMethod();
//            if(clazz.isAnnotationPresent(ResponseResult.class)){
//                request.setAttribute(RESPONSE_RESULT_ANN,clazz.getAnnotation(ResponseResult.class));
//            }else if (method.isAnnotationPresent(ResponseResult.class)){
//                request.setAttribute(RESPONSE_RESULT_ANN,method.getAnnotation(ResponseResult.class));
//            }
//        }
//        return true;
//    }
//
//}
