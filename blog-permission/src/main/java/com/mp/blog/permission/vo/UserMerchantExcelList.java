package com.mp.blog.permission.vo;

import lombok.Data;

@Data
public class UserMerchantExcelList {
    private String wayId;

    private  String createTime;
    private String reason;


    public static String[] headers = {
            "渠道编码",
            "创建时间",
            "失败原因",
    };
    public static String[] keys = {
            "wayId",
            "createTime",
            "reason",

    };
}
