package com.mp.blog.common.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author duchong
 * @description
 * @date
 */
public abstract class RequestParam extends Param implements Serializable {
    private static Logger _log = LoggerFactory.getLogger(RequestParam.class);

    /**
     * 参数检查
     *
     * @return 返回检查结果
     */
    public abstract String check();
}
