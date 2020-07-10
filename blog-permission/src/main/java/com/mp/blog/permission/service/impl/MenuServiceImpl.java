package com.mp.blog.permission.service.impl;

import com.mp.blog.common.base.dao.mybatis.BaseMapper;
import com.mp.blog.common.base.service.impl.BaseMybatisServiceImpl;
import com.mp.blog.permission.entity.Menu;
import com.mp.blog.permission.mapper.MenuMapper;
import com.mp.blog.permission.service.MenuService;
import com.mp.blog.permission.vo.MenuList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @date 2020-07-09 15:06:14
 **/
@Service
public class MenuServiceImpl extends BaseMybatisServiceImpl<Menu,Long> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    @Override
    protected BaseMapper<Menu, Long> getBaseMapper() {
        return menuMapper;
    }

    @Override
    public List<MenuList> queryByIdIn(List<String> ids) {
        return menuMapper.selectByIdIn(ids);
    }
}
