/**
 * lightingboot.com
 */
package com.mp.blog.common.enums;


import org.apache.commons.lang3.StringUtils;

/**
 * 支付客户端
 * @author simon
 * @version $Id: PaymentType.java, v 0.1 2017-06-23 下午5:19 simon Exp $$
 */
public enum PayClient {

                       ALIPAY("01", "支付宝", "ALI"), WEIXIN("02", "微信支付", "WX");

    private String channelType;

    private String code;

    private String desc;

    PayClient(String code, String desc, String channelType) {
        this.code = code;
        this.desc = desc;
        this.channelType = channelType;
    }

    /**
     * Setter method for property channelType.
     *
     * @param channelType value to be assigned to property channelType
     */
    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    /**
     * Getter method for property code.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter method for property code.
     *
     * @param code value to be assigned to property code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Setter method for property desc.
     *
     * @param desc value to be assigned to property desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getChannelType() {
        return channelType;
    }

    public boolean isAlipay() {
        return this.equals(ALIPAY);
    }

    public boolean isWeixin() {
        return this.equals(WEIXIN);
    }

    public static PayClient getByDesc(String desc) {
        if (StringUtils.isBlank(desc)) {
            return null;
        }
        PayClient[] enums = PayClient.values();
        for (PayClient e : enums) {
            if (e.getDesc().equals(desc)) {
                return e;
            }
        }
        return null;
    }
}