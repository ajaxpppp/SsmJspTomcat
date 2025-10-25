package com.study.ssm.lab.experiment3.service.impl;

import com.study.ssm.lab.experiment3.dao.OrderDao;
import com.study.ssm.lab.experiment3.service.OrderService;

/**
 * 实验3：构造注入 - 业务层实现类
 * 演示：通过构造函数注入依赖对象和配置参数
 */
public class OrderServiceImpl implements OrderService {
    
    // 依赖的DAO对象（通过构造函数注入）
    private OrderDao orderDao;
    
    // 业务配置参数（通过构造函数注入）
    private String companyName;
    private double taxRate;
    private boolean enableAudit;
    
    // 构造函数 - 注入所有依赖
    public OrderServiceImpl(OrderDao orderDao, String companyName, 
                           double taxRate, boolean enableAudit) {
        this.orderDao = orderDao;
        this.companyName = companyName;
        this.taxRate = taxRate;
        this.enableAudit = enableAudit;
        
        System.out.println("【业务层】OrderServiceImpl对象被创建");
        System.out.println("【业务层】通过构造函数注入参数：");
        System.out.println("  - OrderDao对象: " + orderDao.getClass().getSimpleName());
        System.out.println("  - 公司名称: " + companyName);
        System.out.println("  - 税率: " + (taxRate * 100) + "%");
        System.out.println("  - 启用审核: " + enableAudit);
    }
    
    @Override
    public String getOrderInfo(int orderId) {
        System.out.println("【业务层】" + companyName + " - 查询订单信息");
        return orderDao.findOrderById(orderId);
    }
    
    @Override
    public void submitOrder(String orderNo, double amount) {
        System.out.println("【业务层】" + companyName + " - 提交订单");
        
        // 计算含税金额
        double totalAmount = amount * (1 + taxRate);
        System.out.println("【业务层】原始金额: " + amount);
        System.out.println("【业务层】税率: " + (taxRate * 100) + "%");
        System.out.println("【业务层】含税总额: " + totalAmount);
        
        // 审核流程
        if (enableAudit) {
            System.out.println("【业务层】订单需要审核");
        } else {
            System.out.println("【业务层】订单无需审核，直接提交");
        }
        
        orderDao.createOrder(orderNo, totalAmount);
    }
}
