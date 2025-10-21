package com.study.ssm.entity;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 商品实体类 - 对应tb_article表
 */
@Data
public class Article {
    private Integer id;
    private String name;
    private BigDecimal price;
    private String remark;
    
    public Article() {
    }
    
    public Article(String name, BigDecimal price, String remark) {
        this.name = name;
        this.price = price;
        this.remark = remark;
    }
}
