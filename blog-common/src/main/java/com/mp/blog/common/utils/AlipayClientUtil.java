package com.mp.blog.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.mp.blog.common.constant.AliConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 直付通工具类
 *
 * @author duchong
 * @date 2019-2-27 11:40:34
 */
public class AlipayClientUtil {
    private static Logger _log = LoggerFactory.getLogger(AlipayClientUtil.class);

    /**
     * 订单支付
     *
     * @param appAuthToken
     * @param bizContent
     * @return
     * @throws AlipayApiException
     */
    public static AlipayTradePayResponse tradePay(String appAuthToken, String bizContent, AlipayClient alipayClient) {
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        if (DataUtil.isNotEmpty(appAuthToken)) {
            request.putOtherTextParam("app_auth_token", appAuthToken);
        }
        request.setBizContent(bizContent);
        AlipayTradePayResponse response = null;
        try {
            _log.error("支付创建:{}", JSONObject.toJSONString(request));
            response = alipayClient.execute(request);
            _log.error("支付创建结果:{}", JSONObject.toJSONString(response));
        } catch (AlipayApiException e) {
            _log.error("，支付单创建异常：{}", e);
        } catch (Exception e) {
            _log.error("，支付单创建异常：{}", e);
        }
        if (response == null) {
            response = new AlipayTradePayResponse();
            response.setCode(AliConstants.ERROR);
            response.setMsg("请求失败");
            response.setSubCode("SYSTEM_ERROR");
            response.setSubMsg("系统繁忙，稍后再试");
        }
        return response;
    }

    /**
     * 查询
     *
     * @param appAuthToken
     * @param bizContent
     * @return
     * @throws AlipayApiException
     */
    public static AlipayTradeQueryResponse tradeQuery(String appAuthToken, String bizContent, AlipayClient alipayClient) {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        if (DataUtil.isNotEmpty(appAuthToken)) {
            request.putOtherTextParam("app_auth_token", appAuthToken);
        }
        request.setBizContent(bizContent);
        AlipayTradeQueryResponse response = null;
        try {
            _log.error("查询创建:{}", JSONObject.toJSONString(request));
            response = alipayClient.execute(request);
            _log.error("查询创建结果:{}", JSONObject.toJSONString(response));
        } catch (AlipayApiException e) {
            _log.error("，支付单查询异常：{}", e);
        } catch (Exception e) {
            _log.error("，支付单查询异常：{}", e);
        }
        if (response == null) {
            response = new AlipayTradeQueryResponse();
            response.setCode(AliConstants.ERROR);
            response.setMsg("请求失败");
            response.setSubCode("SYSTEM_ERROR");
            response.setSubMsg("系统繁忙，稍后再试");
        }
        return response;
    }

    /**
     * 撤销
     *
     * @param appAuthToken
     * @param bizContent
     * @return
     * @throws AlipayApiException
     */
    public static AlipayTradeCancelResponse tradeCancel(String appAuthToken, String bizContent, AlipayClient alipayClient) {
        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
        if (DataUtil.isNotEmpty(appAuthToken)) {
            request.putOtherTextParam("app_auth_token", appAuthToken);
        }
        request.setBizContent(bizContent);
        AlipayTradeCancelResponse response = null;
        try {
            _log.error("撤销创建:{}", JSONObject.toJSONString(request));
            response = alipayClient.execute(request);
            _log.error("撤销创建结果:{}", JSONObject.toJSONString(response));
        } catch (AlipayApiException e) {
            _log.error("支付单撤销异常：{}", e);
        } catch (Exception e) {
            _log.error("支付单撤销异常：{}", e);
        }
        if (response == null) {
            response = new AlipayTradeCancelResponse();
            response.setCode(AliConstants.ERROR);
            response.setMsg("请求失败");
            response.setSubCode("SYSTEM_ERROR");
            response.setSubMsg("系统繁忙，稍后再试");
        }
        return response;
    }

    /**
     * 退款
     *
     * @param bizContent
     * @return
     * @throws AlipayApiException
     */
    public static AlipayTradeRefundResponse tradeRefund(String appAuthToken, String bizContent, AlipayClient alipayClient) {
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        if (DataUtil.isNotEmpty(appAuthToken)) {
            request.putOtherTextParam("app_auth_token", appAuthToken);
        }
        request.setBizContent(bizContent);
        AlipayTradeRefundResponse response = null;
        try {
            _log.error("退款创建:{}", JSONObject.toJSONString(request));
            response = alipayClient.execute(request);
            _log.error("退款创建结果:{}", JSONObject.toJSONString(response));
        } catch (AlipayApiException e) {
            _log.error("，支付单退款异常：{}", e);
        } catch (Exception e) {
            _log.error("，支付单退款异常：{}", e);
        }
        if (response == null) {
            response = new AlipayTradeRefundResponse();
            response.setCode(AliConstants.ERROR);
            response.setMsg("请求失败");
            response.setSubCode("SYSTEM_ERROR");
            response.setSubMsg("系统繁忙，稍后再试");
        }
        return response;
    }

    /**
     * 退款查询
     *
     * @param appAuthToken
     * @param trade_no       商户订单号
     * @param out_request_no 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
     * @return
     * @throws AlipayApiException
     */
    public static AlipayTradeFastpayRefundQueryResponse tradeRefundQuery(String appAuthToken, String trade_no, String out_request_no, AlipayClient alipayClient) {
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        if (DataUtil.isNotEmpty(appAuthToken)) {
            request.putOtherTextParam("app_auth_token", appAuthToken);
        }
        JSONObject json = new JSONObject();
        json.put("trade_no", trade_no);
        json.put("out_request_no", out_request_no);
        request.setBizContent(json.toJSONString());
        AlipayTradeFastpayRefundQueryResponse response = null;
        try {
            _log.error("退款查询:{}", JSONObject.toJSONString(request));
            response = alipayClient.execute(request);
            _log.error("退款查询结果:{}", JSONObject.toJSONString(response));
        } catch (AlipayApiException e) {
            _log.error("，支付单退款查询异常：{}", e);
        } catch (Exception e) {
            _log.error("，支付单退款查询异常：{}", e);
        }
        if (response == null) {
            response = new AlipayTradeFastpayRefundQueryResponse();
            response.setCode(AliConstants.ERROR);
            response.setMsg("请求失败");
            response.setSubCode("SYSTEM_ERROR");
            response.setSubMsg("系统繁忙，稍后再试");
        }
        return response;
    }

    /**
     * 交易预创建
     *
     * @param appAuthToken
     * @param notify_url   回调地址
     * @return
     * @throws AlipayApiException
     */
    public static AlipayTradePrecreateResponse tradePrecreate(String appAuthToken, String bizContent, String notify_url, AlipayClient alipayClient) {
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        if (DataUtil.isNotEmpty(appAuthToken)) {
            request.putOtherTextParam("app_auth_token", appAuthToken);
        }
        request.setNotifyUrl(notify_url);
        request.setBizContent(bizContent);
        AlipayTradePrecreateResponse response = null;
        try {
            _log.error("预支付创建:{}", JSONObject.toJSONString(request));
            response = alipayClient.execute(request);
            _log.error("预支付创建结果:{}", JSONObject.toJSONString(response));
        } catch (AlipayApiException e) {
            _log.error("预支付交易创建异常:{}", e);
        } catch (Exception e) {
            _log.error("预支付交易创建异常:{}", e);
        }
        if (response == null) {
            response = new AlipayTradePrecreateResponse();
            response.setCode(AliConstants.ERROR);
            response.setMsg("请求失败");
            response.setSubCode("SYSTEM_ERROR");
            response.setSubMsg("系统繁忙，稍后再试");
        }
        return response;
    }

    /**
     * 交易创建
     *
     * @param appAuthToken
     * @param notify_url   回调地址
     * @return
     * @throws AlipayApiException
     */
    public static AlipayTradeCreateResponse tradeCreate(String appAuthToken, String bizContent, String notify_url, AlipayClient alipayClient) {
        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
        if (DataUtil.isNotEmpty(appAuthToken)) {
            request.putOtherTextParam("app_auth_token", appAuthToken);
        }
        if (DataUtil.isNotEmpty(notify_url)) {
            request.setNotifyUrl(notify_url);
        }
        request.setBizContent(bizContent);
        AlipayTradeCreateResponse response = null;
        try {
            _log.error("支付预创建:{}", JSONObject.toJSONString(request));
            response = alipayClient.execute(request);
            _log.error("支付预创建结果:{}", JSONObject.toJSONString(response));
        } catch (AlipayApiException e) {
            _log.error("，支付单预创建异常：{}", e);
        } catch (Exception e) {
            _log.error("，支付单预创建异常：{}", e);
        }
        if (response == null) {
            response = new AlipayTradeCreateResponse();
            response.setCode(AliConstants.ERROR);
            response.setMsg("请求失败");
            response.setSubCode("SYSTEM_ERROR");
            response.setSubMsg("系统繁忙，稍后再试");
        }
        return response;
    }


    public static AlipayTradeCloseResponse tradeClose(String appAuthToken, String bizContent, AlipayClient alipayClient) {
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        if (DataUtil.isNotEmpty(appAuthToken)) {
            request.putOtherTextParam("app_auth_token", appAuthToken);
        }
        request.setBizContent(bizContent);
        AlipayTradeCloseResponse response = null;
        try {
            _log.error("支付关闭:{}", JSONObject.toJSONString(request));
            response = alipayClient.execute(request);
            _log.error("支付关闭结果:{}", JSONObject.toJSONString(response));
        } catch (AlipayApiException e) {
            _log.error("，支付单关闭异常：{}", e);
        } catch (Exception e) {
            _log.error("，支付单关闭异常：{}", e);
        }
        if (response == null) {
            response = new AlipayTradeCloseResponse();
            response.setCode(AliConstants.ERROR);
            response.setMsg("请求失败");
            response.setSubCode("SYSTEM_ERROR");
            response.setSubMsg("系统繁忙，稍后再试");
        }
        return response;
    }

    /**
     * 分账
     */
    public static AlipayTradeOrderSettleResponse royaltyInfo(String biz_content, AlipayClient client) {
        AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
        request.setBizContent(biz_content);
        AlipayTradeOrderSettleResponse response = null;
        try {
            response = client.execute(request);
            return response;
        } catch (AlipayApiException e) {
            _log.error("分账错误:{}", e);
        } catch (Exception e) {
            _log.error("，分账错误：{}", e);
        }
        if (response == null) {
            response = new AlipayTradeOrderSettleResponse();
            response.setCode(AliConstants.ERROR);
            response.setMsg("请求失败");
            response.setSubCode("SYSTEM_ERROR");
            response.setSubMsg("系统繁忙，稍后再试");
        }
        return response;
    }


    /**
     * 基础参数预校验
     *
     * @param biz_content
     * @param client
     * @return
     */
    public static AntMerchantExpandIndirectZftConsultResponse consult(String biz_content, AlipayClient client) {
        AntMerchantExpandIndirectZftConsultRequest request = new AntMerchantExpandIndirectZftConsultRequest();
        request.setBizContent(biz_content);
        AntMerchantExpandIndirectZftConsultResponse response = null;
        try {
            _log.error("商户预校验:{}", JSONObject.toJSONString(request));
            response = client.execute(request);
            _log.error("商户预校验结果:{}", JSONObject.toJSONString(response));
            return response;
        } catch (AlipayApiException e) {
            _log.error("商户预校验错误:{}", e);
        } catch (Exception e) {
            _log.error("商户预校验系统异常：{}", e);
        }
        if (response == null) {
            response = new AntMerchantExpandIndirectZftConsultResponse();
            response.setCode(AliConstants.ERROR);
            response.setMsg("请求失败");
            response.setSubCode("SYSTEM_ERROR");
            response.setSubMsg("系统繁忙，稍后再试");
        }
        return response;
    }

    /**
     * 商户校验
     *
     * @param biz_content
     * @param client
     * @return
     */
    public static AntMerchantExpandIndirectZftCreateResponse create(String biz_content, AlipayClient client) {
        AntMerchantExpandIndirectZftCreateRequest request = new AntMerchantExpandIndirectZftCreateRequest();
        request.setBizContent(biz_content);
        AntMerchantExpandIndirectZftCreateResponse response = null;
        try {
            _log.error("商户创建:{}", JSONObject.toJSONString(request));
            response = client.execute(request);
            _log.error("商户创建结果:{}", JSONObject.toJSONString(response));
            return response;
        } catch (AlipayApiException e) {
            _log.error("商户创建错误:{}", e);
        } catch (Exception e) {
            _log.error("商户创建系统异常：{}", e);
        }
        if (response == null) {
            response = new AntMerchantExpandIndirectZftCreateResponse();
            response.setCode(AliConstants.ERROR);
            response.setMsg("请求失败");
            response.setSubCode("SYSTEM_ERROR");
            response.setSubMsg("系统繁忙，稍后再试");
        }
        return response;
    }


    /**
     * 商户创建结果查询
     *
     * @param biz_content
     * @param client
     * @return
     */
    public static AntMerchantExpandOrderQueryResponse registerQuery(String biz_content, AlipayClient client) {
        AntMerchantExpandOrderQueryRequest request = new AntMerchantExpandOrderQueryRequest();
        request.setBizContent(biz_content);
        AntMerchantExpandOrderQueryResponse response = null;
        try {
            _log.error("商户创建结果查询:{}", JSONObject.toJSONString(request));
            response = client.execute(request);
            _log.error("商户创建结果查询结果:{}", JSONObject.toJSONString(response));
            return response;
        } catch (AlipayApiException e) {
            _log.error("商户创建查询结果错误:{}", e);
        } catch (Exception e) {
            _log.error("商户创建查询结果系统异常：{}", e);
        }
        if (response == null) {
            response = new AntMerchantExpandOrderQueryResponse();
            response.setCode(AliConstants.ERROR);
            response.setMsg("请求失败");
            response.setSubCode("SYSTEM_ERROR");
            response.setSubMsg("系统繁忙，稍后再试");
        }
        return response;
    }


    /**
     * 上传图片
     *
     * @param image  图片完整路径
     * @param client 客户端初始化
     */
    public static AntMerchantExpandIndirectImageUploadResponse upLoadImage(String image, AlipayClient client) {
//        if (image == null) {
//            return null;
//        }
//        AntMerchantExpandIndirectImageUploadResponse response = null;
//        try {
//            AntMerchantExpandIndirectImageUploadRequest request = new AntMerchantExpandIndirectImageUploadRequest();
//            request.setImageType("jpg");
////            request.setImageContent(IoUtils.getFileItem(image));
//            request.setImageContent(IoUtils.getFileItemByInputStream(new ByteArrayInputStream(FileClient.downLoadImageFile(image)),
//                    IoUtils.getFileName(image)));
//            response = client.execute(request);
//        } catch (AlipayApiException ae) {
//            _log.error("上传图片失败:{}", ae);
//        } catch (Exception e) {
//            _log.error("上传图片失败:{}", e);
//        }
//        if (response == null) {
//            response = new AntMerchantExpandIndirectImageUploadResponse();
//            response.setCode(AliConstants.ERROR);
//            response.setMsg("请求失败");
//            response.setSubCode("SYSTEM_ERROR");
//            response.setSubMsg("系统繁忙，稍后再试");
//        }
//        return response;
        return null;
    }

    /**
     * 商户信息修改
     *
     * @param biz_content 图片完整路径
     * @param client      客户端初始化
     */
    public static AntMerchantExpandIndirectZftModifyResponse modify(String biz_content, AlipayClient client) {
        AntMerchantExpandIndirectZftModifyRequest request = new AntMerchantExpandIndirectZftModifyRequest();
        request.setBizContent(biz_content);
        AntMerchantExpandIndirectZftModifyResponse response = null;
        try {
            _log.error("商户信息修改:{}", JSONObject.toJSONString(request));
            response = client.execute(request);
            _log.error("商户信息修改结果:{}", JSONObject.toJSONString(response));
            return response;
        } catch (AlipayApiException e) {
            _log.error("商户信息修改错误:{}", e);
        } catch (Exception e) {
            _log.error("商户信息修改异常：{}", e);
        }
        if (response == null) {
            response = new AntMerchantExpandIndirectZftModifyResponse();
            response.setCode(AliConstants.ERROR);
            response.setMsg("请求失败");
            response.setSubCode("SYSTEM_ERROR");
            response.setSubMsg("系统繁忙，稍后再试");
        }
        return response;
    }


    /**
     * 红包模板创建
     *
     * @param client
     * @param biz_content
     */
    public static AlipayMarketingCouponTemplateCreateResponse coupon(String biz_content, AlipayClient client) {
        AlipayMarketingCouponTemplateCreateRequest request = new AlipayMarketingCouponTemplateCreateRequest();
        request.setBizContent(biz_content);
        AlipayMarketingCouponTemplateCreateResponse response = null;
        try {
            _log.error("创建红包模板:{}", JSONObject.toJSONString(request));
            response = client.execute(request);
            _log.error("创建红包模板结果:{}", JSONObject.toJSONString(response));
            return response;
        } catch (AlipayApiException e) {
            _log.error("创建红包模板错误:{}", e);
        } catch (Exception e) {
            _log.error("创建红包模板异常：{}", e);
        }
        if (response == null) {
            response = new AlipayMarketingCouponTemplateCreateResponse();
            response.setCode(AliConstants.ERROR);
            response.setMsg("请求失败");
            response.setSubCode("SYSTEM_ERROR");
            response.setSubMsg("系统繁忙，稍后再试");
        }
        return response;
    }


    /**
     * 发放红包
     *
     * @param client
     * @param biz_content
     */
    public static AlipayMarketingVoucherSendResponse ticket(String biz_content, AlipayClient client) {
        AlipayMarketingVoucherSendRequest request = new AlipayMarketingVoucherSendRequest();
        request.setBizContent(biz_content);
        AlipayMarketingVoucherSendResponse response = null;
        try {
            _log.error("发券:{}", JSONObject.toJSONString(request));
            response = client.execute(request);
            _log.error("发券结果:{}", JSONObject.toJSONString(response));
            return response;
        } catch (AlipayApiException e) {
            _log.error("发券错误:{}", e);
        } catch (Exception e) {
            _log.error("发券异常：{}", e);
        }
        if (response == null) {
            response = new AlipayMarketingVoucherSendResponse();
            response.setCode(AliConstants.ERROR);
            response.setMsg("请求失败");
            response.setSubCode("SYSTEM_ERROR");
            response.setSubMsg("系统繁忙，稍后再试");
        }
        return response;
    }
}
