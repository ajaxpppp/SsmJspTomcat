package com.study.ssm.lab.experiment4.dao.impl;

import com.study.ssm.lab.experiment4.dao.CustomerDao;
import org.springframework.stereotype.Repository;

/**
 * 实验4：基于注解的装配 - 数据访问层实现类
 * 使用@Repository注解标注数据访问层组件
 */
@Repository("labCustomerDao")
public class CustomerDaoImpl implements CustomerDao {
    
    public CustomerDaoImpl() {
        System.out.println("【数据层】CustomerDaoImpl对象被创建（通过@Repository注解）");
    }
    
    @Override
    public String findCustomerById(int customerId) {
        System.out.println("【数据层】查询客户，ID: " + customerId);
        return "客户" + customerId;
    }
    
    @Override
    public void saveCustomer(String customerName, String phone) {
        System.out.println("【数据层】保存客户: " + customerName + "，电话: " + phone);
    }
}
