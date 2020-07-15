package com.mp.blog.common.utils;


import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class JsonUtils {
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj,true);
    }

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("name","Lucy");
        map.put("sex","female");
        System.out.println(toJson(map));
    }
}
