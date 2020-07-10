package com.mp.blog.permission.service.impl;

import com.mp.blog.common.base.dao.mybatis.BaseMapper;
import com.mp.blog.common.base.service.impl.BaseMybatisServiceImpl;
import com.mp.blog.permission.entity.Role;
import com.mp.blog.permission.mapper.RoleMapper;
import com.mp.blog.permission.service.RoleService;
import com.mp.blog.permission.vo.MenuList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @date 2020-07-09 15:06:32
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
