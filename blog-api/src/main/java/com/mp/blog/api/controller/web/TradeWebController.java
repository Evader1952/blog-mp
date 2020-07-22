package com.mp.blog.api.controller.web;

import com.mp.blog.common.common.Response;
import com.mp.blog.common.controller.BaseController;
import com.mp.blog.common.utils.DataUtil;
import com.mp.blog.common.utils.IdempotentUtils;
import com.mp.blog.shopping.dto.AmountData;
import com.mp.blog.shopping.dto.AmountInOut;
import com.mp.blog.shopping.entity.Trade;
import com.mp.blog.shopping.enums.TradeTypeEnum;
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
public class TradeWebController  extends BaseController {

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
            return Response.ok(tradeService.findByPage(query));
        } catch (Exception e) {
            log.error("获取交易列表异常:{}", e);
            return Response.fail("查询失败");
        }
    }

    /**
     * 添加收支
     *
     * @param trade
     * @param types
     * @return
     */
    @RequestMapping(value = "/add")
    public Response<String> addTrade(Trade trade, String types) {
        String check = trade.check();
        if (check != null) {
            return Response.fail(check);
        }
        trade.setType(types.equals(TradeTypeEnum.INCOME.toDesc()) ? 0 : 1);
        if (tradeService.addTrade(trade)) {
            return Response.ok("添加成功");
        }
        return Response.fail("添加失败");
    }

    /**
     * 结算
     *
     * @param redPackState
     * @param state
     * @param id
     * @return
     */
    @RequestMapping(value = "/settle")
    public Response<String> settle(Integer redPackState, Integer state, Long id, Integer type) {
        if (IdempotentUtils.judge(id.toString(), this.getClass())) {
            return Response.fail("请勿重复操作");
        }
        if (tradeService.settle(id, redPackState, state, type)) {
            return Response.ok("操作成功");
        }

        return Response.fail("操作失败");
    }


    /**
     * 获取金额
     * @return
     */
    @RequestMapping(value = "/getAmount")
    public Response<AmountData> getAmount() {
        return Response.ok(tradeService.getAmountData());
    }

    /**
     * 获取金额
     * @return
     */
    @RequestMapping(value = "/getAmountByTime")
    public Response<AmountInOut> getAmountByTime(String startTime,String endTime) {
        return Response.ok(tradeService.getAmountByTime(startTime, endTime));
    }
}
