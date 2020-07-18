package com.mp.blog.shopping.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: mapei
 * @Date: 2020/7/18 15:19
 */
@Data
public class TradeWebList {

    private  Long id;
    /**
     *商品名称
     */
    private  String goodsName;
    /**
     *
     */
    private  Integer goodsType;

    private  String amount;

    /**
     *
     */
    private String paymentAmount;
    /**
     * 返佣
     */
    private String rebateAmount;

    private  String remark;

    /**
     * 购买渠道
     * 0 淘宝
     * 1 京东
     * 2 天猫
     * 3 拼多多
     * 4 淘礼品
     * 5 线下商店
     */
    private  Integer buyWay;

    private  String buyWayDesc;
    /**
     * 0开销
     * 1刷单
     */
    private  Integer bizType;

    private  String bizTypeDesc;

    /**
     * 0收入 1支出  收支
     */
    private  Integer type;

    private  String typeDesc;
    /**
     * 状态
     * 0待返佣
     * 1已返佣
     */
    private  Integer state;

    private  String stateDesc;
    /**
     * 创建时间
     */
    private Date createTime;
}
