package com.mp.blog.permission.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MerchantExcelList {


    private Long id;

    /**
     * 商户号
     */
    private String userId;
    /**
     * 商户号
     */
    private String merNo;

    private  Integer type;

    private Integer state;

    private Date createTime;

    /**
     * 数据维度id
     */
    private Long dimensionId;

    private  String reason;

    private  String wayId;


}
