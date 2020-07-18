package com.mp.blog.permission.service.impl;

import com.mp.blog.common.utils.PassWordUtil;
import com.mp.blog.permission.entity.User;
import com.mp.blog.permission.fileter.LoginUser;
import com.mp.blog.permission.fileter.UsernamePasswordToken;
import com.mp.blog.permission.service.UserPermissionResolver;
import com.mp.blog.permission.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author duchong
 * @description
 * @date 2020-1-13 17:40:57
 */
@Slf4j
@Component(value = "userPermissionResolver")
public class MyUserPermissionResolver extends UserPermissionResolver {

    @Autowired
    private UserService userService;


    @Override
    public LoginUser getLoginUser(UsernamePasswordToken token) {
        User user = userService.findByUsername(token.getUsername());
        return getLoginUser(user);
    }


    @Override
    public LoginUser getLoginUser(String userId) {
        User user = userService.queryById(Long.valueOf(userId));
        return getLoginUser(user);
    }

    @Override
    protected Boolean comparePassWord(UsernamePasswordToken token, LoginUser loginUser) {
        String loginPass = PassWordUtil.generatePasswordSha1WithSalt(token.getPassword(), loginUser.getSalt());
        return loginPass.equals(loginUser.getPassword());
    }

    /**
     * 获取登录用户
     *
     * @param user
     * @return
     */
    private LoginUser getLoginUser(User user) {
        if (user != null) {
            //TODO 状态判断
            LoginUser loginUser = new LoginUser();
            loginUser.setUserId(user.getId());
            loginUser.setUsername(user.getUsername());
            loginUser.setPassword(user.getPassword());
            loginUser.setSalt(user.getSalt());
            loginUser.setState(user.getState());
            loginUser.setType(user.getType());
            return loginUser;
        } else {

            return null;
        }
    }
}
