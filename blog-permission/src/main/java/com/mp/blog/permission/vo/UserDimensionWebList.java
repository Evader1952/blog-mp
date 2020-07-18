package com.mp.blog.permission.vo;


import lombok.Data;

@Data
public class UserDimensionWebList {

    private  Long id;
    private Integer type;

    private  String typeDesc;

    private String range;
    private Integer state;
}
