package com.mp.blog.permission.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserDetails {
    private  Long id;
    private  String uid;
    private  String username;
    private  String remark;
    private  String pwd;
    private List<RoleSelect> roleSelects;
}
