package com.mp.blog.common.entity;

import com.alibaba.fastjson.JSONObject;
import com.mp.blog.common.utils.DataUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author duchong
 * @description
 * @date
 */
public abstract class Param implements Serializable {

    private SimpleDateFormat timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 检查不为空
     *
     * @param param 参数
     * @return
     */
    protected boolean checkNotNull(Object param) {
        return DataUtil.isNotEmpty(param);
    }

    /**
     * 检查为空
     *
     * @param param 参数
     * @return
     */
    protected boolean checkNull(Object param) {
        return DataUtil.isEmpty(param);
    }

    /**
     * 检查长度
     *
     * @param param  参数
     * @return
     */
    private String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";

    protected boolean checkLink(String param) {
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(param).matches()) {
            return true;
        }
        return false;
    }

    /**
     * 检查长度
     *
     * @param param  参数
     * @param length 最大长度
     * @return
     */
    protected boolean checkLength(String param, Integer length) {
        if (param.length() > length) {
            return true;
        }
        return false;
    }



    /**
     * 验证时间戳
     *
     * @param param 参数
     * @return
     */
    protected boolean checkTimestamp(String param) {
        try {
            timestamp.parse(param);
            return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;

    }

    /**
     * 检查金额格式
     *
     * @param param 参数
     * @return
     */
    protected boolean checkMoneyFormat(String param) {
        try {
           new BigDecimal(param);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    /**
     * 检查金额信息
     *
     * @param param 参数
     * @param max   最大金额
     * @param min   最小金额
     * @return
     */
    protected boolean checkMoney(String param, String max, String min) {
        BigDecimal maxMoney = new BigDecimal(max);
        BigDecimal minMoney = new BigDecimal(min);
        BigDecimal money = new BigDecimal(param);
        Integer maxJudge = money.compareTo(maxMoney);
        Integer minJudge = money.compareTo(minMoney);
        if (maxJudge > 0) {
            return true;
        }
        if (minJudge < 0) {
            return true;
        }
        return false;
    }


    /**
     * 转换数组
     *
     * @param param  参数
     * @param tClass 类型
     * @return
     */
    protected <T> List<T> parseArray(String param, Class<T> tClass) {
        try {
            return JSONObject.parseArray(param, tClass);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 检查长度
     *
     * @param param  参数
     * @param tClass 类型
     * @return
     */
    protected <T> T parseObject(String param, Class<T> tClass) {
        try {
            return JSONObject.parseObject(param, tClass);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 检查该参数是否符合要求
     *
     * @param param 参数
     * @return
     */
    protected boolean checkEquals(Object param, Object... objects) {
        if (checkNull(param) || checkNull(param) || objects.length == 0) {
            return true;
        }
        Integer num = 0;
        for (Object o : objects) {
            if (param.equals(o)) {
                num++;
            }
        }
        return num.equals(0);
    }


    /**
     * 转换成JSON字符串
     *
     * @return
     */
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
