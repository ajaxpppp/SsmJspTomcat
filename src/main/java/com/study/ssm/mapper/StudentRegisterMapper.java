package com.study.ssm.mapper;

import com.study.ssm.entity.StudentRegister;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentRegisterMapper {

    @Insert("INSERT INTO tb_student_register (student_id, name, class_name, phone, email, birth_date) " +
            "VALUES (#{studentId}, #{name}, #{className}, #{phone}, #{email}, #{birthDate})")
    int insert(StudentRegister s);

    @Select("SELECT student_id, name, class_name, phone, email, birth_date FROM tb_student_register ORDER BY birth_date DESC")
    @Results(id = "StudentRegisterMap", value = {
            @Result(column = "student_id", property = "studentId"),
            @Result(column = "class_name", property = "className"),
            @Result(column = "birth_date", property = "birthDate")
    })
    List<StudentRegister> findAll();
}
