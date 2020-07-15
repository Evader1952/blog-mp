package com.mp.blog.common.utils;

/**
 * @author lvlu
 * @date 2019-06-12 10:29
 **/
public class MobileUtil {

    public static boolean validMobile(String mobile){
        return mobile.matches("^1(3[0-9]|4[0-9]|5[0-9]|6[0-9]|7[0-9]|8[0-9]|9[0-9])\\d{8}$");
    }

    public static void main(String[] args) {
        System.out.println(validMobile("15871350349"));
    }
}
