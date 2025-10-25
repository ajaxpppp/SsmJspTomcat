package com.study.ssm.lab.experiment2.service.impl;

import com.study.ssm.lab.experiment2.dao.ProductDao;
import com.study.ssm.lab.experiment2.service.ProductService;

/**
 * 实验2：Setter注入 - 业务层实现类
 * 演示：通过Setter方法注入多个依赖（对象依赖和基本类型属性）
 */
public class ProductServiceImpl implements ProductService {
    
    // 依赖的DAO对象（通过Setter注入）
    private ProductDao productDao;
    
    // 业务配置属性（通过Setter注入）
    private String serviceName;
    private int maxRetryTimes;
    private double discountRate;
    
    public ProductServiceImpl() {
        System.out.println("【业务层】ProductServiceImpl对象被创建");
    }
    
    // Setter方法 - 注入DAO对象
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
        System.out.println("【业务层】通过Setter注入ProductDao对象");
    }
    
    // Setter方法 - 注入服务名称
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
        System.out.println("【业务层】通过Setter注入服务名称: " + serviceName);
    }
    
    // Setter方法 - 注入最大重试次数
    public void setMaxRetryTimes(int maxRetryTimes) {
        this.maxRetryTimes = maxRetryTimes;
        System.out.println("【业务层】通过Setter注入最大重试次数: " + maxRetryTimes);
    }
    
    // Setter方法 - 注入折扣率
    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
        System.out.println("【业务层】通过Setter注入折扣率: " + discountRate);
    }
    
    @Override
    public String getProductInfo(int productId) {
        System.out.println("【业务层】" + serviceName + " - 查询商品信息");
        return productDao.findProductById(productId);
    }
    
    @Override
    public void createProduct(String productName, double price) {
        System.out.println("【业务层】" + serviceName + " - 创建商品");
        System.out.println("【业务层】最大重试次数: " + maxRetryTimes);
        double finalPrice = price * (1 - discountRate);
        System.out.println("【业务层】应用折扣后价格: " + finalPrice);
        productDao.addProduct(productName, finalPrice);
    }
}
