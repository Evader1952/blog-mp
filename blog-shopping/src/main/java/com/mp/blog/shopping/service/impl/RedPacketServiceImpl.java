package com.mp.blog.shopping.service.impl;

import com.mp.blog.common.dao.mybatis.BaseMapper;
import com.mp.blog.common.service.impl.BaseMybatisServiceImpl;
import com.mp.blog.shopping.entity.RedPacket;
import com.mp.blog.shopping.mapper.RedPacketMapper;
import com.mp.blog.shopping.service.RedPacketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2020-07-20 14:28:35
 **/
@Service
@Slf4j
public class RedPacketServiceImpl extends BaseMybatisServiceImpl<RedPacket,Long> implements RedPacketService {

    @Autowired
    private RedPacketMapper redPacketMapper;


    @Override
    protected BaseMapper<RedPacket, Long> getBaseMapper() {
        return redPacketMapper;
    }

    @Override
    public Boolean updateByTid(RedPacket redPacket) {
        try {
            redPacketMapper.updateByTid(redPacket);
        } catch (Exception e) {
            log.error("修改redPacket失败，{}", redPacket);
            return  false;
        }
        return  true;
    }
}
