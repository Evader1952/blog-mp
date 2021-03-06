package com.mp.blog.shopping.enums;


import com.mp.blog.common.utils.DataUtil;

public enum TradeStateEnum {
    /**
     *
     */
    NOREBATE("未结算", 0),
    REBATE("已结算", 1);

    private Integer code;
    private String desc;

    public Integer toCode(){
        return this.code;
    }
    public String toDesc(){
        return this.desc;
    }

    TradeStateEnum(String desc, Integer code) {
        this.code = code;
        this.desc = desc;
    }

    public static TradeStateEnum getCode(String str){
        for(TradeStateEnum e : values()){
            if(e.toDesc().equals(str)){
                return e;
            }
        }
        return null;
    }

    public static String getDescByCode(Integer str) {
        if (DataUtil.isEmpty(str)){
            return null;
        }
        for(TradeStateEnum e : values()){
            if(e.toCode().equals(str)){
                return e.toDesc();
            }
        }
        return null;
    }
}
