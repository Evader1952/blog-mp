package com.mp.blog.permission.entity;

import com.mp.blog.common.model.Identifiable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRole implements Identifiable<Long> {
    private Long id;

    private  Integer userId;

    private Integer roleId;
}
