package com.study.ssm.lab.experiment1.dao.impl;

import com.study.ssm.lab.experiment1.dao.UserDao;

/**
 * 实验1：Spring简单应用 - 数据访问层实现类
 */
public class UserDaoImpl implements UserDao {
    
    public UserDaoImpl() {
        System.out.println("【数据层】UserDaoImpl对象被创建");
    }
    
    @Override
    public String findUserNameById(int userId) {
        System.out.println("【数据层】执行查询操作，用户ID: " + userId);
        // 模拟数据库查询
        return "用户" + userId;
    }
    
    @Override
    public void saveUser(String userName) {
        System.out.println("【数据层】保存用户: " + userName);
        // 模拟数据库保存操作
    }
}
