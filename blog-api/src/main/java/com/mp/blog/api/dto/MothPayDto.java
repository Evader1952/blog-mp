package com.mp.blog.api.dto;

import lombok.Data;

/**
 * @Author: mapei
 * @Date: 2020/7/16 15:22
 */
@Data
public class MothPayDto {

    /**
     * 业务场景
     */
    private String business_code;

    /**
     * 资产编码类型
     */
    private String asset_type_code="ALICREDITFF";

    /**
     * 业务类型
     */
    private String biz_type="MONTH";

    /**
     * 商户的身份信息
     */
    private  MerchantDTO merchant;

    /**
     * 商户和蚂蚁的对接模式
     */
    private String link_mode;

    /**
     * 蚂蚁统一会员ID  (可选)
     */
    private String user_id;

    /**
     * 买家的身份信息
     */
    private  UserDTO user;

    /**
     * 表示按月均匀支付  PAY_MONTHLY
     */
    private  String payment_mode;

    /**
     *  按周期收款：  RECEIVE_BY_PERIOD
     */
    private   String receive_mode;

    /**
     * 支付总金额
     */
    private  String total_payment_amount;

    /**
     *分次支付的次数  目前只支持10
     */
    private Integer total_install_num;





}
