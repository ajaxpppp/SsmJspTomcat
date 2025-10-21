package com.study.ssm.mapper;

import com.study.ssm.entity.Employee;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 员工Mapper接口 - 动态SQL操作
 */
public interface EmployeeMapper {
    
    /**
     * 动态查询员工信息
     * @param id 员工ID
     * @param loginname 登录名（模糊查询）
     * @param sex 性别
     * @param age 年龄（大于某个值）
     * @param phone 电话（不为空查询）
     * @return 员工列表
     */
    List<Employee> selectEmployeeDynamic(@Param("id") Integer id,
                                       @Param("loginname") String loginname,
                                       @Param("sex") String sex,
                                       @Param("age") Integer age,
                                       @Param("phone") String phone);
    
    /**
     * 动态更新员工信息
     * @param employee 员工对象
     * @return 影响行数
     */
    int updateEmployeeDynamic(Employee employee);
    
    /**
     * 根据ID查询员工
     * @param id 员工ID
     * @return 员工对象
     */
    Employee selectEmployeeById(@Param("id") Integer id);
    
    /**
     * 插入员工信息
     * @param employee 员工对象
     * @return 影响行数
     */
    int insertEmployee(Employee employee);
    
    /**
     * 查询所有员工
     * @return 员工列表
     */
    List<Employee> selectAllEmployees();
}
