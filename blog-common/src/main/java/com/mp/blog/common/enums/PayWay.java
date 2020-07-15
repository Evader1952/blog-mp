package com.mp.blog.common.enums;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 付款方式
 * @author duchong
 * @date 2019-2-21 14:07:08
 * */
@Data
public class PayWay {

    public static Map<String,String> way_map = new HashMap<>();


    public enum Way{
        BALANCE("BALANCE","余额支付"),
        CREDIT("CREDIT","分期支付");

        private String code;
        private String desc;

        Way(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
