package com.mp.blog.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求结果返回
 *
 * @author duchong
 * @date 2019-7-5 10:25:29
 */
@Data
public abstract class ResponseParam extends Param implements Serializable {

    private String status;

    private String reason;

    public boolean isSuccess(){
       return status.endsWith(Status.SUCCESS.toString());
    }

    public void setFail(){
       this.setStatus(Status.FAIL.toString());
    }

    public void setSuccess(){
        this.setStatus(Status.SUCCESS.toString());
    }

    public enum Status{
        SUCCESS,FAIL;
    }
}
