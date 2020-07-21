package com.mp.blog.api.controller;

import com.mp.blog.common.utils.IdempotentUtils;
import com.mp.blog.shopping.entity.RedPacket;
import com.mp.blog.shopping.service.RedPacketService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: mapei
 * @Date: 2020/7/9 14:25
 */
@Slf4j
@RestController
@Api
public class TestController {

    @Autowired
    private RedPacketService redPacketService;

    @RequestMapping(value = "/test")
    public String test(String id){

        if (!IdempotentUtils.judge(id, this.getClass())) {
            return "执行失败,重复数据";
        }
        // -------------- 幂等性调用（结束） --------------
        // 业务代码...
        redPacketService.insert(RedPacket.builder().tid(Long.valueOf(id)).build());
        System.out.println("添加用户ID:" + id);
        return "执行成功！";


    }

}
