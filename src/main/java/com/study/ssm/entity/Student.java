package com.study.ssm.entity;

import lombok.Data;
import lombok.ToString;

/**
 * 学生实体类 - 对应tb_student表
 */
@Data
@ToString(exclude = "clazz")
public class Student {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private Integer clazzId;
    
    // 关联的班级对象
    private Clazz clazz;

    public Student() {
    }

    public Student(String name, String sex, Integer age, Integer clazzId) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.clazzId = clazzId;
    }

    public Student(Integer id, String name, String sex, Integer age, Integer clazzId) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.clazzId = clazzId;
    }
}
