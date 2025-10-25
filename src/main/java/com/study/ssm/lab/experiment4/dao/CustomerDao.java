package com.study.ssm.lab.experiment4.dao;

/**
 * 实验4：基于注解的装配 - 数据访问层接口
 */
public interface CustomerDao {
    /**
     * 根据客户ID查询客户名称
     */
    String findCustomerById(int customerId);
    
    /**
     * 保存客户信息
     */
    void saveCustomer(String customerName, String phone);
}
