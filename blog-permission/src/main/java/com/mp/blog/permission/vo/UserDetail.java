package com.mp.blog.permission.vo;


import lombok.Data;

@Data
public class UserDetail {

    private  Long id;
    private  String uid;
    private  String username;
    private  String remark;
    private  String pwd;
    private  Long[] roleTypes;

}
