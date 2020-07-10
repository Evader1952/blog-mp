package com.mp.blog.common.model;


import com.mp.blog.common.enums.EnumInterface;

/**
 * 网关返回码
 */
public enum CodeMsg implements EnumInterface {
    SYSTEM_ERROR("10500", "系统异常"),
    INVALID_LOGIN("40015", "登陆过期"),
    INVALID_VERSION("30015", "版本过期"),
    FAIL("40500", "操作失败"),
    SUCCESS("20000", "操作成功");

    CodeMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;

    private String msg;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}
