package com.study.ssm.test.user;

import com.study.ssm.entity.User;
import com.study.ssm.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * 用户测试类 - MyBatis CRUD操作测试
 */
public class UserTest {

    /**
     * (1) 添加操作测试
     */
    @Test
    public void testInsertUser() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = MyBatisUtil.getSqlSession();
            
            // 创建用户对象（姓名，年龄，性别1-男，部门，备注）
            User user = new User("测试用户", 25, 1, "测试部", "测试用户账号");
            
            // 执行插入操作
            int result = sqlSession.insert("UserMapper.insertUser", user);
            
            // 提交事务
            sqlSession.commit();
            
            System.out.println("添加用户成功，影响行数：" + result);
            System.out.println("添加的用户信息：" + user);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (sqlSession != null) {
                sqlSession.rollback();
            }
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * (2) 更新操作测试
     */
    @Test
    public void testUpdateUser() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = MyBatisUtil.getSqlSession();
            
            // 创建用户对象（需要包含ID）
            User user = new User(1, "张三修改", 30, 2, "人事部", "人事部经理-已修改");
            
            // 执行更新操作
            int result = sqlSession.update("UserMapper.updateUser", user);
            
            // 提交事务
            sqlSession.commit();
            
            System.out.println("更新用户成功，影响行数：" + result);
            System.out.println("更新的用户信息：" + user);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (sqlSession != null) {
                sqlSession.rollback();
            }
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * (3) 删除操作测试
     */
    @Test
    public void testDeleteUser() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = MyBatisUtil.getSqlSession();
            
            // 要删除的用户ID
            int userId = 4;
            
            // 执行删除操作
            int result = sqlSession.delete("UserMapper.deleteUser", userId);
            
            // 提交事务
            sqlSession.commit();
            
            System.out.println("删除用户成功，影响行数：" + result);
            System.out.println("删除的用户ID：" + userId);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (sqlSession != null) {
                sqlSession.rollback();
            }
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * (4) 查询操作测试 - 根据ID查询
     */
    @Test
    public void testSelectUserById() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = MyBatisUtil.getSqlSession();
            
            // 要查询的用户ID
            int userId = 1;
            
            // 执行查询操作
            User user = sqlSession.selectOne("UserMapper.selectUserById", userId);
            
            System.out.println("根据ID查询用户成功");
            System.out.println("查询结果：" + user);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * (4) 查询操作测试 - 查询所有用户
     */
    @Test
    public void testSelectAllUsers() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = MyBatisUtil.getSqlSession();
            
            // 执行查询操作
            List<User> users = sqlSession.selectList("UserMapper.selectAllUsers");
            
            System.out.println("查询所有用户成功，共查询到 " + users.size() + " 条记录");
            for (User user : users) {
                System.out.println(user);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * (4) 查询操作测试 - 根据姓名查询
     */
    @Test
    public void testSelectUserByName() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = MyBatisUtil.getSqlSession();
            
            // 要查询的用户姓名
            String userName = "张三";
            
            // 执行查询操作
            User user = sqlSession.selectOne("UserMapper.selectUserByName", userName);
            
            System.out.println("根据姓名查询用户成功");
            System.out.println("查询结果：" + user);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 综合测试 - 完整的CRUD流程
     */
    @Test
    public void testCRUDFlow() {
        System.out.println("=== 开始CRUD综合测试 ===");
        
        // 1. 添加用户
        System.out.println("\n1. 添加用户测试");
        testInsertUser();
        
        // 2. 查询所有用户
        System.out.println("\n2. 查询所有用户");
        testSelectAllUsers();
        
        // 3. 根据ID查询用户
        System.out.println("\n3. 根据ID查询用户");
        testSelectUserById();
        
        // 4. 更新用户
        System.out.println("\n4. 更新用户测试");
        testUpdateUser();
        
        // 5. 再次查询验证更新结果
        System.out.println("\n5. 验证更新结果");
        testSelectUserById();
        
        System.out.println("\n=== CRUD综合测试完成 ===");
    }
}
