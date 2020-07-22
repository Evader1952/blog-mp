package com.mp.blog.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: mapei
 * @Date: 2020/7/22 10:27
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmountData {

    /**
     * 天收入
     */
    private  String dayInAmount;

    /**
     * 天支出
     */
    private  String dayOutAmount;
    /**
     * 周收入
     */
    private  String weekInAmount;
    /**
     * 周支出
     */
    private  String weekOutAmount;

    /**
     * 月收入
     */
    private String monthInAmount;

    /**
     * 月支出
     */
    private  String monthOutAmount;


    /**
     * 年收入
     */
    private  String yearInAmount;
    /**
     * 年支出
     */
    private  String yearOutAmount;
    /**
     * 总收入
     */
    private  String allInAmount;
    /**
     * 总支出
     */
    private  String allOutAmount;

    /**
     * 总佣金
     */
    private  String allRedPacket;

    @Override
    public String toString() {
        return "AmountData{" +
                "dayInAmount='" + dayInAmount + '\'' +
                ", dayOutAmount='" + dayOutAmount + '\'' +
                ", weekInAmount='" + weekInAmount + '\'' +
                ", weekOutAmount='" + weekOutAmount + '\'' +
                ", monthInAmount='" + monthInAmount + '\'' +
                ", monthOutAmount='" + monthOutAmount + '\'' +
                ", yearInAmount='" + yearInAmount + '\'' +
                ", yearOutAmount='" + yearOutAmount + '\'' +
                ", allInAmount='" + allInAmount + '\'' +
                ", allOutAmount='" + allOutAmount + '\'' +
                ", allRedPacket='" + allRedPacket + '\'' +
                '}';
    }
}

