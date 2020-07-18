package com.mp.blog.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(value = {"com.mp.blog"})
@MapperScan(value = {"com.mp.blog.*.mapper"})
@ServletComponentScan(basePackages = {"com.mp.blog.permission.filter"})
@EnableConfigurationProperties
@SpringBootApplication
public class BlogMpApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogMpApplication.class, args);
    }

}
