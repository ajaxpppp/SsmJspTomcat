package com.study.ssm.entity;

import lombok.Data;

/**
 * 图书分类实体类 - 对应catalog表
 */
@Data
public class Catalog {
    private Integer catalogid;      // 主键ID（自动生成）
    private String catalogname;     // 分类名称

    public Catalog() {
    }

    public Catalog(Integer catalogid, String catalogname) {
        this.catalogid = catalogid;
        this.catalogname = catalogname;
    }
}
