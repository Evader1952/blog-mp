package com.mp.blog.api.controller.web;

import com.mp.blog.common.common.Response;
import com.mp.blog.common.utils.DataUtil;
import com.mp.blog.shopping.entity.Trade;
import com.mp.blog.shopping.query.TradeQuery;
import com.mp.blog.shopping.service.TradeService;
import com.mp.blog.shopping.vo.TradeWebList;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: mapei
 * @Date: 2020/7/18 15:30
 */
@Slf4j
@RestController
@Api("账目管理")
@RequestMapping(value = "api/web/trade")
public class TradeWebController {

    @Autowired
    private TradeService tradeService;

    /**
     * 获取交易列表
     *
     * @param query
     * @return
     */
    @RequestMapping(value = "/list")
    public Response<Page<TradeWebList>> getMerchantList(TradeQuery query) {
        if (DataUtil.isEmpty(query.getPage())) {
            query.setPage(0);
        }
        if (DataUtil.isEmpty(query.getLimit())) {
            query.setLimit(10);
        }
        try {
            return Response.ok( tradeService.findByPage(query));
        } catch (Exception e) {
            log.error("获取交易列表异常:{}", e);
            return Response.fail("查询失败");
        }
    }

    @RequestMapping(value = "/add")
    public Response<String> addTrade(Trade trade) {
        String check = trade.check();
        if (check!=null){
            return  Response.fail(check);
        }
        Boolean flag = tradeService.addTrade(trade);
        if (flag){
            return Response.ok("添加成功");
        }
        return Response.fail("添加失败");
    }
}
