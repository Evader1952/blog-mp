package com.mp.blog.permission.vo;

import lombok.Data;

import java.util.List;

@Data
public class RoleDetails {
    private Long id;
    private Integer type;
    private String name;

    /**
     * 备注
     */
    private String remark;

    private List<TreeList> treeList;

}
