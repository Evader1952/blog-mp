package com.mp.blog.common.utils;

/**
 * 文件名称
 */
public class FileNameUtil {

    //3.2.1.1 批量扣款明细文件
    public static String ISV_DEDUCTION_FILE = "MPLCASH_888099900000004_DEDUCTION_TIMESTR.DAT";

    //3.2.1.2 和包余额冻结明细回盘文件（花呗分期）
    public static String ISV_FROZEN_BACK_FILE = "MPLCASH_888099900000004_FROZEN_BACK_TIMESTR.DAT";

    //3.2.1.3 服务商结算结果明细文件
    public static String ISV_SETTLE_FILE = "MPLCASH_888099900000004_SETTLE_TIMESTR.DAT";

    //3.2.1.4 资金方激励费发放明细结果文件
    public static String ISV_INC007_FILE = "MPLFEE_999999999999999_INC007_TIMESTR_001.DAT";

    //3.2.2.1 预授权主动还款明细文件
    public static String HB_SETTLE_FILE = "HBCBF_888099900000004_03_TIMESTR.DAT";

    //3.2.2.2 批量扣款回盘文件
    public static String HB_DEDUCTION_FILE = "BACK_MPLCASH_888099900000004_DEDUCTION_TIMESTR.DAT";

    //3.2.2.3 和包余额冻结明细文件（花呗分期）
    public static String HB_FROZEN_FILE = "MPLCASH_888099900000004_FROZEN_TIMESTR.DAT";

    //3.2.2.4分省业务办理确认文件（预授权）
    public static String HB_YSQ_FILE = "HBCBF_YSQ_PROVINCE_888099900000004_01_TIMESTR_001.DAT";

    //3.2.2.5 分省业务办理确认文件（花呗分期）
    public static String HB_HB_FILE = "HBCBF_HB_PROVINCE_888099900000004_01_TIMESTR.DAT";

    //3.2.2.6分省提前清贷/退货文件（预授权）
    public static String HB_YSQ_BACK_FILE = "HBCBF_YSQ_PROVINCE_888099900000004_02_TIMESTR_001.DAT";

    /**
     * 获取文件名称
     * @param fileName
     * @param timeStr
     * @return
     */
    public static String getFileName(String fileName, String timeStr) {
        if (DataUtil.isEmpty(fileName) || DataUtil.isEmpty(timeStr)) {
            return null;
        }
        if (fileName.indexOf("TIMESTR") < 0) {
            return null;
        }
        return fileName.replace("TIMESTR", timeStr);
    }

    /**
     * 获取文件名称
     * @param fileName
     * @param provinceCode
     * @param timeStr
     * @return
     */
    public static String getFileName(String fileName, String provinceCode, String timeStr) {
        if (DataUtil.isEmpty(fileName) || DataUtil.isEmpty(provinceCode) || DataUtil.isEmpty(timeStr)) {
            return null;
        }
        if (fileName.indexOf("TIMESTR") < 0) {
            return null;
        }
        if (fileName.indexOf("PROVINCE") < 0) {
            return null;
        }
        return fileName.replace("PROVINCE", provinceCode).replace("TIMESTR", timeStr);
    }

}
