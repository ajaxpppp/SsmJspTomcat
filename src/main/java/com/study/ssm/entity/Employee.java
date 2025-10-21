package com.study.ssm.entity;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 员工实体类 - 对应tb_employee表
 */
@Data
public class Employee {
    private Integer id;
    private String loginname;
    private String password;
    private String name;
    private String sex;
    private Integer age;
    private String phone;
    private BigDecimal sal;
    private String state;

    public Employee() {
    }

    public Employee(String loginname, String password, String name, String sex, Integer age, String phone, BigDecimal sal, String state) {
        this.loginname = loginname;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
        this.sal = sal;
        this.state = state;
    }

    public Employee(Integer id, String loginname, String password, String name, String sex, Integer age, String phone, BigDecimal sal, String state) {
        this.id = id;
        this.loginname = loginname;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
        this.sal = sal;
        this.state = state;
    }
}
