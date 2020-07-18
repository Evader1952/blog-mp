package com.mp.blog.permission.vo;

import lombok.Data;

@Data
public class UserWebList {
    private Long id;

    /**
     * 用户名
     */
    private String nikeName;
    /**
     * 账号
     */
    private  String username;
    /**
     * 密码
     */
    private  String passWord;
    /**
     * 备注
     */
    private  String remark;

    private Integer type;

    private  String typeDesc;

    private  Integer state;
    private  String stateDesc;
    private  String uid;

}
