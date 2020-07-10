package com.mp.blog.permission.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duchong
 * @description 主菜单
 * @date 2019-11-13 14:31:25
 */
@Data
public class MenuList {

    private  String id;
    private String title;

    private String icon;

    private String path;

    private String name;

    private String component;

    private Integer type;

    private Integer parentId;

    private List<MenuList> submenus=new ArrayList<>();
}
