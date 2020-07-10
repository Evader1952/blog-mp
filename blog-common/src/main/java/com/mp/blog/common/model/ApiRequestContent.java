package com.mp.blog.common.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author duchong
 * @description
 * @date
 */
@Data
public class ApiRequestContent extends Param {

    private String method;

    private String appId;

    private String cipherJson;

    private String content;

    public String check() {
        if (checkNull(method)) {
            return "缺少方法名";
        }
        if (checkNull(appId)) {
            return "缺少应用ID";
        }
        return null;
    }


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
