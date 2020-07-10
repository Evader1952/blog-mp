package com.mp.blog.permission.mapper;

import com.mp.blog.common.base.dao.mybatis.BaseMapper;
import com.mp.blog.permission.entity.Menu;
import com.mp.blog.permission.vo.MenuList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 * @date 2020-07-09 15:06:14
 **/
@Mapper
public interface MenuMapper extends BaseMapper<Menu,Long> {


    List<MenuList> selectByIdIn(@Param("ids") List<String> ids);

}
