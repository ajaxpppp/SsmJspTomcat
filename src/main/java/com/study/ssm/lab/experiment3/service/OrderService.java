package com.study.ssm.lab.experiment3.service;

/**
 * 实验3：构造注入 - 业务层接口
 */
public interface OrderService {
    /**
     * 获取订单信息
     */
    String getOrderInfo(int orderId);
    
    /**
     * 提交订单
     */
    void submitOrder(String orderNo, double amount);
}
