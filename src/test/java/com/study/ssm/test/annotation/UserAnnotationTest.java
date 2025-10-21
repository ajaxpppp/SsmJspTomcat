package com.study.ssm.test.annotation;

import com.study.ssm.entity.UserAnnotation;
import com.study.ssm.mapper.UserAnnotationMapper;
import com.study.ssm.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 用户注解方式测试类
 * 测试使用MyBatis注解实现对tb_user表的增删改查操作
 */
public class UserAnnotationTest {
    
    /**
     * 测试1：插入用户（增加操作）
     */
    @Test
    public void testInsertUser() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            UserAnnotationMapper mapper = sqlSession.getMapper(UserAnnotationMapper.class);
            
            System.out.println("========== 测试1：插入用户（使用注解） ==========");
            
            // 创建新用户
            UserAnnotation user = new UserAnnotation();
            user.setUsername("testuser");
            user.setPassword("123456");
            user.setPhone("13800138000");
            user.setAddress("北京市朝阳区");
            
            // 执行插入
            int result = mapper.insertUser(user);
            sqlSession.commit();
            
            System.out.println("插入结果: " + (result > 0 ? "成功" : "失败"));
            System.out.println("生成的用户ID: " + user.getId());
            System.out.println("用户信息: " + user);
            
        } finally {
            sqlSession.close();
        }
    }
    
    /**
     * 测试2：根据ID查询用户（查询操作）
     */
    @Test
    public void testSelectUserById() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            UserAnnotationMapper mapper = sqlSession.getMapper(UserAnnotationMapper.class);
            
            System.out.println("========== 测试2：根据ID查询用户（使用注解） ==========");
            
            UserAnnotation user = mapper.selectUserById(1);
            
            if (user != null) {
                System.out.println("查询成功！");
                System.out.println("用户ID: " + user.getId());
                System.out.println("用户名: " + user.getUsername());
                System.out.println("密码: " + user.getPassword());
                System.out.println("电话: " + user.getPhone());
                System.out.println("地址: " + user.getAddress());
            } else {
                System.out.println("未找到用户");
            }
            
        } finally {
            sqlSession.close();
        }
    }
    
    /**
     * 测试3：更新用户信息（修改操作）
     */
    @Test
    public void testUpdateUser() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            UserAnnotationMapper mapper = sqlSession.getMapper(UserAnnotationMapper.class);
            
            System.out.println("========== 测试3：更新用户信息（使用注解） ==========");
            
            // 先查询用户
            UserAnnotation user = mapper.selectUserById(1);
            if (user != null) {
                System.out.println("更新前: " + user);
                
                // 修改用户信息
                user.setPassword("newpassword");
                user.setPhone("13900139000");
                user.setAddress("上海市浦东新区");
                
                // 执行更新
                int result = mapper.updateUser(user);
                sqlSession.commit();
                
                System.out.println("更新结果: " + (result > 0 ? "成功" : "失败"));
                
                // 重新查询验证
                UserAnnotation updatedUser = mapper.selectUserById(1);
                System.out.println("更新后: " + updatedUser);
            }
            
        } finally {
            sqlSession.close();
        }
    }
    
    /**
     * 测试4：删除用户（删除操作）
     */
    @Test
    public void testDeleteUser() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            UserAnnotationMapper mapper = sqlSession.getMapper(UserAnnotationMapper.class);
            
            System.out.println("========== 测试4：删除用户（使用注解） ==========");
            
            // 先插入一个测试用户
            UserAnnotation testUser = new UserAnnotation("deletetest", "123456", "13700137000", "测试地址");
            mapper.insertUser(testUser);
            sqlSession.commit();
            System.out.println("插入测试用户，ID: " + testUser.getId());
            
            // 执行删除
            int result = mapper.deleteUserById(testUser.getId());
            sqlSession.commit();
            
            System.out.println("删除结果: " + (result > 0 ? "成功" : "失败"));
            
            // 验证删除
            UserAnnotation deletedUser = mapper.selectUserById(testUser.getId());
            System.out.println("验证删除: " + (deletedUser == null ? "用户已删除" : "删除失败"));
            
        } finally {
            sqlSession.close();
        }
    }
    
    /**
     * 测试5：查询所有用户
     */
    @Test
    public void testSelectAllUsers() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            UserAnnotationMapper mapper = sqlSession.getMapper(UserAnnotationMapper.class);
            
            System.out.println("========== 测试5：查询所有用户（使用注解） ==========");
            
            List<UserAnnotation> users = mapper.selectAllUsers();
            
            System.out.println("共查询到 " + users.size() + " 个用户：");
            for (UserAnnotation user : users) {
                System.out.println("  - " + user);
            }
            
        } finally {
            sqlSession.close();
        }
    }
    
    /**
     * 测试6：综合CRUD操作
     */
    @Test
    public void testCompleteCRUD() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            UserAnnotationMapper mapper = sqlSession.getMapper(UserAnnotationMapper.class);
            
            System.out.println("==========================================");
            System.out.println("    用户管理 - 注解方式CRUD综合测试");
            System.out.println("==========================================\n");
            
            // 1. 创建（Create）
            System.out.println("【1. 创建用户】");
            UserAnnotation newUser = new UserAnnotation();
            newUser.setUsername("annotation_user");
            newUser.setPassword("anno123");
            newUser.setPhone("13666666666");
            newUser.setAddress("深圳市南山区");
            
            mapper.insertUser(newUser);
            sqlSession.commit();
            System.out.println("创建成功，用户ID: " + newUser.getId());
            
            // 2. 读取（Read）
            System.out.println("\n【2. 读取用户】");
            UserAnnotation readUser = mapper.selectUserById(newUser.getId());
            System.out.println("读取结果: " + readUser);
            
            // 3. 更新（Update）
            System.out.println("\n【3. 更新用户】");
            readUser.setPassword("updated_pass");
            readUser.setAddress("深圳市福田区");
            mapper.updateUser(readUser);
            sqlSession.commit();
            
            UserAnnotation updatedUser = mapper.selectUserById(newUser.getId());
            System.out.println("更新后: " + updatedUser);
            
            // 4. 删除（Delete）
            System.out.println("\n【4. 删除用户】");
            mapper.deleteUserById(newUser.getId());
            sqlSession.commit();
            
            UserAnnotation deletedUser = mapper.selectUserById(newUser.getId());
            System.out.println("删除验证: " + (deletedUser == null ? "删除成功" : "删除失败"));
            
            System.out.println("\n==========================================");
            System.out.println("           CRUD测试完成");
            System.out.println("==========================================");
            
        } finally {
            sqlSession.close();
        }
    }
    
    /**
     * 测试7：批量操作和高级查询
     */
    @Test
    public void testAdvancedOperations() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            UserAnnotationMapper mapper = sqlSession.getMapper(UserAnnotationMapper.class);
            
            System.out.println("========== 测试7：批量操作和高级查询 ==========");
            
            // 1. 模糊查询
            System.out.println("\n【模糊查询】");
            List<UserAnnotation> users = mapper.selectUsersByKeyword("张");
            System.out.println("包含'张'的用户：");
            for (UserAnnotation user : users) {
                System.out.println("  - " + user.getUsername());
            }
            
            // 2. 统计用户总数
            System.out.println("\n【统计用户】");
            int count = mapper.countUsers();
            System.out.println("用户总数: " + count);
            
            // 3. 批量删除（先创建测试数据）
            System.out.println("\n【批量删除】");
            UserAnnotation user1 = new UserAnnotation("batch1", "123", "111", "addr1");
            UserAnnotation user2 = new UserAnnotation("batch2", "123", "222", "addr2");
            mapper.insertUser(user1);
            mapper.insertUser(user2);
            sqlSession.commit();
            
            List<Integer> ids = Arrays.asList(user1.getId(), user2.getId());
            int deleted = mapper.deleteUsersByIds(ids);
            sqlSession.commit();
            System.out.println("批量删除 " + deleted + " 个用户");
            
        } finally {
            sqlSession.close();
        }
    }
}
