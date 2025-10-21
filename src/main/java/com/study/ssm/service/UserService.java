package com.study.ssm.service;

import com.study.ssm.entity.User;

import java.util.List;

/**
 * 用户服务接口 - 适配tb_user表结构
 */
public interface UserService {
    
    /**
     * 根据ID查询用户
     */
    User getUserById(Integer id);
    
    /**
     * 根据姓名查询用户
     */
    User getUserByName(String name);
    
    /**
     * 查询所有用户
     */
    List<User> getAllUsers();
    
    /**
     * 添加用户
     */
    boolean addUser(User user);
    
    /**
     * 更新用户信息
     */
    boolean updateUser(User user);
    
    /**
     * 根据ID删除用户
     */
    boolean deleteUser(Integer id);
    
    /**
     * 获取用户总数
     */
    int getUserCount();
}
