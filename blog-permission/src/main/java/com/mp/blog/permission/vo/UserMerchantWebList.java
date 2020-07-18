package com.mp.blog.permission.vo;

import lombok.Data;

@Data
public class UserMerchantWebList {

    private Long id;

    private String uid;

    private String username;

    private String password;

    private  Long merchantCount;

    private  Long rangeCount;

    private String remark;

    private String createTime;

    private String stateDesc;

    private Integer type;

    private String TypeDesc;

    private Integer state;



}
