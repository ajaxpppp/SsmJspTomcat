package com.study.ssm.entity;

import lombok.Data;
import lombok.ToString;
import java.util.List;

/**
 * 用户实体类 - 对应tb_user表
 */
@Data
@ToString(exclude = "orders")
public class User {
    private Integer id;
    private String name;
    private String username;
    private String loginname;
    private String password;
    private String sex;
    private Integer age;
    private String phone;
    private String address;
    
    // 一对多关系：一个用户可以有多个订单
    private List<Order> orders;

    public User() {
    }

    public User(String name, String sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public User(Integer id, String name, String sex, Integer age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    
}
