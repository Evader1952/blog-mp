package com.mp.blog.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author duchong
 * @description
 * @date 2019-10-29 10:26:06
 */
@Component
public class ApplicationConfig {


    public static String apiServer;

    @Value("${server.api}")
    public  void setApiServer(String apiServer) {
        ApplicationConfig.apiServer = apiServer;
    }

}
