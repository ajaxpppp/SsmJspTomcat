package com.study.ssm.hrm;

import com.study.ssm.entity.User;
import com.study.ssm.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * HRM人力资源管理系统 - SSM框架整合测试
 * 测试用户的增删改查功能
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class HrmUserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSSMIntegration() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║    HRM人力资源管理系统 - SSM框架整合实验                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();

        // 1. 测试查询所有用户
        System.out.println("【步骤1】查询所有用户");
        System.out.println("----------------------------------------------------------");
        List<User> users = userService.getAllUsers();
        System.out.println(">>> 当前用户总数：" + users.size());
        for (User user : users) {
            System.out.printf("    ID=%d, 姓名=%s, 年龄=%d, 性别=%s, 部门=%s%n",
                    user.getId(), user.getName(), user.getAge(), 
                    user.getSexText(), user.getDepart());
        }
        System.out.println();

        // 2. 测试添加用户
        System.out.println("【步骤2】添加新用户");
        System.out.println("----------------------------------------------------------");
        User newUser = new User("王五", 28, 1, "技术部", "技术部经理");
        boolean addResult = userService.addUser(newUser);
        if (addResult) {
            System.out.println(">>> 添加用户成功！ID=" + newUser.getId());
        } else {
            System.out.println(">>> 添加用户失败（可能姓名已存在）");
        }
        System.out.println();

        // 3. 测试根据ID查询用户
        System.out.println("【步骤3】根据ID查询用户");
        System.out.println("----------------------------------------------------------");
        if (addResult && newUser.getId() != null) {
            User foundUser = userService.getUserById(newUser.getId());
            if (foundUser != null) {
                System.out.println(">>> 查询成功：" + foundUser);
            } else {
                System.out.println(">>> 用户不存在");
            }
        } else {
            System.out.println(">>> 跳过此步骤（因添加失败）");
        }
        System.out.println();

        // 4. 测试更新用户
        System.out.println("【步骤4】更新用户信息");
        System.out.println("----------------------------------------------------------");
        if (addResult && newUser.getId() != null) {
            newUser.setAge(30);
            newUser.setDepart("研发部");
            newUser.setRemark("研发部技术总监");
            boolean updateResult = userService.updateUser(newUser);
            if (updateResult) {
                System.out.println(">>> 更新用户成功");
                User updatedUser = userService.getUserById(newUser.getId());
                System.out.println(">>> 更新后：" + updatedUser);
            } else {
                System.out.println(">>> 更新用户失败");
            }
        } else {
            System.out.println(">>> 跳过此步骤（因添加失败）");
        }
        System.out.println();

        // 5. 测试根据姓名查询用户
        System.out.println("【步骤5】根据姓名查询用户");
        System.out.println("----------------------------------------------------------");
        User userByName = userService.getUserByName("张三");
        if (userByName != null) {
            System.out.println(">>> 查询成功：" + userByName);
        } else {
            System.out.println(">>> 用户'张三'不存在");
        }
        System.out.println();

        // 6. 测试删除用户（清理测试数据）
        System.out.println("【步骤6】删除测试用户");
        System.out.println("----------------------------------------------------------");
        if (addResult && newUser.getId() != null) {
            boolean deleteResult = userService.deleteUser(newUser.getId());
            if (deleteResult) {
                System.out.println(">>> 删除用户成功");
            } else {
                System.out.println(">>> 删除用户失败");
            }
        } else {
            System.out.println(">>> 跳过此步骤（因添加失败）");
        }
        System.out.println();

        // 7. 再次查询所有用户
        System.out.println("【步骤7】再次查询所有用户");
        System.out.println("----------------------------------------------------------");
        List<User> finalUsers = userService.getAllUsers();
        System.out.println(">>> 最终用户总数：" + finalUsers.size());
        System.out.println();

        // 实验总结
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║    实验总结                                               ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║  ✓ Spring 容器管理：Service层通过@Autowired注入Mapper     ║");
        System.out.println("║  ✓ SpringMVC 控制层：Controller处理HTTP请求               ║");
        System.out.println("║  ✓ MyBatis 数据层：Mapper接口映射数据库操作               ║");
        System.out.println("║  ✓ 事务管理：@Transactional声明式事务                     ║");
        System.out.println("║  ✓ CRUD功能：实现用户的增删改查                           ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }

    @Test
    public void testQueryAllUsers() {
        System.out.println("========== 测试：查询所有用户 ==========");
        List<User> users = userService.getAllUsers();
        System.out.println("用户总数：" + users.size());
        users.forEach(user -> {
            System.out.printf("ID=%d, 姓名=%s, 年龄=%d, 性别=%s, 部门=%s, 备注=%s%n",
                    user.getId(), user.getName(), user.getAge(),
                    user.getSexText(), user.getDepart(), user.getRemark());
        });
    }

    @Test
    public void testAddUser() {
        System.out.println("========== 测试：添加用户 ==========");
        User user = new User("赵六", 35, 2, "市场部", "市场部主管");
        boolean result = userService.addUser(user);
        System.out.println("添加结果：" + (result ? "成功" : "失败"));
        if (result) {
            System.out.println("新用户ID：" + user.getId());
        }
    }

    @Test
    public void testUpdateUser() {
        System.out.println("========== 测试：更新用户 ==========");
        // 先查询第一个用户
        List<User> users = userService.getAllUsers();
        if (!users.isEmpty()) {
            User user = users.get(0);
            System.out.println("原用户信息：" + user);
            
            // 修改信息
            user.setAge(user.getAge() + 1);
            user.setRemark("更新测试 - " + System.currentTimeMillis());
            
            boolean result = userService.updateUser(user);
            System.out.println("更新结果：" + (result ? "成功" : "失败"));
            
            if (result) {
                User updatedUser = userService.getUserById(user.getId());
                System.out.println("新用户信息：" + updatedUser);
            }
        } else {
            System.out.println("没有用户数据可供更新");
        }
    }

    @Test
    public void testDeleteUser() {
        System.out.println("========== 测试：删除用户 ==========");
        // 先添加一个测试用户
        User user = new User("测试删除", 25, 1, "测试部", "测试删除功能");
        boolean addResult = userService.addUser(user);
        
        if (addResult) {
            System.out.println("创建测试用户成功，ID=" + user.getId());
            
            // 删除该用户
            boolean deleteResult = userService.deleteUser(user.getId());
            System.out.println("删除结果：" + (deleteResult ? "成功" : "失败"));
        } else {
            System.out.println("创建测试用户失败");
        }
    }

    @Test
    public void testGetUserById() {
        System.out.println("========== 测试：根据ID查询用户 ==========");
        List<User> users = userService.getAllUsers();
        if (!users.isEmpty()) {
            Integer userId = users.get(0).getId();
            User user = userService.getUserById(userId);
            System.out.println("查询结果：" + user);
        } else {
            System.out.println("没有用户数据");
        }
    }

    @Test
    public void testGetUserByName() {
        System.out.println("========== 测试：根据姓名查询用户 ==========");
        User user = userService.getUserByName("张三");
        if (user != null) {
            System.out.println("查询结果：" + user);
        } else {
            System.out.println("未找到姓名为'张三'的用户");
        }
    }
}
