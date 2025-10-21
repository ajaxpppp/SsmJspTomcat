package com.study.ssm.mapper;

import com.study.ssm.entity.Order;
import java.util.List;

/**
 * 订单Mapper接口
 */
public interface OrderMapper {
    
    /**
     * 根据ID查询订单信息
     * @param id 订单ID
     * @return 订单信息
     */
    Order selectOrderById(Integer id);
    
    /**
     * 查询订单信息，同时懒加载用户信息
     * @param id 订单ID
     * @return 订单信息（包含用户信息）
     */
    Order selectOrderWithUser(Integer id);
    
    /**
     * 查询订单信息，同时懒加载商品信息
     * @param id 订单ID
     * @return 订单信息（包含商品信息）
     */
    Order selectOrderWithItems(Integer id);
    
    /**
     * 查询订单信息，同时懒加载用户和商品信息
     * @param id 订单ID
     * @return 订单信息（包含用户和商品信息）
     */
    Order selectOrderWithUserAndItems(Integer id);
    
    /**
     * 查询所有订单
     * @return 订单列表
     */
    List<Order> selectAllOrders();
    
    /**
     * 查询所有订单，同时懒加载用户和商品信息
     * @return 订单列表（包含用户和商品信息）
     */
    List<Order> selectAllOrdersWithDetails();
    
    /**
     * 根据用户ID查询订单
     * @param userId 用户ID
     * @return 订单列表
     */
    List<Order> selectOrdersByUserId(Integer userId);
    
    /**
     * 插入订单
     * @param order 订单信息
     * @return 影响行数
     */
    int insertOrder(Order order);
    
    /**
     * 更新订单
     * @param order 订单信息
     * @return 影响行数
     */
    int updateOrder(Order order);
    
    /**
     * 删除订单
     * @param id 订单ID
     * @return 影响行数
     */
    int deleteOrder(Integer id);
}
