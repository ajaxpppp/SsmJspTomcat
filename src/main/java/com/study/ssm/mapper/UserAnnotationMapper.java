package com.study.ssm.mapper;

import com.study.ssm.entity.UserAnnotation;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * 用户Mapper接口（注解版本）
 * 使用MyBatis注解实现对tb_user表的增删改查操作
 */
public interface UserAnnotationMapper {
    
    /**
     * 1. 插入用户（增加操作）
     * @param user 用户信息
     * @return 影响行数
     */
    @Insert("INSERT INTO tb_user(username, password, phone, address) " +
            "VALUES(#{username}, #{password}, #{phone}, #{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(UserAnnotation user);
    
    /**
     * 2. 根据ID删除用户（删除操作）
     * @param id 用户ID
     * @return 影响行数
     */
    @Delete("DELETE FROM tb_user WHERE id = #{id}")
    int deleteUserById(@Param("id") Integer id);
    
    /**
     * 3. 更新用户信息（修改操作）
     * @param user 用户信息
     * @return 影响行数
     */
    @Update("UPDATE tb_user SET " +
            "username = #{username}, " +
            "password = #{password}, " +
            "phone = #{phone}, " +
            "address = #{address} " +
            "WHERE id = #{id}")
    int updateUser(UserAnnotation user);
    
    /**
     * 4. 根据ID查询用户（查询操作）
     * @param id 用户ID
     * @return 用户信息
     */
    @Select("SELECT id, username, password, phone, address FROM tb_user WHERE id = #{id}")
    @Results(id = "userResultMap", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "username", column = "username"),
        @Result(property = "password", column = "password"),
        @Result(property = "phone", column = "phone"),
        @Result(property = "address", column = "address")
    })
    UserAnnotation selectUserById(@Param("id") Integer id);
    
    /**
     * 5. 查询所有用户
     * @return 用户列表
     */
    @Select("SELECT id, username, password, phone, address FROM tb_user")
    @ResultMap("userResultMap")
    List<UserAnnotation> selectAllUsers();
    
    /**
     * 6. 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    @Select("SELECT id, username, password, phone, address FROM tb_user WHERE username = #{username}")
    @ResultMap("userResultMap")
    UserAnnotation selectUserByUsername(@Param("username") String username);
    
    /**
     * 7. 根据用户名和密码查询用户（用于登录验证）
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    @Select("SELECT id, username, password, phone, address FROM tb_user " +
            "WHERE username = #{username} AND password = #{password}")
    @ResultMap("userResultMap")
    UserAnnotation selectUserByUsernameAndPassword(@Param("username") String username, 
                                                   @Param("password") String password);
    
    /**
     * 8. 批量删除用户
     * @param ids ID列表
     * @return 影响行数
     */
    @Delete("<script>" +
            "DELETE FROM tb_user WHERE id IN " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    int deleteUsersByIds(@Param("ids") List<Integer> ids);
    
    /**
     * 9. 模糊查询用户（根据用户名）
     * @param keyword 关键字
     * @return 用户列表
     */
    @Select("SELECT id, username, password, phone, address FROM tb_user " +
            "WHERE username LIKE CONCAT('%', #{keyword}, '%')")
    @ResultMap("userResultMap")
    List<UserAnnotation> selectUsersByKeyword(@Param("keyword") String keyword);
    
    /**
     * 10. 统计用户总数
     * @return 用户总数
     */
    @Select("SELECT COUNT(*) FROM tb_user")
    int countUsers();
}
