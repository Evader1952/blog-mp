package com.mp.blog.permission.mapper;

import com.mp.blog.common.dao.mybatis.BaseMapper;
import com.mp.blog.permission.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 * @date 2020-07-09 15:07:08
 **/
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole,Long> {


}
