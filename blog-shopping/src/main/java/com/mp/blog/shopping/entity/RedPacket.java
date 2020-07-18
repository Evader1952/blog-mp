package com.mp.blog.shopping.entity;

import com.mp.blog.common.entity.Identifiable;
import lombok.Data;

import java.util.Date;

/**
 * @Author: mapei
 * 返佣
 * @Date: 2020/7/18 14:47
 */
@Data
public class RedPacket implements Identifiable<Long> {


    private  Long id;

    /**
     * 返佣金额
     */
    private  String amount;


    private  String way;
    /**
     * 创建时间
     */
    private Date createTime;
}
