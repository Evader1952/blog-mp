package com.mp.blog.common.queue.contents;

public class TradeContents {

    /**
     * 处理订单支付结果结果队列
     */
    public static final String TRADE_RESULT_QUEUE = "trade_result_queue";

    /**
     * 处理预授权网商垫资结果队列
     */
    public static final String TRADE_LOAN_STATUS_RESULT_QUEUE = "trade_loan_status_result_queue";

    /**
     * 处理退货支付结果队列
     */
    public static final String REJECT_PAY_TRADE_RESULT_QUEUE = "reject_pay_trade_result_queue";

    /**
     * 交易成功创建还款计划队列
     */
    public static final String TRADE_REPAYMENT_QUEUE = "trade_repayment_queue";

    /**
     * 红包发放队列表
     */
    public static final String RED_PACKET_PROVIDE_QUEUE = "red.packet.provide.queue";

    /**
     * 订单支付成功处理合同
     */
    public static final String TRADE_PAY_CONTRACT_QUEUE = "trade_pay_contract_queue";

    /**
     * 订单支付成功修改Trade authNo
     */
    public static final String TRADE_PAY_UPDATE_AUTHNO_QUEUE = "trade_pay_update_authno_queue";

    /**
     * 取消网商打款
     * */
    public final static String ORDER_SETTLE_CANCEL = "order.settle.cancel";
}
