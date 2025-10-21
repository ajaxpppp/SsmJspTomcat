package com.study.ssm.service;

import com.study.ssm.entity.Clazz;
import java.util.List;

/**
 * 班级服务接口
 */
public interface ClazzService {
    
    /**
     * 根据ID查询班级信息（包含学生列表）
     * @param id 班级ID
     * @return 班级对象（包含学生列表）
     */
    Clazz getClazzWithStudentsById(Integer id);
    
    /**
     * 查询所有班级信息（包含学生列表）
     * @return 班级列表（包含学生列表）
     */
    List<Clazz> getAllClazzWithStudents();
    
    /**
     * 根据班级编码查询班级信息（包含学生列表）
     * @param code 班级编码
     * @return 班级对象（包含学生列表）
     */
    Clazz getClazzWithStudentsByCode(String code);
    
    /**
     * 根据班级名称查询班级信息（包含学生列表）
     * @param name 班级名称
     * @return 班级对象（包含学生列表）
     */
    Clazz getClazzWithStudentsByName(String name);
    
    /**
     * 添加班级信息
     * @param clazz 班级对象
     * @return 是否添加成功
     */
    boolean addClazz(Clazz clazz);
    
    /**
     * 更新班级信息
     * @param clazz 班级对象
     * @return 是否更新成功
     */
    boolean updateClazz(Clazz clazz);
    
    /**
     * 根据ID删除班级
     * @param id 班级ID
     * @return 是否删除成功
     */
    boolean deleteClazz(Integer id);
}
