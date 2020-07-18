package com.mp.blog.permission.mapper;

import com.mp.blog.common.dao.mybatis.BaseMapper;
import com.mp.blog.permission.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 * @date 2020-07-09 15:06:52
 **/
@Mapper
public interface UserMapper extends BaseMapper<User,Long> {


    /**
     * 根据用户名查找
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 查密码
     * @param id
     * @return
     */
    String findPassword(Long id);
}
