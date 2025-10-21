package com.study.ssm.test.user;

import com.study.ssm.entity.User;
import com.study.ssm.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * (2) 更新操作测试类
 */
public class UpdateUserTest {

    @Test
    public void testUpdateUser() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = MyBatisUtil.getSqlSession();
            
            // 创建用户对象（需要包含ID）
            User user = new User(8, "沈俊123", "女", 35);
            
            // 执行更新操作
            int result = sqlSession.update("UserMapper.updateUser", user);
            
            // 提交事务
            sqlSession.commit();
            
            System.out.println("更新用户成功，影响行数：" + result);
            System.out.println("更新的用户信息：" + user);
            
        } catch (Exception e) {
            System.err.println("更新用户失败：" + e.getMessage());
            e.printStackTrace();
            if (sqlSession != null) {
                sqlSession.rollback();
            }
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }
}
