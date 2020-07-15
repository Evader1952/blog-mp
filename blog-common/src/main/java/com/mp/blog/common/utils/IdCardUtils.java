package com.mp.blog.common.utils;


import com.mp.blog.common.exception.BizException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lvlu
 * @date 2019-05-27 16:48
 **/
public class IdCardUtils {
    /**
     * 15位身份证号
     */
    private static final Integer FIFTEEN_ID_CARD = 15;
    /**
     * 18位身份证号
     */
    private static final Integer EIGHTEEN_ID_CARD = 18;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 根据身份证号获取性别
     *
     * @param IDCard
     * @return
     */
    public static String getSex(String IDCard) {
        String sex = "";
        if (DataUtil.isNotEmpty(IDCard)) {
            //15位身份证号
            if (IDCard.length() == FIFTEEN_ID_CARD) {
                if (Integer.parseInt(IDCard.substring(14, 15)) % 2 == 0) {
                    sex = "女";
                } else {
                    sex = "男";
                }
                //18位身份证号
            } else if (IDCard.length() == EIGHTEEN_ID_CARD) {
                // 判断性别
                if (Integer.parseInt(IDCard.substring(16).substring(0, 1)) % 2 == 0) {
                    sex = "女";
                } else {
                    sex = "男";
                }
            }
        }
        return sex;
    }

    /**
     * 根据身份证号获取性别
     * 返回int 0=男 1=女
     *
     * @param IDCard
     * @return
     */
    public static Integer getSexResultInt(String IDCard) {
        String sex = getSex(IDCard);
        if (DataUtil.isEmpty(sex) || (!"男".equals(sex) && !"女".equals(sex))) {
            return null;
        }
        return "男".equals(sex) ? 0 : 1;
    }

    /**
     * 根据身份证号获取年龄
     *
     * @param IDCard
     * @return
     */
    public static Integer getAge(String IDCard) {
        Integer age = 0;
        Date date = new Date();
        if (DataUtil.isNotEmpty(IDCard) && isValid(IDCard)) {
            //15位身份证号
            String year;
            String month;
            String day;
            if (IDCard.length() == FIFTEEN_ID_CARD) {
                // 身份证上的年份(15位身份证为1980年前的)
                year = "19" + IDCard.substring(6, 8);
                // 身份证上的月份
                month = IDCard.substring(8, 10);
                // 身份证上的日期
                day = IDCard.substring(10, 12);
                //18位身份证号
            } else if (IDCard.length() == EIGHTEEN_ID_CARD) {
                // 身份证上的年份
                year = IDCard.substring(6).substring(0, 4);
                // 身份证上的月份
                month = IDCard.substring(10).substring(0, 2);
                day = IDCard.substring(12).substring(0, 2);
                // 当前年份
            } else {
                throw new BizException("不合法的身份证号");
            }
            String fyear = format.format(date).substring(0, 4);
            // 当前月份
            String fyue = format.format(date).substring(5, 7);

            String fday = format.format(date).substring(8, 10);
            // 当前月份大于用户出身的月份表示已过生日
            if (Integer.parseInt(month) < Integer.parseInt(fyue)) {
                age = Integer.parseInt(fyear) - Integer.parseInt(year);
                // 当前用户还没过生日
            } else if (Integer.parseInt(month) == Integer.parseInt(fyue)) {
                if (Integer.parseInt(day) <= Integer.parseInt(fday)) {
                    age = Integer.parseInt(fyear) - Integer.parseInt(year);
                }
            } else {
                age = Integer.parseInt(fyear) - Integer.parseInt(year) - 1;
                age = age > 0 ? age : 0;
            }
        } else {
            throw new BizException("不合法的身份证号");
        }
        return age;
    }

    public static Integer getBornDate(String IDCard) {
        if (IDCard.length() == EIGHTEEN_ID_CARD) {
            Date now = new Date();
            try {
                Date bornDate = new SimpleDateFormat("yyyyMMdd").parse(IDCard.substring(6, 6 + 8));
                return (int) ((now.getTime() - bornDate.getTime()) / (1000 * 3600 * 24));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取出生日期  yyyy年MM月dd日
     *
     * @param IDCard
     * @return
     */
    public static String getBirthday(String IDCard) {
        String birthday = "";
        String year = "";
        String month = "";
        String day = "";
        if (DataUtil.isNotEmpty(IDCard)) {
            //15位身份证号
            if (IDCard.length() == FIFTEEN_ID_CARD) {
                // 身份证上的年份(15位身份证为1980年前的)
                year = "19" + IDCard.substring(6, 8);
                //身份证上的月份
                month = IDCard.substring(8, 10);
                //身份证上的日期
                day = IDCard.substring(10, 12);
                //18位身份证号
            } else if (IDCard.length() == EIGHTEEN_ID_CARD) {
                // 身份证上的年份
                year = IDCard.substring(6).substring(0, 4);
                // 身份证上的月份
                month = IDCard.substring(10).substring(0, 2);
                //身份证上的日期
                day = IDCard.substring(12).substring(0, 2);
            }
            birthday = year + "年" + month + "月" + day + "日";
        }
        return birthday;
    }

    /**
     * 获取出生日期  yyyy年MM月dd日
     *
     * @param IDCard
     * @param connect_char 年月日连接字符
     * @return
     */
    public static String getBirthday(String IDCard, String connect_char) {
        String birthday = "";
        String year = "";
        String month = "";
        String day = "";
        if (DataUtil.isNotEmpty(IDCard)) {
            //15位身份证号
            if (IDCard.length() == FIFTEEN_ID_CARD) {
                // 身份证上的年份(15位身份证为1980年前的)
                year = "19" + IDCard.substring(6, 8);
                //身份证上的月份
                month = IDCard.substring(8, 10);
                //身份证上的日期
                day = IDCard.substring(10, 12);
                //18位身份证号
            } else if (IDCard.length() == EIGHTEEN_ID_CARD) {
                // 身份证上的年份
                year = IDCard.substring(6).substring(0, 4);
                // 身份证上的月份
                month = IDCard.substring(10).substring(0, 2);
                //身份证上的日期
                day = IDCard.substring(12).substring(0, 2);
            }
            birthday = year + connect_char + month + connect_char + day;
        }
        return birthday;
    }

    /**
     * 身份证验证
     *
     * @param id 号码内容
     * @return 是否有效
     */
    public static boolean isValid(String id) {
        Boolean validResult = true;
        //校验长度只能为15或18
        int len = id.length();
        if (len != FIFTEEN_ID_CARD && len != EIGHTEEN_ID_CARD) {
            validResult = false;
        }
        //校验生日
        if (!validDate(id)) {
            validResult = false;
        }
        return validResult;
    }

    /**
     * 校验生日
     *
     * @param id
     * @return
     */
    private static boolean validDate(String id) {
        try {
            String birth = id.length() == 15 ? "19" + id.substring(6, 12) : id.substring(6, 14);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date birthDate = sdf.parse(birth);
            if (!birth.equals(sdf.format(birthDate))) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 校验身份证
     *
     * @param idCardNo
     * @return
     */
    public static boolean isValidDate(String idCardNo) {
        boolean errorInfo = true;//记录错误信息
        //最后一位身份证的号码
        String[] ValCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};
        //17位系数
        String[] Coefficient = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2"};
        String Ai = "";
        //=============号码长度15位或18位
        if (idCardNo.length() != 15 && idCardNo.length() != 18) {
            errorInfo = false;
            return errorInfo;
        }
        //============前17位应为纯数字
        if (idCardNo.length() == 18) {
            Ai = idCardNo.substring(0, 17);
        } else if (idCardNo.length() == 15) {
            Ai = idCardNo.substring(0, 6) + "19" + idCardNo.substring(6, 15);//中间拼的“19”是年数“19**”年
        }
        if (Utils.isNumeric(Ai) == false) {
            errorInfo = false;
            return errorInfo;
        }
        //===========出生年月是否有效
        String IdCardNoYear = Ai.substring(6, 10);
        String IdCardNoMonth = Ai.substring(10, 12);
        String IdCardNoDay = Ai.substring(12, 14);
        if (isDate(IdCardNoYear + "-" + IdCardNoMonth + "-" + IdCardNoDay) == false) {
            errorInfo = false;
            return errorInfo;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(IdCardNoYear)) > 150 || (gc.getTime().getTime() - sdf.parse(IdCardNoYear + "-" +
                    IdCardNoMonth + "-" + IdCardNoDay).getTime()) < 0) {
                errorInfo = false;
                return errorInfo;
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //月份范围1-12
        if (Integer.parseInt(IdCardNoMonth) > 12 || Integer.parseInt(IdCardNoMonth) == 0) {
            errorInfo = false;
            return errorInfo;
        }
        //日期范围1-31
        if (Integer.parseInt(IdCardNoDay) > 31 || Integer.parseInt(IdCardNoDay) == 0) {
            errorInfo = false;
            return errorInfo;
        }
        //========地区码
        Hashtable Hashtable = GetAreaCode();
        if (Hashtable.get(Ai.substring(0, 2)) == null) {
            errorInfo = false;
            return errorInfo;
        }
        //======判断最后一位值
        int TotalmulAiCoefficient = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiCoefficient = TotalmulAiCoefficient + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Coefficient[i]);
        }
        int modValue = TotalmulAiCoefficient % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;
        if (idCardNo.length() == 18) {
            if (Ai.equals(idCardNo) == false) {
                errorInfo = false;
                return errorInfo;
            }
        } else {
            errorInfo = true;
        }
        return errorInfo;
    }


    @SuppressWarnings("unchecked")
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    static Pattern DATE_PATTERN = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");

    /**
     * 判断字符串是否为日期格式
     *
     * @return
     */
    private static boolean isDate(String strDate) {
        Matcher matcher = DATE_PATTERN.matcher(strDate);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
