package com.mp.blog.shopping.mapper;

import com.mp.blog.common.dao.mybatis.BaseMapper;
import com.mp.blog.shopping.entity.Trade;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 * @date 2020-07-18 15:18:26
 **/
@Mapper
public interface TradeMapper extends BaseMapper<Trade,Long> {


}
