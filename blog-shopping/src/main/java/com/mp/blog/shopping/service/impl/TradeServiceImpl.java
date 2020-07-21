package com.mp.blog.shopping.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mp.blog.common.utils.*;
import com.mp.blog.common.dao.mybatis.BaseMapper;
import com.mp.blog.common.service.impl.BaseMybatisServiceImpl;

import com.mp.blog.shopping.entity.RedPacket;
import com.mp.blog.shopping.entity.Trade;
import com.mp.blog.shopping.enums.*;
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
        RequestContext.RequestUser currentUser = RequestContext.getCurrentUser();
        query.setUid(currentUser.getId());
        Pageable pageable = PageRequest.of(query.getPage(), query.getLimit());
        Page<Trade> page = this.queryPage(query, pageable);
        List<TradeWebList> voList = new ArrayList<>();
        for (Trade trade : page.getContent()) {
            voList.add(getListVo(trade));
        }
        Page<TradeWebList> voPage = new PageImpl<>(voList, pageable, page.getTotalElements());
        return voPage;
    }

    private static final Date date = new Date();

    @Override
    public Boolean addTrade(Trade trade) {
        Boolean flag = true;
        String rebateAmount = "0";
        RedPacket redPacket = new RedPacket();
        try {
            if (TradeBizTypeEnum.SWIPE.toCode().equals(trade.getBizType())) {
                rebateAmount=  TradeBuyWayEnum.TLP.toCode().equals(trade.getBuyWay())?MoneyUtil.subtract(trade.getAmount(), trade.getPaymentAmount()):trade.getRebateAmount();
                trade.setRebateAmount(rebateAmount);
                trade.setRedPackState(0);
                trade.setState(0);
                redPacket = RedPacket.builder().amount(rebateAmount).buyWay(trade.getBuyWay()).createTime(date).state(0).build();
            }
            if (trade.getType().equals(TradeTypeEnum.EXPENSES.toCode())) {
                trade.setAmount("-" + trade.getAmount());
                if (DataUtil.isNotEmpty(trade.getPaymentAmount())) {
                    trade.setPaymentAmount("-" + trade.getPaymentAmount());
                }
            }
            trade.setCreateTime(date);
            RequestContext.RequestUser currentUser = RequestContext.getCurrentUser();
            trade.setUid(currentUser.getId());
            Trade insert = this.insert(trade);
            redPacket.setTid(insert.getId());
            redPacketService.insert(redPacket);
        } catch (Exception e) {
            flag = false;
            log.error("添加trade失败{},{} ", JSONObject.toJSONString(trade), e.getMessage());
        }
        return flag;
    }

    @Override
    public Boolean settle(Long id, Integer redPackState, Integer state, Integer type) {
        try {
            this.updateById(Trade.builder().id(id).redPackState(redPackState).state(state).build());
            if (type.equals(0)) {
                redPacketService.updateByTid(RedPacket.builder().tid(id).state(RedPacketStateEnum.SWIPE.toCode()).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private TradeWebList getListVo(Trade trade) {
        TradeWebList webList = new TradeWebList();
        webList.setId(trade.getId());
        webList.setTitle(trade.getTitle());
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
        webList.setRedPackState(trade.getRedPackState());
        webList.setRedPackStateDesc(RedPacketStateEnum.getDescByCode(trade.getRedPackState()));
        webList.setState(trade.getState());
        webList.setStateDesc(TradeStateEnum.getDescByCode(trade.getState()));
        webList.setCreateTime(DataUtil.isEmpty(trade.getCreateTime()) ? "" : DateUtil.formatDate(trade.getCreateTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
        return webList;
    }
}