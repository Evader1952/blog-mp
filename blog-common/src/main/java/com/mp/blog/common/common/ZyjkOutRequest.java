package com.mp.blog.common.common;

import lombok.Data;

/**
 * @author lvlu
 * @date 2019-07-03 14:41
 **/
@Data
public class ZyjkOutRequest {

    private String requestTm;

    private String jrnNo;

    private String methodName = "/test";
}
