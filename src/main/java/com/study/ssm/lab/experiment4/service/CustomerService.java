package com.study.ssm.lab.experiment4.service;

/**
 * 实验4：基于注解的装配 - 业务层接口
 */
public interface CustomerService {
    /**
     * 获取客户信息
     */
    String getCustomerInfo(int customerId);
    
    /**
     * 注册客户
     */
    void registerCustomer(String customerName, String phone);
    
    /**
     * 显示服务配置信息
     */
    void showServiceInfo();
}
