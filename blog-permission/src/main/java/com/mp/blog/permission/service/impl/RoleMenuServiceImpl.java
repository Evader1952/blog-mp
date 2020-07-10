package com.mp.blog.permission.service.impl;

import com.mp.blog.common.base.dao.mybatis.BaseMapper;
import com.mp.blog.common.base.service.impl.BaseMybatisServiceImpl;
import com.mp.blog.permission.entity.RoleMenu;
import com.mp.blog.permission.mapper.RoleMenuMapper;
import com.mp.blog.permission.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2020-07-09 15:06:42
 **/
@Service
public class RoleMenuServiceImpl extends BaseMybatisServiceImpl<RoleMenu,Long> implements RoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;


    @Override
    protected BaseMapper<RoleMenu, Long> getBaseMapper() {
        return roleMenuMapper;
    }
}
