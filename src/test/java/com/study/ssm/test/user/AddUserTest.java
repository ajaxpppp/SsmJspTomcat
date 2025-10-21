package com.study.ssm.test.user;

import com.study.ssm.entity.User;
import com.study.ssm.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Random;

/**
 * (1) 添加操作测试类
 */
public class AddUserTest {

    @Test
    public void testAddUser() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = MyBatisUtil.getSqlSession();
            Random random = new Random();
            String name = String.valueOf(random.nextInt(100));
            // 创建用户对象
            User user = new User("新用户"+name, "女", 26);
            
            // 执行插入操作
            int result = sqlSession.insert("UserMapper.insertUser", user);
            
            // 提交事务
            sqlSession.commit();
            
            System.out.println("添加用户成功，影响行数：" + result);
            System.out.println("添加的用户信息：" + user);
            
        } catch (Exception e) {
            System.err.println("添加用户失败：" + e.getMessage());
            e.printStackTrace();
            if (sqlSession != null) {
                sqlSession.rollback();
            }
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }
}
