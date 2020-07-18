package com.mp.blog.permission.service;

import com.mp.blog.common.service.BaseService;
import com.mp.blog.permission.entity.Role;
import com.mp.blog.permission.vo.RoleSelect;

import java.util.List;

/**
 * @author Administrator
 * @date 2020-07-13 11:25:59
 **/
public interface RoleService extends BaseService<Role,Long> {

    List<RoleSelect> findAll();
}
