package com.mp.blog.common.entity;

import lombok.Data;

/**
 * ftp文件上传下载
 *
 * @author duchong
 * @date 2019-7-8 11:10:56
 */
@Data
public class Ftp {

    /**
     * ip地址
     * */
    private String ipAddr;

    /**
     * 端口号
     * */
    private Integer port;

    /**
     * 用户名
     * */
    private String userName;

    /**
     * 密码
     * */
    private String pwd;

    /**
     * 路径
     * */
    private String path;
}