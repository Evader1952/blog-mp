package com.mp.blog.web;

import com.mp.blog.shopping.dto.AmountData;
import com.mp.blog.shopping.service.TradeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: mapei
 * @Date: 2020/7/22 11:51
 */
public class TradeTest  extends  BlogMpApplicationTests {

    @Autowired
    private TradeService tradeService;

    @Test
    public   void test() {

        AmountData amountData = tradeService.getAmountData();
        System.err.println(amountData.toString());
    }
}
