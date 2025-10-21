package com.study.ssm.test.employee;

import com.study.ssm.entity.Employee;
import com.study.ssm.mapper.EmployeeMapper;
import com.study.ssm.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * 员工动态更新测试类
 */
public class EmployeeDynamicUpdateTest {
    
    private SqlSession sqlSession;
    private EmployeeMapper employeeMapper;
    
    @Before
    public void setUp() {
        sqlSession = MyBatisUtil.getSqlSession(true); // 自动提交事务
        employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
    }
    
    @After
    public void tearDown() {
        MyBatisUtil.closeSqlSession(sqlSession);
    }
    
    /**
     * 测试更新单个字段 - 姓名
     */
    @Test
    public void testUpdateSingleField() {
        System.out.println("=== 测试更新单个字段（姓名） ===");
        
        // 查询更新前的数据
        Employee beforeUpdate = employeeMapper.selectEmployeeById(1);
        System.out.println("更新前: " + beforeUpdate);
        
        // 创建更新对象，只设置ID和要更新的字段
        Employee updateEmployee = new Employee();
        updateEmployee.setId(1);
        updateEmployee.setName("张三丰");
        
        // 执行更新
        int result = employeeMapper.updateEmployeeDynamic(updateEmployee);
        System.out.println("更新影响行数: " + result);
        
        // 查询更新后的数据
        Employee afterUpdate = employeeMapper.selectEmployeeById(1);
        System.out.println("更新后: " + afterUpdate);
    }
    
    /**
     * 测试更新多个字段
     */
    @Test
    public void testUpdateMultipleFields() {
        System.out.println("=== 测试更新多个字段 ===");
        
        // 查询更新前的数据
        Employee beforeUpdate = employeeMapper.selectEmployeeById(2);
        System.out.println("更新前: " + beforeUpdate);
        
        // 创建更新对象，设置多个字段
        Employee updateEmployee = new Employee();
        updateEmployee.setId(2);
        updateEmployee.setName("李四四");
        updateEmployee.setAge(24);
        updateEmployee.setSal(new BigDecimal("4800.00"));
        updateEmployee.setState("试用期");
        
        // 执行更新
        int result = employeeMapper.updateEmployeeDynamic(updateEmployee);
        System.out.println("更新影响行数: " + result);
        
        // 查询更新后的数据
        Employee afterUpdate = employeeMapper.selectEmployeeById(2);
        System.out.println("更新后: " + afterUpdate);
    }
    
    /**
     * 测试更新所有字段
     */
    @Test
    public void testUpdateAllFields() {
        System.out.println("=== 测试更新所有字段 ===");
        
        // 查询更新前的数据
        Employee beforeUpdate = employeeMapper.selectEmployeeById(3);
        System.out.println("更新前: " + beforeUpdate);
        
        // 创建更新对象，设置所有字段
        Employee updateEmployee = new Employee();
        updateEmployee.setId(3);
        updateEmployee.setLoginname("emp003_new");
        updateEmployee.setPassword("newpass123");
        updateEmployee.setName("王五五");
        updateEmployee.setSex("男");
        updateEmployee.setAge(29);
        updateEmployee.setPhone("13900139003");
        updateEmployee.setSal(new BigDecimal("6500.00"));
        updateEmployee.setState("正式员工");
        
        // 执行更新
        int result = employeeMapper.updateEmployeeDynamic(updateEmployee);
        System.out.println("更新影响行数: " + result);
        
        // 查询更新后的数据
        Employee afterUpdate = employeeMapper.selectEmployeeById(3);
        System.out.println("更新后: " + afterUpdate);
    }
    
    /**
     * 测试更新薪资字段
     */
    @Test
    public void testUpdateSalary() {
        System.out.println("=== 测试更新薪资字段 ===");
        
        // 查询更新前的数据
        Employee beforeUpdate = employeeMapper.selectEmployeeById(4);
        System.out.println("更新前: " + beforeUpdate);
        
        // 创建更新对象，只更新薪资
        Employee updateEmployee = new Employee();
        updateEmployee.setId(4);
        updateEmployee.setSal(new BigDecimal("4200.00"));
        
        // 执行更新
        int result = employeeMapper.updateEmployeeDynamic(updateEmployee);
        System.out.println("更新影响行数: " + result);
        
        // 查询更新后的数据
        Employee afterUpdate = employeeMapper.selectEmployeeById(4);
        System.out.println("更新后: " + afterUpdate);
    }
    
    /**
     * 测试更新联系方式
     */
    @Test
    public void testUpdateContactInfo() {
        System.out.println("=== 测试更新联系方式 ===");
        
        // 查询更新前的数据
        Employee beforeUpdate = employeeMapper.selectEmployeeById(5);
        System.out.println("更新前: " + beforeUpdate);
        
        // 创建更新对象，更新电话
        Employee updateEmployee = new Employee();
        updateEmployee.setId(5);
        updateEmployee.setPhone("13700137005");
        
        // 执行更新
        int result = employeeMapper.updateEmployeeDynamic(updateEmployee);
        System.out.println("更新影响行数: " + result);
        
        // 查询更新后的数据
        Employee afterUpdate = employeeMapper.selectEmployeeById(5);
        System.out.println("更新后: " + afterUpdate);
    }
    
    /**
     * 测试更新状态字段
     */
    @Test
    public void testUpdateState() {
        System.out.println("=== 测试更新状态字段 ===");
        
        // 查询更新前的数据
        Employee beforeUpdate = employeeMapper.selectEmployeeById(4);
        System.out.println("更新前: " + beforeUpdate);
        
        // 创建更新对象，更新状态
        Employee updateEmployee = new Employee();
        updateEmployee.setId(4);
        updateEmployee.setState("在职");
        
        // 执行更新
        int result = employeeMapper.updateEmployeeDynamic(updateEmployee);
        System.out.println("更新影响行数: " + result);
        
        // 查询更新后的数据
        Employee afterUpdate = employeeMapper.selectEmployeeById(4);
        System.out.println("更新后: " + afterUpdate);
    }
    
    /**
     * 测试批量更新不同员工的不同字段
     */
    @Test
    public void testBatchUpdateDifferentFields() {
        System.out.println("=== 测试批量更新不同员工的不同字段 ===");
        
        // 更新员工1的年龄
        Employee emp1 = new Employee();
        emp1.setId(1);
        emp1.setAge(26);
        int result1 = employeeMapper.updateEmployeeDynamic(emp1);
        System.out.println("更新员工1年龄，影响行数: " + result1);
        
        // 更新员工2的薪资
        Employee emp2 = new Employee();
        emp2.setId(2);
        emp2.setSal(new BigDecimal("5000.00"));
        int result2 = employeeMapper.updateEmployeeDynamic(emp2);
        System.out.println("更新员工2薪资，影响行数: " + result2);
        
        // 更新员工3的电话
        Employee emp3 = new Employee();
        emp3.setId(3);
        emp3.setPhone("13800138888");
        int result3 = employeeMapper.updateEmployeeDynamic(emp3);
        System.out.println("更新员工3电话，影响行数: " + result3);
        
        // 查看更新结果
        System.out.println("更新后的员工信息:");
        System.out.println("员工1: " + employeeMapper.selectEmployeeById(1));
        System.out.println("员工2: " + employeeMapper.selectEmployeeById(2));
        System.out.println("员工3: " + employeeMapper.selectEmployeeById(3));
    }
}
