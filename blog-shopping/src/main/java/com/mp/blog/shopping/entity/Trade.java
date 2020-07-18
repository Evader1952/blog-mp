package com.mp.blog.shopping.entity;

import com.mp.blog.common.entity.Identifiable;
import com.mp.blog.common.entity.Param;
import lombok.Data;

import java.util.Date;

/**
 * @Author: mapei
 * @Date: 2020/7/18 14:17
 */
@Data
public class Trade  extends Param  implements Identifiable<Long>  {

    private  Long id;
    /**
     *商品名称
     */
    private  String goodsName;

    /**
     * 商品类型
     */
    private  Integer goodsType;

    /**
     * 商品价格
     */
    private  String amount;

    /**
     *实付金额
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

    /**
     * 0开销  类型
     * 1刷单
     */
    private  Integer bizType;

    /**
     * 0收入 1支出  收支
     */
    private  Integer type;
    /**
     * 状态
     * 0待返佣
     * 1已返佣
     */
    private  Integer state;
    /**
     * 创建时间
     */
    private Date createTime;

    public String check(){
        if (checkNull(goodsName)){
            return "请输入商品名称";
        }
        if (checkNull(goodsType)){
            return "请选择商品类型";
        }
        if (checkNull(amount)){
            return "请选择价格";
        }
        if (checkNull(paymentAmount)){
            return "请选择价格";
        }
        if (checkNull(buyWay)){
            return "请选择购买渠道";
        }
        if (checkNull(bizType)){
            return "请选择类型";
        }
        if (checkNull(type)){
            return "请选择收支类型";
        }

        return null;
    }
}
