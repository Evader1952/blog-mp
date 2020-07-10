package com.mp.blog.api.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
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


    @RequestMapping(value = "/test")
    public String test(){

        return "测试";
    }

}
