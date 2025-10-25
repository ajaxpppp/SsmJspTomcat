package com.study.ssm.lab.experiment1.service.impl;

import com.study.ssm.lab.experiment1.dao.UserDao;
import com.study.ssm.lab.experiment1.service.UserService;

/**
 * 实验1：Spring简单应用 - 业务层实现类
 */
public class UserServiceImpl implements UserService {
    
    // 依赖数据层
    private UserDao userDao;
    
    public UserServiceImpl() {
        System.out.println("【业务层】UserServiceImpl对象被创建");
    }
    
    // Setter方法，供Spring注入依赖
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
        System.out.println("【业务层】UserDao依赖注入成功");
    }
    
    @Override
    public String getUserInfo(int userId) {
        System.out.println("【业务层】调用数据层查询用户信息");
        return userDao.findUserNameById(userId);
    }
    
    @Override
    public void registerUser(String userName) {
        System.out.println("【业务层】调用数据层保存用户");
        userDao.saveUser(userName);
    }
}
