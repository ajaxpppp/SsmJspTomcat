package com.study.ssm.entity;

import lombok.Data;
import lombok.ToString;
import java.util.List;

/**
 * 班级实体类 - 对应tb_clazz表
 */
@Data
@ToString(exclude = "students")
public class Clazz {
    private Integer id;
    private String code;
    private String name;
    
    // 关联的学生列表
    private List<Student> students;

    public Clazz() {
    }

    public Clazz(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Clazz(Integer id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
}
