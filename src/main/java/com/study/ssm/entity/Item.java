package com.study.ssm.entity;

import lombok.Data;

/**
 * 订单明细实体类 - 对应tb_item表
 * 用于关联订单和商品的中间表
 */
@Data
public class Item {
    private Integer orderId;
    private Integer articleId;
    private Integer amount;
    
    // 关联的商品信息
    private Article article;
    
    public Item() {
    }
    
    public Item(Integer orderId, Integer articleId, Integer amount) {
        this.orderId = orderId;
        this.articleId = articleId;
        this.amount = amount;
    }
}
