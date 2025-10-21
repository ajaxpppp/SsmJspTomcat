package com.study.ssm.service.impl;

import com.study.ssm.entity.User;
import com.study.ssm.service.UserService;
import com.study.ssm.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户服务实现类 - 适配tb_user表结构
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(Integer id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtil.getSqlSession();
            return sqlSession.selectOne("UserMapper.selectUserById", id);
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    @Override
    public User getUserByName(String name) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtil.getSqlSession();
            return sqlSession.selectOne("UserMapper.selectUserByName", name);
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    @Override
    public List<User> getAllUsers() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtil.getSqlSession();
            return sqlSession.selectList("UserMapper.selectAllUsers");
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    @Override
    public boolean addUser(User user) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtil.getSqlSession();
            // 检查姓名是否已存在
            if (getUserByName(user.getName()) != null) {
                return false;
            }
            int result = sqlSession.insert("UserMapper.insertUser", user);
            sqlSession.commit();
            return result > 0;
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            return false;
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    @Override
    public boolean updateUser(User user) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtil.getSqlSession();
            int result = sqlSession.update("UserMapper.updateUser", user);
            sqlSession.commit();
            return result > 0;
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            return false;
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    @Override
    public boolean deleteUser(Integer id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtil.getSqlSession();
            int result = sqlSession.delete("UserMapper.deleteUser", id);
            sqlSession.commit();
            return result > 0;
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            return false;
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    @Override
    public int getUserCount() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtil.getSqlSession();
            List<User> users = sqlSession.selectList("UserMapper.selectAllUsers");
            return users.size();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }
}
