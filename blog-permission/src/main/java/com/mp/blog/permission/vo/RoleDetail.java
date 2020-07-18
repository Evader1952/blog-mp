package com.mp.blog.permission.vo;


import lombok.Data;

/**
 * 冻结/解冻参数
 */
@Data
public class RoleDetail {

    private  Long id;

    private  Long Type;

    private  Integer RoleState;

    private  Integer RoleMenuState;

}

