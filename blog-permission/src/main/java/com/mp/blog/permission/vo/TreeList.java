package com.mp.blog.permission.vo;

import lombok.Data;

import java.util.List;

@Data
public class TreeList {

    private  Long id;
    private  String code;
    private  String title;

    private  String homeCode;

    private  Boolean expand;

    private  Integer state;

    private  Boolean checked;
    private  Integer type;
    private List<TreeList> children;
}
