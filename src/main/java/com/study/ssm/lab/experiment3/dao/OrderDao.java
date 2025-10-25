package com.study.ssm.lab.experiment3.dao;

/**
 * 实验3：构造注入 - 数据访问层接口
 */
public interface OrderDao {
    /**
     * 根据订单ID查询订单信息
     */
    String findOrderById(int orderId);
    
    /**
     * 创建订单
     */
    void createOrder(String orderNo, double amount);
}
