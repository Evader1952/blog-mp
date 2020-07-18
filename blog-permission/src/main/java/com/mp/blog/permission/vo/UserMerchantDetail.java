package com.mp.blog.permission.vo;

import lombok.Data;

@Data
public class UserMerchantDetail {

    /**
     * 商户号
     */
    private  String uid;

    private Integer type;

    private String sellerNo;

    private String provinceCode;

    private String cityCode;

    private String areaCode;

    private  String areaDesc;


}
