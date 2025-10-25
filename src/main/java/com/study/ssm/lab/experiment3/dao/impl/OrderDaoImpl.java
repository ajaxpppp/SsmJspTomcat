package com.study.ssm.lab.experiment3.dao.impl;

import com.study.ssm.lab.experiment3.dao.OrderDao;

/**
 * 实验3：构造注入 - 数据访问层实现类
 */
public class OrderDaoImpl implements OrderDao {
    
    // 数据库配置信息（通过构造函数注入）
    private String dbUrl;
    private String dbUsername;
    private int dbPort;
    
    // 构造函数 - 注入数据库配置参数
    public OrderDaoImpl(String dbUrl, String dbUsername, int dbPort) {
        this.dbUrl = dbUrl;
        this.dbUsername = dbUsername;
        this.dbPort = dbPort;
        System.out.println("【数据层】OrderDaoImpl对象被创建");
        System.out.println("【数据层】通过构造函数注入参数：");
        System.out.println("  - 数据库URL: " + dbUrl);
        System.out.println("  - 数据库用户: " + dbUsername);
        System.out.println("  - 数据库端口: " + dbPort);
    }
    
    @Override
    public String findOrderById(int orderId) {
        System.out.println("【数据层】用户 " + dbUsername + " 从 " + dbUrl + ":" + dbPort + " 查询订单，ID: " + orderId);
        return "订单" + orderId;
    }
    
    @Override
    public void createOrder(String orderNo, double amount) {
        System.out.println("【数据层】用户 " + dbUsername + " 向 " + dbUrl + ":" + dbPort + " 创建订单");
        System.out.println("【数据层】订单号: " + orderNo + "，金额: " + amount);
    }
}
