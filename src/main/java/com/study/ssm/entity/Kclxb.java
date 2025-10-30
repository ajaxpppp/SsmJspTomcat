package com.study.ssm.entity;

import lombok.Data;

/**
 * 课程类型实体类 - 对应kclxb表
 */
@Data
public class Kclxb {
    private Integer id;      // 主键ID（自动生成）
    private String lxm;      // 类型名
    private String bz;       // 备注

    public Kclxb() {
    }

    public Kclxb(Integer id, String lxm, String bz) {
        this.id = id;
        this.lxm = lxm;
        this.bz = bz;
    }
}
