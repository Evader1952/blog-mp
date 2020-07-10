package com.mp.blog.permission.mapper;

import com.mp.blog.common.base.dao.mybatis.BaseMapper;
import com.mp.blog.permission.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 * @date 2020-07-09 15:06:32
 **/
@Mapper
public interface RoleMapper extends BaseMapper<Role,Long> {


}
