package com.mp.blog.permission.service;

import com.mp.blog.common.service.BaseService;
import com.mp.blog.permission.entity.User;

/**
 * @author Administrator
 * @date 2020-07-09 15:06:52
 **/
public interface UserService extends BaseService<User,Long> {

    User findByUsername(String username);

    /**
     * 修改密码
     * @param userId
     * @param salt
     * @param password
     * @param oldPassword
     * @param newPassword
     * @return
     */
    String changePassword(Long userId, String salt, String password, String oldPassword, String newPassword);

}
