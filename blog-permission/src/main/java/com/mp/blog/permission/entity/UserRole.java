package com.mp.blog.permission.entity;

import com.mp.blog.common.entity.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Identifiable<Long> {
    private Long id;

    private  Long userId;

    private Long roleId;
}
