package com.mp.blog.permission.service;

import com.mp.blog.common.service.BaseService;
import com.mp.blog.permission.entity.UserRole;
import com.mp.blog.permission.vo.MenuList;

import java.util.List;

/**
 * @author mapei
 * @date 2020-07-09 15:07:08
 **/
public interface UserRoleService extends BaseService<UserRole,Long> {


    /**
     * 根据userId查询权限列表
     * @param userId
     * @return
     */
    List<MenuList> findPermissionByUid(Long userId);

    void deleteByUid(Long id);
}
