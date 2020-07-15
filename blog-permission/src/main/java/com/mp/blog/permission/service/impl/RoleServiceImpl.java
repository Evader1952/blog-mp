package com.mp.blog.permission.service.impl;

import com.mp.blog.common.dao.mybatis.BaseMapper;
import com.mp.blog.common.service.impl.BaseMybatisServiceImpl;
import com.mp.blog.permission.entity.Role;
import com.mp.blog.permission.mapper.RoleMapper;
import com.mp.blog.permission.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2020-07-13 11:25:59
 **/
@Service
public class RoleServiceImpl extends BaseMybatisServiceImpl<Role,Long> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    protected BaseMapper<Role, Long> getBaseMapper() {
        return roleMapper;
    }
}
