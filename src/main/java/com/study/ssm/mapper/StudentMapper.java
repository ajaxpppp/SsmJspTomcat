package com.study.ssm.mapper;

import com.study.ssm.entity.Student;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 学生数据访问接口 - 对应tb_student表
 */
public interface StudentMapper {
    
    /**
     * 根据ID查询学生信息（包含班级信息）
     * @param id 学生ID
     * @return 学生对象（包含班级信息）
     */
    Student selectStudentWithClazzById(@Param("id") Integer id);
    
    /**
     * 查询所有学生信息（包含班级信息）
     * @return 学生列表（包含班级信息）
     */
    List<Student> selectAllStudentsWithClazz();
    
    /**
     * 根据班级ID查询学生列表（包含班级信息）
     * @param clazzId 班级ID
     * @return 学生列表（包含班级信息）
     */
    List<Student> selectStudentsByClazzId(@Param("clazzId") Integer clazzId);
    
    /**
     * 根据学生姓名查询学生信息（包含班级信息）
     * @param name 学生姓名
     * @return 学生对象（包含班级信息）
     */
    Student selectStudentWithClazzByName(@Param("name") String name);
    
    /**
     * 插入学生信息
     * @param student 学生对象
     * @return 影响行数
     */
    int insertStudent(Student student);
    
    /**
     * 更新学生信息
     * @param student 学生对象
     * @return 影响行数
     */
    int updateStudent(Student student);
    
    /**
     * 根据ID删除学生
     * @param id 学生ID
     * @return 影响行数
     */
    int deleteStudent(@Param("id") Integer id);
}
