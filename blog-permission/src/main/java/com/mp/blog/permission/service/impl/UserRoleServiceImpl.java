package com.mp.blog.permission.service.impl;

import com.mp.blog.common.base.dao.mybatis.BaseMapper;
import com.mp.blog.common.base.service.impl.BaseMybatisServiceImpl;
import com.mp.blog.common.utils.DataUtils;
import com.mp.blog.permission.entity.Menu;
import com.mp.blog.permission.entity.RoleMenu;
import com.mp.blog.permission.entity.UserRole;
import com.mp.blog.permission.mapper.UserRoleMapper;
import com.mp.blog.permission.service.MenuService;
import com.mp.blog.permission.service.RoleMenuService;
import com.mp.blog.permission.service.UserRoleService;
import com.mp.blog.permission.vo.MenuList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @date 2020-07-09 15:07:08
 **/
@Service
public class UserRoleServiceImpl extends BaseMybatisServiceImpl<UserRole, Long> implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    protected BaseMapper<UserRole, Long> getBaseMapper() {
        return userRoleMapper;
    }

    @Override
    public List<MenuList> findPermissionByUid(Integer userId) {
        List<MenuList> allMenus = roleMenuService.findMenuByUid(userId);
        List<MenuList> menuLists = new ArrayList<>();
        Map<String, MenuList> menuMap = new HashMap<>(allMenus.size());
        for (MenuList menu : allMenus) {
            menuMap.put(menu.getId(), menu);
        }
        for (MenuList menu : allMenus) {
            MenuList child = menu;
            if (DataUtils.isEmpty(child.getParentId())) {
                menuLists.add(menu);
            } else {
                MenuList parent = menuMap.get(child.getParentId().toString());
                parent.getSubmenus().add(child);
            }
        }

        return menuLists;
    }
}
