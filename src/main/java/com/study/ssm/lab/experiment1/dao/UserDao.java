package com.study.ssm.lab.experiment1.dao;

/**
 * 实验1：Spring简单应用 - 数据访问层接口
 */
public interface UserDao {
    /**
     * 根据用户ID查询用户名
     */
    String findUserNameById(int userId);
    
    /**
     * 保存用户
     */
    void saveUser(String userName);
}
