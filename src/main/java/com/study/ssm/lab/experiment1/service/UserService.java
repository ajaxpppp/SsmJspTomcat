package com.study.ssm.lab.experiment1.service;

/**
 * 实验1：Spring简单应用 - 业务层接口
 */
public interface UserService {
    /**
     * 获取用户信息
     */
    String getUserInfo(int userId);
    
    /**
     * 注册新用户
     */
    void registerUser(String userName);
}
