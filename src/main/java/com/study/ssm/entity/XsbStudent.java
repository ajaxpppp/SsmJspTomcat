package com.study.ssm.entity;

import lombok.Data;

/**
 * 学生实体类 - 对应xsb表
 */
@Data
public class XsbStudent {
    private String sno;      // 学号（主键）
    private String name;     // 姓名
    private Integer age;     // 年龄
    private String tel;      // 电话
    private String address;  // 地址

    public XsbStudent() {
    }

    public XsbStudent(String sno, String name, Integer age, String tel, String address) {
        this.sno = sno;
        this.name = name;
        this.age = age;
        this.tel = tel;
        this.address = address;
    }
}
