package com.mp.blog.shopping.enums;

import com.mp.blog.common.utils.DataUtil;

/**
 *
 */
public enum RedPacketStateEnum {
    /**
     *
     */
    OVERHEAD("未返佣", 0),
    SWIPE("已返佣", 1);

    private Integer code;
    private String desc;

    public Integer toCode(){
        return this.code;
    }
    public String toDesc(){
        return this.desc;
    }

    RedPacketStateEnum(String desc, Integer code) {
        this.code = code;
        this.desc = desc;
    }

    public static RedPacketStateEnum getCode(String str){
        for(RedPacketStateEnum e : values()){
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
        for(RedPacketStateEnum e : values()){
            if(e.toCode().equals(str)){
                return e.toDesc();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(RedPacketStateEnum.getDescByCode(1));
    }
}
