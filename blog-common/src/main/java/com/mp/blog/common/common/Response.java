package com.mp.blog.common.common;

import java.io.Serializable;

/**
 * 接口响应
 *
 * @author lvlu
 * @date 2017-12-19 10:08
 **/
public class Response<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    private Response() {
    }

    private Response(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Response<T> ok(T data){
        return response(ResponseCode.SUCCESS, ResponseMsg.SUCCESS,data);
    }

    public static <T> Response<T> ok(String msg,T data){
        return response(ResponseCode.SUCCESS,msg,data);
    }

    public static <T> Response<T> fail(String msg){
        return response(ResponseCode.FAIL,msg,null);
    }

    public static <T> Response<T> notAuthorized(String msg){
        return response(ResponseCode.NOTAUTHORIZED,msg,null);
    }

    public static <T> Response<T> session_timeout(String msg){
        return response(ResponseCode.SESSIONTIMEOUT,msg,null);
    }

    public static <T> Response<T> response(Integer code, String msg,T data){
        Response<T> response = new Response<>(code,msg,data);
        return response;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class ResponseCode {
        public static Integer SUCCESS = 20000;
        public static Integer FAIL = 40500;
        public static Integer ERROR = 50000;
        public static Integer SESSIONTIMEOUT = 40015;
        public static Integer NOTAUTHORIZED = 40100;
    }

    static class ResponseMsg {
        public static String SUCCESS = "SUCCESS";
    }
}
