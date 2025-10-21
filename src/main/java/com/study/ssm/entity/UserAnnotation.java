package com.study.ssm.entity;

import lombok.Data;
import lombok.ToString;
import java.util.List;

/**
 * 用户实体类（注解版本） - 对应tb_user表
 * 属性名符合新要求：id、username、password、phone、address
 */
@Data
@ToString(exclude = "orders")
public class UserAnnotation {
    private Integer id;
    private String username;    // 对应数据库的username字段
    private String password;    // 对应数据库的password字段
    private String phone;       // 对应数据库的phone字段
    private String address;     // 对应数据库的address字段
    
    // 一对多关系：一个用户可以有多个订单
    private List<Order> orders;
    
    public UserAnnotation() {
    }
    
    public UserAnnotation(String username, String password, String phone, String address) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }
    
    public UserAnnotation(Integer id, String username, String password, String phone, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }
}
