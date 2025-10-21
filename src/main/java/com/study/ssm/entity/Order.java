package com.study.ssm.entity;

import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单实体类 - 对应tb_order表
 */
@Data
@ToString(exclude = {"user", "items"})
public class Order {
    private Integer id;
    private String code;
    private BigDecimal total;
    private Integer userId;
    
    // 多对一关系：多个订单属于一个用户（懒加载）
    private User user;
    
    // 多对多关系：一个订单包含多个商品（通过tb_item关联）（懒加载）
    private List<Item> items;
    
    public Order() {
    }
    
    public Order(String code, BigDecimal total, Integer userId) {
        this.code = code;
        this.total = total;
        this.userId = userId;
    }
}
