package com.study.ssm.mapper;

import com.study.ssm.entity.XsbStudent;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 学生数据访问接口 - 对应xsb表
 */
public interface XsbStudentMapper {
    
    /**
     * 根据学号查询学生信息
     * @param sno 学号
     * @return 学生对象
     */
    XsbStudent selectStudentBySno(@Param("sno") String sno);
    
    /**
     * 查询所有学生信息
     * @return 学生列表
     */
    List<XsbStudent> selectAllStudents();
    
    /**
     * 插入学生信息
     * @param student 学生对象
     * @return 影响行数
     */
    int insertStudent(XsbStudent student);
    
    /**
     * 更新学生信息
     * @param student 学生对象
     * @return 影响行数
     */
    int updateStudent(XsbStudent student);
    
    /**
     * 根据学号删除学生
     * @param sno 学号
     * @return 影响行数
     */
    int deleteStudent(@Param("sno") String sno);
}
