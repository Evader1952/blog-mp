package com.mp.blog.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liuyangkly on 15/6/27.
 * 杂物工具类
 */
public class Utils {

    private Utils() {
        // No instances.
    }

    public static String toAmount(long amount) {
        return new BigDecimal(amount).divide(new BigDecimal(100)).toString();
    }

    public static String toDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    static Pattern NUMBERIC_PATTERN = Pattern.compile("^[0-9]+(.[0-9]+)?$");

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {

        Matcher isNum = NUMBERIC_PATTERN.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    static Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");

    /**
     * 判断字符串是否为数字
     *
     * @param c
     * @return
     */
    public static boolean isNumberByChar(char c) {
        Matcher isNum = NUMBER_PATTERN.matcher(String.valueOf(c));
        return isNum.matches();
    }

    static String regEx = "[\u4e00-\u9fa5]";
    static Pattern pat = Pattern.compile(regEx);

    public static boolean isContainsChinese(String str) {
        if (str == null) {
            return false;
        }
        Matcher matcher = pat.matcher(str);
        boolean flg = false;
        if (matcher.find()) {
            flg = true;
        }
        return flg;
    }

    /**
     * 获取金额，若金额为整数，则显示整数，若金额为小数，则保留两位小数
     *
     * @param total_money
     * @return
     */
    public static String getMoney(String total_money) {
        if (total_money == null) {
            return "0.00";
        }
        //判断total_money中是否包含 "."
        int ind = total_money.indexOf(".");
        //ind如果不等于-1，代表存在"."
        if (ind != -1) {
            String[] money = total_money.split("\\.");
            Double f = Double.valueOf(money[1]);
            if (f >= 0) {
                BigDecimal b = new BigDecimal(total_money);
                //保留2位小数
                DecimalFormat df = new DecimalFormat("0.00");
                String scale_3 = b.setScale(3, BigDecimal.ROUND_HALF_EVEN).toString();
                String m = df.format(new BigDecimal(scale_3).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
                return m;
            } else {
                return money[0];
            }
        } else {
            total_money = total_money + ".00";
        }
        return total_money;
    }

    /**
     * 根据身份证号码计算年龄
     *
     * @param idCardNo
     * @return
     */
    public static Integer IDCardNoToAge(String idCardNo) {
        int length = idCardNo.length();
        String dates = "";
        if (length == 18) {
            int se = Integer.valueOf(idCardNo.substring(length - 1)) % 2;
            dates = idCardNo.substring(6, 10);
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year = df.format(new Date());
            int u = Integer.parseInt(year) - Integer.parseInt(dates);
            return u;
        } else {
            if (length == 15) {
                dates = idCardNo.substring(6, 8);
                return Integer.parseInt(dates);
            } else {
                return 0;
            }
        }
    }




    /**
     * 创建指定数量的随机字符串
     *
     * @param numberFlag 是否是数字
     * @param length
     * @return
     */
    public static String createRandom(boolean numberFlag, int length) {
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);
        return retStr;
    }

    /**
     * 清除文本中的h5标签
     *
     * @param inputString
     * @return
     */
    public static String removeHtmlTag(String inputString) {
        if (inputString == null) {
            return null;
        }
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        Pattern p_script;
        Matcher m_script;
        Pattern p_style;
        Matcher m_style;
        Pattern p_html;
        Matcher m_html;
        Pattern p_special;
        Matcher m_special;
        try {
            //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
            //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
            // 定义HTML标签的正则表达式
            String regEx_html = "<[^>]+>";
            // 定义一些特殊字符的正则表达式 如：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            String regEx_special = "\\&[a-zA-Z]{1,10};";

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签
            p_special = Pattern.compile(regEx_special, Pattern.CASE_INSENSITIVE);
            m_special = p_special.matcher(htmlStr);
            htmlStr = m_special.replaceAll(""); // 过滤特殊标签
            textStr = htmlStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textStr;// 返回文本字符串
    }
}
