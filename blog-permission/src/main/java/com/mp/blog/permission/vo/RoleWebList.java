package com.mp.blog.permission.vo;

import lombok.Data;

@Data
public class RoleWebList {
    private Long id;

    private Integer type;

    private String name;

    private  String remark;
    /**
     * 绑定账号数
     */
    private Long count;

    private  Integer state;

    private String stateDesc;

}
