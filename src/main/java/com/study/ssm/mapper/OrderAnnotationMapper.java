package com.study.ssm.mapper;

import com.study.ssm.entity.OrderAnnotation;
import com.study.ssm.entity.UserAnnotation;
import com.study.ssm.entity.Item;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import java.util.List;

/**
 * 订单Mapper接口（注解版本）
 * 使用MyBatis注解实现懒加载功能
 */
public interface OrderAnnotationMapper {
    
    /**
     * 1. 根据ID查询订单基本信息（不包含关联信息）
     * @param id 订单ID
     * @return 订单信息
     */
    @Select("SELECT id, code, total, user_id FROM tb_order WHERE id = #{id}")
    @Results(id = "baseOrderMap", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "code", column = "code"),
        @Result(property = "total", column = "total"),
        @Result(property = "userId", column = "user_id")
    })
    OrderAnnotation selectOrderById(@Param("id") Integer id);
    
    /**
     * 2. 查询订单信息，懒加载用户信息
     * @param id 订单ID
     * @return 订单信息（包含用户信息）
     */
    @Select("SELECT id, code, total, user_id FROM tb_order WHERE id = #{id}")
    @Results(id = "orderWithUserMap", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "code", column = "code"),
        @Result(property = "total", column = "total"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "user", column = "user_id",
                one = @One(select = "com.study.ssm.mapper.UserAnnotationMapper.selectUserById",
                          fetchType = FetchType.LAZY))
    })
    OrderAnnotation selectOrderWithUser(@Param("id") Integer id);
    
    /**
     * 3. 查询订单信息，懒加载商品信息
     * @param id 订单ID
     * @return 订单信息（包含商品信息）
     */
    @Select("SELECT id, code, total, user_id FROM tb_order WHERE id = #{id}")
    @Results(id = "orderWithItemsMap", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "code", column = "code"),
        @Result(property = "total", column = "total"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "items", column = "id",
                many = @Many(select = "com.study.ssm.mapper.ItemMapper.selectItemsWithArticleByOrderId",
                            fetchType = FetchType.LAZY))
    })
    OrderAnnotation selectOrderWithItems(@Param("id") Integer id);
    
    /**
     * 4. 查询订单信息，同时懒加载用户和商品信息
     * @param id 订单ID
     * @return 订单信息（包含用户和商品信息）
     */
    @Select("SELECT id, code, total, user_id FROM tb_order WHERE id = #{id}")
    @Results(id = "orderWithUserAndItemsMap", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "code", column = "code"),
        @Result(property = "total", column = "total"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "user", column = "user_id",
                one = @One(select = "com.study.ssm.mapper.UserAnnotationMapper.selectUserById",
                          fetchType = FetchType.LAZY)),
        @Result(property = "items", column = "id",
                many = @Many(select = "com.study.ssm.mapper.ItemMapper.selectItemsWithArticleByOrderId",
                            fetchType = FetchType.LAZY))
    })
    OrderAnnotation selectOrderWithUserAndItems(@Param("id") Integer id);
    
    /**
     * 5. 查询所有订单（基本信息）
     * @return 订单列表
     */
    @Select("SELECT id, code, total, user_id FROM tb_order")
    @ResultMap("baseOrderMap")
    List<OrderAnnotation> selectAllOrders();
    
    /**
     * 6. 查询所有订单，同时懒加载用户和商品信息
     * @return 订单列表（包含用户和商品信息）
     */
    @Select("SELECT id, code, total, user_id FROM tb_order")
    @ResultMap("orderWithUserAndItemsMap")
    List<OrderAnnotation> selectAllOrdersWithDetails();
    
    /**
     * 7. 根据用户ID查询订单
     * @param userId 用户ID
     * @return 订单列表
     */
    @Select("SELECT id, code, total, user_id FROM tb_order WHERE user_id = #{userId}")
    @ResultMap("orderWithItemsMap")
    List<OrderAnnotation> selectOrdersByUserId(@Param("userId") Integer userId);
    
    /**
     * 8. 插入订单
     * @param order 订单信息
     * @return 影响行数
     */
    @Insert("INSERT INTO tb_order(code, total, user_id) VALUES(#{code}, #{total}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertOrder(OrderAnnotation order);
    
    /**
     * 9. 更新订单
     * @param order 订单信息
     * @return 影响行数
     */
    @Update("UPDATE tb_order SET code = #{code}, total = #{total}, user_id = #{userId} WHERE id = #{id}")
    int updateOrder(OrderAnnotation order);
    
    /**
     * 10. 删除订单
     * @param id 订单ID
     * @return 影响行数
     */
    @Delete("DELETE FROM tb_order WHERE id = #{id}")
    int deleteOrder(@Param("id") Integer id);
}
