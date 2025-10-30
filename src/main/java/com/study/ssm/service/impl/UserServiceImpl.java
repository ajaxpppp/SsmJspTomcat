package com.study.ssm.service.impl;

import com.study.ssm.entity.User;
import com.study.ssm.mapper.UserMapper;
import com.study.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户服务实现类 - HRM人力资源管理系统
 * 使用Spring依赖注入，整合SSM框架
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAllUsers();
    }

    @Override
    public boolean addUser(User user) {
        try {
            // 检查姓名是否已存在
            User existUser = userMapper.selectUserByName(user.getName());
            if (existUser != null) {
                System.out.println("用户名已存在：" + user.getName());
                return false;
            }
            int result = userMapper.insertUser(user);
            System.out.println("添加用户成功，ID：" + user.getId());
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加用户失败：" + e.getMessage());
        }
    }

    @Override
    public boolean updateUser(User user) {
        try {
            int result = userMapper.updateUser(user);
            System.out.println("更新用户成功，ID：" + user.getId());
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新用户失败：" + e.getMessage());
        }
    }

    @Override
    public boolean deleteUser(Integer id) {
        try {
            int result = userMapper.deleteUser(id);
            System.out.println("删除用户成功，ID：" + id);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除用户失败：" + e.getMessage());
        }
    }

    @Override
    public int getUserCount() {
        List<User> users = userMapper.selectAllUsers();
        return users != null ? users.size() : 0;
    }
}
