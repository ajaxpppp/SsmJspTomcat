package com.study.ssm.service;

import com.study.ssm.entity.XsbStudent;
import java.util.List;

/**
 * 学生服务接口 - 对应xsb表
 */
public interface XsbStudentService {
    
    /**
     * 根据学号查询学生信息
     * @param sno 学号
     * @return 学生对象
     */
    XsbStudent getStudentBySno(String sno);
    
    /**
     * 查询所有学生信息
     * @return 学生列表
     */
    List<XsbStudent> getAllStudents();
    
    /**
     * 添加学生信息
     * @param student 学生对象
     * @return 是否添加成功
     */
    boolean addStudent(XsbStudent student);
    
    /**
     * 更新学生信息
     * @param student 学生对象
     * @return 是否更新成功
     */
    boolean updateStudent(XsbStudent student);
    
    /**
     * 根据学号删除学生
     * @param sno 学号
     * @return 是否删除成功
     */
    boolean deleteStudent(String sno);
}
