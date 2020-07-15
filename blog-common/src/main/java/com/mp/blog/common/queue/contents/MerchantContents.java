package com.mp.blog.common.queue.contents;

public class MerchantContents {

    /**
     * 发送商户/门店签约结果通知
     */
    public static final String SEND_MERCHANT_SIGNING_RESULT = "send_merchant_signing_result";

    /**
     * 处理商户审核结果队列
     */
    public static final String VERIFY_MERCHANT_QUEUE = "verify_merchant_queue";

    /**
     * 支付结果通知接口
     */
    public static final String SEND_PAY_ORDER_RESULT_QUEUE = "send_pay_order_result_queue";

    /**
     * 退货/提前结清结果通知接口（预授权）
     */
    public static final String SEND_RETURN_GOODS_RESULT = "send_return_goods_result";

    /**
     * 用户主动扣款结果通知
     */
    public static final String TRADE_DEDUCTION_QUEUE = "trade_deduction_queue";

    /**
     * 还款（提前结清转支付）结果通知接口
     */
    public static final String TRADE_PREPAYMENT_QUEUE = "trade_prepayment_queue";

    /**
     * 解冻订单
     */
    public static final String TRADE_UNFREEZE_QUEUE = "trade_unfreeze_queue";

    /**
     * 解冻商户退货金额
     */
    public static final String TRADE_UNFREEZE_REFUND_QUEUE = "trade_reject_refund_queue";
}
