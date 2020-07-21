package com.mp.blog.shopping.entity;

import com.mp.blog.common.entity.Identifiable;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @Author: mapei
 * 返佣
 * @Date: 2020/7/18 14:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RedPacket implements Identifiable<Long> {


    private  Long id;

    /**
     * 返佣金额
     */
    private  String amount;


    private  Integer buyWay;

    /**
     * 返佣状态
     * 0待返佣
     * 1已返佣
     */
    private Integer state;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 对应订单
     */
    private Long tid;
}
