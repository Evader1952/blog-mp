package com.mp.blog.permission.service;

import com.mp.blog.common.base.service.BaseService;
import com.mp.blog.permission.entity.Menu;
import com.mp.blog.permission.vo.MenuList;

import java.util.List;

/**
 * @author Administrator
 * @date 2020-07-09 15:06:14
 **/
public interface MenuService extends BaseService<Menu,Long> {

    List<MenuList> queryByIdIn(List<String> ids);
}
