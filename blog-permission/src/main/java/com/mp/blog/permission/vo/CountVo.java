package com.mp.blog.permission.vo;

import lombok.Data;

@Data
public class CountVo {
    private  Integer successCount;

    private  Integer failCount;

    private  String url;
}
