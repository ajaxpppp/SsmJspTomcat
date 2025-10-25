package com.study.ssm.lab.experiment2.dao;

/**
 * 实验2：Setter注入 - 数据访问层接口
 */
public interface ProductDao {
    /**
     * 根据商品ID查询商品名称
     */
    String findProductById(int productId);
    
    /**
     * 添加商品
     */
    void addProduct(String productName, double price);
}
