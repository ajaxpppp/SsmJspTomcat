package com.study.ssm.lab.experiment4.service.impl;

import com.study.ssm.lab.experiment4.dao.CustomerDao;
import com.study.ssm.lab.experiment4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 实验4：基于注解的装配 - 业务层实现类
 * 演示多种注解的使用：@Service、@Autowired、@Value
 */
@Service("labCustomerService")
public class CustomerServiceImpl implements CustomerService {
    
    // 使用@Autowired注解自动装配DAO对象
    @Autowired
    private CustomerDao customerDao;
    
    // 使用@Value注解注入配置值（支持默认值）
    @Value("${lab.service.name:客户管理服务}")
    private String serviceName;
    
    @Value("${lab.service.version:1.0}")
    private String version;
    
    @Value("${lab.service.maxConnections:100}")
    private int maxConnections;
    
    @Value("${lab.service.enableCache:true}")
    private boolean enableCache;
    
    public CustomerServiceImpl() {
        System.out.println("【业务层】CustomerServiceImpl对象被创建（通过@Service注解）");
    }
    
    @Override
    public String getCustomerInfo(int customerId) {
        System.out.println("【业务层】" + serviceName + " v" + version + " - 查询客户信息");
        if (enableCache) {
            System.out.println("【业务层】缓存已启用，先查询缓存");
        }
        return customerDao.findCustomerById(customerId);
    }
    
    @Override
    public void registerCustomer(String customerName, String phone) {
        System.out.println("【业务层】" + serviceName + " v" + version + " - 注册客户");
        System.out.println("【业务层】当前最大连接数: " + maxConnections);
        customerDao.saveCustomer(customerName, phone);
    }
    
    @Override
    public void showServiceInfo() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║        服务配置信息                     ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ 服务名称: " + serviceName);
        System.out.println("║ 版本号: " + version);
        System.out.println("║ 最大连接数: " + maxConnections);
        System.out.println("║ 启用缓存: " + (enableCache ? "是" : "否"));
        System.out.println("║ DAO对象: " + customerDao.getClass().getSimpleName());
        System.out.println("╚════════════════════════════════════════╝");
    }
}
