package com.mp.blog.shopping.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mp.blog.common.utils.*;
import com.mp.blog.common.dao.mybatis.BaseMapper;
import com.mp.blog.common.service.impl.BaseMybatisServiceImpl;

import com.mp.blog.shopping.entity.RedPacket;
import com.mp.blog.shopping.entity.Trade;
import com.mp.blog.shopping.enums.TradeBizTypeEnum;
import com.mp.blog.shopping.enums.TradeBuyWayEnum;
import com.mp.blog.shopping.enums.TradeStateEnum;
import com.mp.blog.shopping.enums.TradeTypeEnum;
import com.mp.blog.shopping.mapper.TradeMapper;
import com.mp.blog.shopping.query.TradeQuery;
import com.mp.blog.shopping.service.RedPacketService;
import com.mp.blog.shopping.service.TradeService;
import com.mp.blog.shopping.vo.TradeWebList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @date 2020-07-18 15:18:26
 **/
@Slf4j
@Service
public class TradeServiceImpl extends BaseMybatisServiceImpl<Trade, Long> implements TradeService {

    @Autowired
    private TradeMapper tradeMapper;

    @Autowired
    private RedPacketService redPacketService;

    @Override
    protected BaseMapper<Trade, Long> getBaseMapper() {
        return tradeMapper;
    }

    @Override
    public Page<TradeWebList> findByPage(TradeQuery query) {

        Pageable pageable = PageRequest.of(query.getPage(), query.getLimit());
        Page<Trade> page = this.queryPage(query, pageable);
        List<TradeWebList> voList = new ArrayList<>();
        for (Trade trade : page.getContent()) {
            voList.add(getListVo(trade));
        }
        Page<TradeWebList> voPage = new PageImpl<>(voList, pageable, page.getTotalElements());
        return voPage;
    }

    private  static final Date date = new Date();
    @Override
    public Boolean addTrade(Trade trade) {
        Boolean flag = true;

        try {
            if (trade.getType().equals(TradeTypeEnum.EXPENSES.toCode())) {
                trade.setAmount("-" + trade.getAmount());
            }
            if (TradeBizTypeEnum.SWIPE.toCode().equals(trade.getBizType()) && TradeBuyWayEnum.TLP.toCode().equals(trade.getBuyWay())) {
               String rebateAmount= MoneyUtil.subtract(trade.getAmount(), trade.getPaymentAmount());
                trade.setRebateAmount(rebateAmount);
                 RedPacket redPacket = RedPacket.builder().amount(rebateAmount).buyWay(trade.getBuyWay()).createTime(date).build();
                redPacketService.insert(redPacket);
            }
            trade.setCreateTime(date);
            Trade insert = this.insert(trade);
        } catch (Exception e) {
            flag = false;
            log.error("添加trade失败{},{} ", JSONObject.toJSONString(trade), e.getMessage());
        }
        return flag;
    }

    @Override
    public Boolean addOverhead(Trade trade) {
        Boolean flag=true;
        if (trade.getBizType().equals(TradeBizTypeEnum.OVERHEAD.toCode())){
            this.insert(trade);
        }else {
            flag=false;
        }
        return flag;
    }

    private TradeWebList getListVo(Trade trade) {
        TradeWebList webList = new TradeWebList();
        webList.setId(trade.getId());
        webList.setGoodsName(trade.getAmount());
        webList.setGoodsType(trade.getGoodsType());
        webList.setAmount(trade.getAmount());
        webList.setPaymentAmount(trade.getPaymentAmount());
        webList.setRebateAmount(trade.getRebateAmount());
        webList.setRemark(trade.getRemark());
        webList.setBuyWay(trade.getBuyWay());
        webList.setBuyWayDesc(TradeBuyWayEnum.getDescByCode(trade.getBuyWay()));
        webList.setBizType(trade.getBizType());
        webList.setBizTypeDesc(TradeBizTypeEnum.getDescByCode(trade.getBizType()));
        webList.setType(trade.getType());
        webList.setTypeDesc(TradeTypeEnum.getDescByCode(trade.getType()));
        webList.setState(trade.getState());
        webList.setStateDesc(TradeStateEnum.getDescByCode(trade.getState()));
        webList.setCreateTime(trade.getCreateTime());
        return webList;
    }
}