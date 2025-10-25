package com.study.ssm.lab.experiment2.dao.impl;

import com.study.ssm.lab.experiment2.dao.ProductDao;

/**
 * 实验2：Setter注入 - 数据访问层实现类
 */
public class ProductDaoImpl implements ProductDao {
    
    // 数据库连接信息（通过Setter注入）
    private String databaseUrl;
    private String databaseUser;
    
    public ProductDaoImpl() {
        System.out.println("【数据层】ProductDaoImpl对象被创建");
    }
    
    // Setter方法 - 注入数据库URL
    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
        System.out.println("【数据层】通过Setter注入数据库URL: " + databaseUrl);
    }
    
    // Setter方法 - 注入数据库用户名
    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser;
        System.out.println("【数据层】通过Setter注入数据库用户: " + databaseUser);
    }
    
    @Override
    public String findProductById(int productId) {
        System.out.println("【数据层】连接到 " + databaseUrl + "，用户: " + databaseUser);
        System.out.println("【数据层】查询商品，ID: " + productId);
        return "商品" + productId;
    }
    
    @Override
    public void addProduct(String productName, double price) {
        System.out.println("【数据层】连接到 " + databaseUrl + "，用户: " + databaseUser);
        System.out.println("【数据层】添加商品: " + productName + "，价格: " + price);
    }
}
