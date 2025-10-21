package com.study.ssm.test.employee;

import com.study.ssm.entity.Employee;
import com.study.ssm.mapper.EmployeeMapper;
import com.study.ssm.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * 员工动态查询测试类
 */
public class EmployeeDynamicQueryTest {
    
    private SqlSession sqlSession;
    private EmployeeMapper employeeMapper;
    
    @Before
    public void setUp() {
        sqlSession = MyBatisUtil.getSqlSession();
        employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
    }
    
    @After
    public void tearDown() {
        MyBatisUtil.closeSqlSession(sqlSession);
    }
    
    /**
     * 测试根据ID查询
     */
    @Test
    public void testSelectByIdDynamic() {
        System.out.println("=== 测试根据ID查询 ===");
        List<Employee> employees = employeeMapper.
                selectEmployeeDynamic(1, null, null, null, null);
        System.out.println("查询结果数量: " + employees.size());
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
    
    /**
     * 测试根据登录名模糊查询
     */
    @Test
    public void testSelectByLoginnameLike() {
        System.out.println("=== 测试根据登录名模糊查询 ===");
        List<Employee> employees = employeeMapper.selectEmployeeDynamic(null, "emp", null, null, null);
        System.out.println("查询结果数量: " + employees.size());
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
    
    /**
     * 测试根据性别查询
     */
    @Test
    public void testSelectBySex() {
        System.out.println("=== 测试根据性别查询 ===");
        List<Employee> employees = employeeMapper.selectEmployeeDynamic(null, null, "男", null, null);
        System.out.println("查询结果数量: " + employees.size());
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
    
    /**
     * 测试根据年龄大于某个值查询
     */
    @Test
    public void testSelectByAgeGreaterThan() {
        System.out.println("=== 测试根据年龄大于25查询 ===");
        List<Employee> employees = employeeMapper.selectEmployeeDynamic(null, null, null, 25, null);
        System.out.println("查询结果数量: " + employees.size());
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
    
    /**
     * 测试根据电话不为空查询
     */
    @Test
    public void testSelectByPhoneNotNull() {
        System.out.println("=== 测试根据电话不为空查询 ===");
        List<Employee> employees = employeeMapper.selectEmployeeDynamic(null, null, null, null, "1");
        System.out.println("查询结果数量: " + employees.size());
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
    
    /**
     * 测试组合条件查询
     */
    @Test
    public void testSelectByCombinedConditions() {
        System.out.println("=== 测试组合条件查询：性别为男且年龄大于24 ===");
        List<Employee> employees = employeeMapper.selectEmployeeDynamic(null, null, "男", 24, null);
        System.out.println("查询结果数量: " + employees.size());
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
    
    /**
     * 测试所有条件都为空的查询（查询所有）
     */
    @Test
    public void testSelectAll() {
        System.out.println("=== 测试查询所有员工 ===");
        List<Employee> employees = employeeMapper.selectEmployeeDynamic(null, null, null, null, null);
        System.out.println("查询结果数量: " + employees.size());
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
    
    /**
     * 测试复杂组合查询
     */
    @Test
    public void testComplexQuery() {
        System.out.println("=== 测试复杂组合查询：登录名包含'emp'，性别为女，年龄大于20，电话不为空 ===");
        List<Employee> employees = employeeMapper.selectEmployeeDynamic(null, "emp", "女", 20, "1");
        System.out.println("查询结果数量: " + employees.size());
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
}
