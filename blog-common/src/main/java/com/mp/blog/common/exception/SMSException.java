package com.mp.blog.common.exception;

/**
 * @author lvlu
 * @date 2019-06-12 11:12
 **/
public class SMSException  extends RuntimeException {

    private String msg;

    public SMSException(String msg){
        super(msg);
        this.msg = msg;
    }
}
