package com.mp.blog.permission.service.impl;

import com.mp.blog.common.dao.mybatis.BaseMapper;
import com.mp.blog.common.service.impl.BaseMybatisServiceImpl;
import com.mp.blog.permission.entity.RoleMenu;
import com.mp.blog.permission.entity.UserRole;
import com.mp.blog.permission.mapper.RoleMenuMapper;
import com.mp.blog.permission.service.MenuService;
import com.mp.blog.permission.service.RoleMenuService;
import com.mp.blog.permission.service.UserRoleService;
import com.mp.blog.permission.vo.MenuList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @date 2020-07-09 15:06:42
 **/
@Service
public class RoleMenuServiceImpl extends BaseMybatisServiceImpl<RoleMenu,Long> implements RoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private MenuService menuService;
    @Override
    protected BaseMapper<RoleMenu, Long> getBaseMapper() {
        return roleMenuMapper;
    }

    @Override
    public List<MenuList> findMenuByUid(Integer userId) {
        //根据userId查询对应的Menu
        UserRole userRole = UserRole.builder().userId(userId).build();
        List<UserRole> userRoles = userRoleService.queryList(userRole);
        //所有的菜单
        HashSet<RoleMenu> menus = new HashSet<>();
        for (UserRole ur : userRoles) {
            Integer roleId = ur.getRoleId();
            //根据角色id查询对应的权限
            RoleMenu query = RoleMenu.builder().roleId(roleId).state(RoleMenu.State.open.getCode()).build();
            List<RoleMenu> menu = this.queryList(query);
            for (RoleMenu roleMenu : menu) {
                menus.add(roleMenu);
            }
        }
        List<String> strings = new ArrayList<>();
        Iterator it = menus.iterator();
        while (it.hasNext()) {
            RoleMenu next = (RoleMenu) it.next();
            strings.add(next.getMenuId());
        }
        List<MenuList> allMenus = menuService.queryByIdIn(strings).stream().
                sorted(Comparator.comparing(MenuList::getType)).collect(Collectors.toList());

        return allMenus;
    }
}
