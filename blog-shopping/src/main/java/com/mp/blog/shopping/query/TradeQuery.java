package com.mp.blog.shopping.query;

import com.mp.blog.shopping.entity.Trade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: mapei
 * @Date: 2020/7/18 15:12
 */
@Data

public class TradeQuery  extends Trade {
    private Integer page;

    private Integer limit;

    private Integer offset;

    private String startTime;

    private String endTime;
}
