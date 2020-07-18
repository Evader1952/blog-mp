package com.mp.blog.shopping.enums;

import com.mp.blog.common.utils.DataUtil;

/**
 *
 */
public enum TradeBuyWayEnum   {
    /**
     * 购买渠道
     * 0 淘宝
     * 1 京东
     * 2 天猫
     * 3 拼多多
     * 4 淘礼品
     * 5 线下商店
     */
    TB("淘宝", 0),
    JD("京东", 1),
    TM("天猫", 2),
    PDD("拼多多", 3),
    TLP("淘礼品", 4),
    OFFLINE("线下", 5);

    private Integer code;
    private String desc;

    public Integer toCode(){
        return this.code;
    }
    public String toDesc(){
        return this.desc;
    }

    TradeBuyWayEnum(String desc, Integer code) {
        this.code = code;
        this.desc = desc;
    }

    public static TradeBuyWayEnum getCode(String str){
        for(TradeBuyWayEnum e : values()){
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
        for(TradeBuyWayEnum e : values()){
            if(e.toCode().equals(str)){
                return e.toDesc();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(TradeBuyWayEnum.getDescByCode(1));
    }
}
