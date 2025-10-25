package com.study.ssm.lab.experiment2.service;

/**
 * 实验2：Setter注入 - 业务层接口
 */
public interface ProductService {
    /**
     * 获取商品信息
     */
    String getProductInfo(int productId);
    
    /**
     * 创建新商品
     */
    void createProduct(String productName, double price);
}
