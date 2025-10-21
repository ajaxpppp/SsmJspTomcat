package com.study.ssm.service;

import com.study.ssm.entity.Student;
import java.util.List;

/**
 * 学生服务接口
 */
public interface StudentService {
    
    /**
     * 根据ID查询学生信息（包含班级信息）
     * @param id 学生ID
     * @return 学生对象（包含班级信息）
     */
    Student getStudentWithClazzById(Integer id);
    
    /**
     * 查询所有学生信息（包含班级信息）
     * @return 学生列表（包含班级信息）
     */
    List<Student> getAllStudentsWithClazz();
    
    /**
     * 根据班级ID查询学生列表（包含班级信息）
     * @param clazzId 班级ID
     * @return 学生列表（包含班级信息）
     */
    List<Student> getStudentsByClazzId(Integer clazzId);
    
    /**
     * 根据学生姓名查询学生信息（包含班级信息）
     * @param name 学生姓名
     * @return 学生对象（包含班级信息）
     */
    Student getStudentWithClazzByName(String name);
    
    /**
     * 添加学生信息
     * @param student 学生对象
     * @return 是否添加成功
     */
    boolean addStudent(Student student);
    
    /**
     * 更新学生信息
     * @param student 学生对象
     * @return 是否更新成功
     */
    boolean updateStudent(Student student);
    
    /**
     * 根据ID删除学生
     * @param id 学生ID
     * @return 是否删除成功
     */
    boolean deleteStudent(Integer id);
}
