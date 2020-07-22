package com.mp.blog.shopping.service;

import com.mp.blog.common.service.BaseService;
import com.mp.blog.shopping.dto.AmountData;
import com.mp.blog.shopping.dto.AmountInOut;
import com.mp.blog.shopping.entity.Trade;
import com.mp.blog.shopping.query.TradeQuery;
import com.mp.blog.shopping.vo.TradeWebList;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Administrator
 * @date 2020-07-18 15:18:26
 **/
public interface TradeService extends BaseService<Trade,Long> {
    /**
     * 分页查询
     * @param query
     * @return
     */
    Page<TradeWebList> findByPage(TradeQuery query);

    /**
     * 刷单
     * @param trade
     * @return
     */
    Boolean addTrade(Trade trade);

    /**
     * 结算
     * @param id
     * @param redPackState
     * @param state
     * @param type
     * @return
     */
    Boolean settle(Long id, Integer redPackState, Integer state, Integer type);

    /**
     * 获取金额
     * @return
     */
    AmountData getAmountData();

    AmountInOut getAmountByTime(String startTime,String endTime);
}
