package com.study.ssm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

/**
 * 用户实体类 - 对应tb_user表（HRM人力资源管理系统）
 * 
 * 数据库表结构：
 * id int primary key AUTO_INCREMENT,
 * name varchar(20),           -- 姓名
 * age int(3),                 -- 年龄
 * sex int(1),                 -- 性别：1-男，2-女
 * depart varchar(50),         -- 部门
 * remark varchar(1000)        -- 备注
 */
@Data
@ToString(exclude = "orders")
//@AllArgsConstructor
//@NoArgsConstructor
public class User {
    private Integer id;        // 用户ID
    private String name;       // 姓名
    private Integer age;       // 年龄
    private Integer sex;       // 性别：1-男，2-女
    private String depart;     // 部门
    private String remark;     // 备注
    
    // 以下字段用于其他模块，保留兼容性
    private String username;
    private String loginname;
    private String password;
    private String phone;
    private String address;
    
    // 一对多关系：一个用户可以有多个订单
    private List<Order> orders;

    public User() {
    }

    public User(String name, Integer age, Integer sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public User(String name, Integer age, Integer sex, String depart, String remark) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.depart = depart;
        this.remark = remark;
    }

    public User(Integer id, String name, Integer age, Integer sex, String depart, String remark) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.depart = depart;
        this.remark = remark;
    }

    /**
     * 获取性别文字描述
     */
    public String getSexText() {
        if (sex == null) return "未知";
        return sex == 1 ? "男" : "女";
    }
}
