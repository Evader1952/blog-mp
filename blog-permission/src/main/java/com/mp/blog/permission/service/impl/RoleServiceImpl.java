package com.mp.blog.permission.service.impl;

import com.mp.blog.common.dao.mybatis.BaseMapper;
import com.mp.blog.common.service.impl.BaseMybatisServiceImpl;
import com.mp.blog.permission.entity.Role;
import com.mp.blog.permission.mapper.RoleMapper;
import com.mp.blog.permission.service.RoleService;
import com.mp.blog.permission.vo.RoleSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<RoleSelect> findAll() {
        List<Role> roleList = roleMapper.selectAll();
        List<RoleSelect> roleSelects = new ArrayList<>();
        for (Role role:roleList ) {
            RoleSelect selectList = getSelectList(role);
            roleSelects.add(selectList);
        }
        return roleSelects ;
    }
    private RoleSelect getSelectList(Role role) {
        RoleSelect roleSelect = new RoleSelect();
        roleSelect.setId(role.getId());
        roleSelect.setName(role.getName());
        roleSelect.setType(Math.toIntExact(role.getId()));
        roleSelect.setSelect(0);
        return roleSelect;
    }
}
