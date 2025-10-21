package com.study.ssm.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 学生注册实体类
 * 用于学生注册功能的数据绑定
 */
@Data
public class StudentRegister {
    
    /**
     * 学号
     */
    private String studentId;
    
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 班级
     */
    private String className;
    
    /**
     * 电话
     */
    private String phone;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    
    // 无参构造函数
    public StudentRegister() {
    }
    
    // 全参构造函数
    public StudentRegister(String studentId, String name, String className, 
                          String phone, String email, Date birthDate) {
        this.studentId = studentId;
        this.name = name;
        this.className = className;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
    }
}
