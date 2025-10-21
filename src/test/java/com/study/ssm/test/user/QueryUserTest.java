package com.study.ssm.test.user;

import com.study.ssm.entity.User;
import com.study.ssm.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * (4) 查询操作测试类
 */
public class QueryUserTest {

    @Test
    public void testSelectUserById() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = MyBatisUtil.getSqlSession();
            
            // 要查询的用户ID
            int userId = 8;
            
            // 执行查询操作
            User user = sqlSession.selectOne("UserMapper.selectUserById", userId);
            
            System.out.println("根据ID查询用户成功");
            System.out.println("查询结果：" + user);
            
        } catch (Exception e) {
            System.err.println("根据ID查询用户失败：" + e.getMessage());
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

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
            System.err.println("查询所有用户失败：" + e.getMessage());
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

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
            System.err.println("根据姓名查询用户失败：" + e.getMessage());
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }
}
