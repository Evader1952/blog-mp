package com.mp.blog.permission.mapper;

import com.mp.blog.common.dao.mybatis.BaseMapper;
import com.mp.blog.permission.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 * @date 2020-07-13 11:25:59
 **/
@Mapper
public interface RoleMapper extends BaseMapper<Role,Long> {


}
