package com.mp.blog.permission.service;

import com.mp.blog.common.service.BaseService;
import com.mp.blog.permission.entity.User;

/**
 * @author Administrator
 * @date 2020-07-09 15:06:52
 **/
public interface UserService extends BaseService<User,Long> {

    User findByUsername(String username);
}
