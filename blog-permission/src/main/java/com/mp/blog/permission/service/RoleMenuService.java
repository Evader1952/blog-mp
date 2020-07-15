package com.mp.blog.permission.service;

import com.mp.blog.common.service.BaseService;
import com.mp.blog.permission.entity.RoleMenu;
import com.mp.blog.permission.vo.MenuList;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author Administrator
 * @date 2020-07-09 15:06:42
 **/
public interface RoleMenuService extends BaseService<RoleMenu,Long> {

    /**
     * 根据Uid查询拥有的menu
     * @param userId
     * @return
     */
    List<MenuList> findMenuByUid(Integer userId);
}
