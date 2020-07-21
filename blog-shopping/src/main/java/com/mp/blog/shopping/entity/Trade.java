package com.mp.blog.shopping.entity;

import com.mp.blog.common.entity.Identifiable;
import com.mp.blog.common.entity.Param;
import com.mp.blog.shopping.enums.TradeBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: mapei
 * @Date: 2020/7/18 14:17
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Trade  extends Param  implements Identifiable<Long>  {

    private  Long id;
    /**
     *收支简介
     */
    private  String title;

    /**
     * 收支金额
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
     * 2 拼多多
     * 3 淘礼品
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
     * 返佣状态
     * 0待返佣
     * 1已返佣
     */
    private  Integer redPackState;

    /**
     * 0未结算
     * 1已结算
     */
    private  Integer state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     * @return
     */
    private  Long uid;

    public String check(){
        if (checkNull(title)) {
            return "请输入备注（名称）";
        }
        if (checkNull(amount)) {
            return "请选择价格";
        }
        if (TradeBizTypeEnum.SWIPE.toCode().equals(bizType)) {
            if (checkNull(paymentAmount)) {
                return "请选择实付金额";
            }
            if (checkNull(buyWay)) {
                return "请选择购买渠道";
            }
            if (checkNull(bizType)) {
                return "请选择类型";
            }
        }else if (TradeBizTypeEnum.OVERHEAD.toCode().equals(bizType)){

        }
        return null;
    }

}
