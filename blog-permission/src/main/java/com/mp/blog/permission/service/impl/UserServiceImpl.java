package com.mp.blog.permission.service.impl;

import com.mp.blog.common.dao.mybatis.BaseMapper;
import com.mp.blog.common.service.impl.BaseMybatisServiceImpl;
import com.mp.blog.common.utils.PassWordUtil;
import com.mp.blog.permission.entity.User;
import com.mp.blog.permission.mapper.UserMapper;
import com.mp.blog.permission.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2020-07-09 15:06:52
 **/
@Service
public class UserServiceImpl extends BaseMybatisServiceImpl<User,Long> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    protected BaseMapper<User, Long> getBaseMapper() {
        return userMapper;
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public String changePassword(Long userId, String salt, String password, String oldPassword, String newPassword) {
        String oldPass = PassWordUtil.generatePasswordSha1WithSalt(oldPassword, salt);
        if (!oldPass.equals(password)) {
            return "原密码错误";
        }
        String newPass = PassWordUtil.generatePasswordSha1WithSalt(newPassword, salt);
        User query = new User();
        query.setId(userId);
        query.setPassword(newPass);
        query.setPwd(newPassword);
        this.updateById(query);
        return null;
    }
}
