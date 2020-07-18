package com.mp.blog.shopping.query;

import com.mp.blog.shopping.entity.Trade;
import lombok.Data;

/**
 * @Author: mapei
 * @Date: 2020/7/18 15:12
 */
@Data
public class TradeQuery  extends Trade {
    private Integer page;

    private Integer limit;

    private Integer offset;
}
