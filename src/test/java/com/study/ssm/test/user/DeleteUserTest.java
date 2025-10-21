package com.study.ssm.test.user;

import com.study.ssm.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * (3) 删除操作测试类
 */
public class DeleteUserTest {

    @Test
    public void testDeleteUser() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = MyBatisUtil.getSqlSession();
            
            // 要删除的用户ID
            int userId = 2;
            
            // 执行删除操作
            int result = sqlSession.delete("UserMapper.deleteUser", userId);
            
            // 提交事务
            sqlSession.commit();
            
            System.out.println("删除用户成功，影响行数：" + result);
            System.out.println("删除的用户ID：" + userId);
            
        } catch (Exception e) {
            System.err.println("删除用户失败：" + e.getMessage());
            e.printStackTrace();
            if (sqlSession != null) {
                sqlSession.rollback();
            }
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }
}
