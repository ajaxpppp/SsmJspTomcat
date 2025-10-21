package com.study.ssm.mapper;

import com.study.ssm.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户数据访问接口 - 适配tb_user表结构
 */
public interface UserMapper {
    
    /**
     * 根据ID查询用户
     */
    User selectUserById(@Param("id") Integer id);
    
    /**
     * 根据姓名查询用户
     */
    User selectUserByName(@Param("name") String name);
    
    /**
     * 查询所有用户
     */
    List<User> selectAllUsers();
    
    /**
     * 插入用户
     */
    int insertUser(User user);
    
    /**
     * 更新用户
     */
    int updateUser(User user);
    
    /**
     * 根据ID删除用户
     */
    int deleteUser(@Param("id") Integer id);
}
