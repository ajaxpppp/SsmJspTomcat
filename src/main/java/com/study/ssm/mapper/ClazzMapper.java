package com.study.ssm.mapper;

import com.study.ssm.entity.Clazz;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 班级数据访问接口 - 对应tb_clazz表
 */
public interface ClazzMapper {
    
    /**
     * 根据ID查询班级信息（包含学生列表）
     * @param id 班级ID
     * @return 班级对象（包含学生列表）
     */
    Clazz selectClazzWithStudentsById(@Param("id") Integer id);
    
    /**
     * 查询所有班级信息（包含学生列表）
     * @return 班级列表（包含学生列表）
     */
    List<Clazz> selectAllClazzWithStudents();
    
    /**
     * 根据班级编码查询班级信息（包含学生列表）
     * @param code 班级编码
     * @return 班级对象（包含学生列表）
     */
    Clazz selectClazzWithStudentsByCode(@Param("code") String code);
    
    /**
     * 根据班级名称查询班级信息（包含学生列表）
     * @param name 班级名称
     * @return 班级对象（包含学生列表）
     */
    Clazz selectClazzWithStudentsByName(@Param("name") String name);
    
    /**
     * 插入班级信息
     * @param clazz 班级对象
     * @return 影响行数
     */
    int insertClazz(Clazz clazz);
    
    /**
     * 更新班级信息
     * @param clazz 班级对象
     * @return 影响行数
     */
    int updateClazz(Clazz clazz);
    
    /**
     * 根据ID删除班级
     * @param id 班级ID
     * @return 影响行数
     */
    int deleteClazz(@Param("id") Integer id);
}
