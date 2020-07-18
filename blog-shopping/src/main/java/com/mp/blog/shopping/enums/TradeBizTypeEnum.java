package com.mp.blog.shopping.enums;

import com.mp.blog.common.utils.DataUtil;

/**
 *
 */
public enum TradeBizTypeEnum {
    /**
     *
     */
    OVERHEAD("开销", 0),
    SWIPE("刷单", 1);

    private Integer code;
    private String desc;

    public Integer toCode(){
        return this.code;
    }
    public String toDesc(){
        return this.desc;
    }

    TradeBizTypeEnum(String desc, Integer code) {
        this.code = code;
        this.desc = desc;
    }

    public static TradeBizTypeEnum getCode(String str){
        for(TradeBizTypeEnum e : values()){
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
        for(TradeBizTypeEnum e : values()){
            if(e.toCode().equals(str)){
                return e.toDesc();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(TradeBizTypeEnum.getDescByCode(1));
    }
}
