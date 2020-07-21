package com.mp.blog.shopping.mapper;

import com.mp.blog.common.dao.mybatis.BaseMapper;
import com.mp.blog.shopping.entity.RedPacket;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 * @date 2020-07-20 14:28:35
 **/
@Mapper
public interface RedPacketMapper extends BaseMapper<RedPacket,Long> {
    /**
     * 根据订单号改
     * @param redPacket
     */
    void updateByTid(RedPacket redPacket);
}
