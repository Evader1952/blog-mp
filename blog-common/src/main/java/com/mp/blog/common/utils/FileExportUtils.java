package com.mp.blog.common.utils;
//文件导出时  生成文件名及判断时间工具类 wusi

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FileExportUtils {
    static  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    public static String getFileName(String item,String startTime,String endTime) throws ParseException {
        if (startTime == null && endTime == null) {
            String format = sdf.format(new Date());
            startTime=format;
            endTime=format;
        }
        startTime = startTime.replaceAll("-", "").substring(0, 8);
        endTime = endTime.replaceAll("-", "").substring(0,8);
        Date start = sdf.parse(startTime);
        Date end = sdf.parse(endTime);
        String name=null;
        if (startTime.equals(endTime)) {//同一天
            name = item + startTime;
        } else if (!startTime.substring(0, 4).equals(endTime.substring(0, 4))) {//跨年
            name = item + startTime + "-" + endTime;
        } else {
            name = item + startTime + "-" + endTime.substring(endTime.length() - 4, endTime.length());
        }
        Long time = System.currentTimeMillis();
        String timeStamp = time.toString();
        return name+"-"+timeStamp;

    }
    public static long getDays(String startTime,String endTime) throws ParseException {
        if (startTime == null && endTime == null) {
            String format = sdf.format(new Date());
            startTime=format;
            endTime=format;
        }
        startTime = startTime.replaceAll("-", "").substring(0, 8);
        endTime = endTime.replaceAll("-", "").substring(0,8);
        Date start = sdf.parse(startTime);
        Date end = sdf.parse(endTime);
        long day = end.getTime() - start.getTime();
        int days = (int) ((day) / (1000 * 60 * 60 * 24));
        return days;
    }

    public  static Map<String,String> getQueryTime(String startTime, String endTime){
        Map<String,String> map=new HashMap<>(2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (DataUtil.isEmpty(startTime)&&DataUtil.isEmpty(endTime) ) {
            String format = sdf.format(new Date());
            map.put("startTime", DateUtil.getStartOrEndTime(format, 0));
            map.put("endTime",DateUtil.getStartOrEndTime(format, 1));
        }else {
            map.put("startTime",DateUtil.getStartOrEndTime(startTime, 0));
            map.put("endTime",DateUtil.getStartOrEndTime(endTime, 1));
        }
        return  map;
    }

    public static void main(String[] args) {
        Map<String, String> queryTime = getQueryTime("2020-06-05", "2020-06-23");
        System.out.println(queryTime.get("startTime"));
        System.out.println(queryTime.get("endTime"));
    }
}
