package com.mp.blog.shopping.enums;


import com.mp.blog.common.utils.DataUtil;

public enum TradeTypeEnum {

    /**
     * 0收入 1支出  收支
     */
    INCOME("收入", 0),
    EXPENSES("支出", 1);

    private Integer code;
    private String desc;

    public Integer toCode(){
        return this.code;
    }
    public String toDesc(){
        return this.desc;
    }

    TradeTypeEnum(String desc, Integer code) {
        this.code = code;
        this.desc = desc;
    }

    public static TradeTypeEnum getCode(String str){
        for(TradeTypeEnum e : values()){
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
        for(TradeTypeEnum e : values()){
            if(e.toCode().equals(str)){
                return e.toDesc();
            }
        }
        return null;
    }
}
