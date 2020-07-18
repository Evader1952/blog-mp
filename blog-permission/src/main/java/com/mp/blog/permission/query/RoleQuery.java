package com.mp.blog.permission.query;

import com.mp.blog.permission.entity.Role;
import lombok.Data;

@Data
public class RoleQuery extends Role {
    private Integer page;

    private Integer limit;

    private Integer offset;

  //  private String sort;
}
