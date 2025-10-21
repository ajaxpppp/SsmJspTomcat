package com.study.ssm.service;

import com.study.ssm.entity.Order;
import java.util.List;

/**
 * 订单服务接口
 */
public interface OrderService {
    
    /**
     * 根据ID查询订单（包含用户和商品信息，懒加载）
     * @param id 订单ID
     * @return 订单信息
     */
    Order getOrderWithDetails(Integer id);
    
    /**
     * 查询所有订单（包含用户和商品信息，懒加载）
     * @return 订单列表
     */
    List<Order> getAllOrdersWithDetails();
    
    /**
     * 根据用户ID查询订单
     * @param userId 用户ID
     * @return 订单列表
     */
    List<Order> getOrdersByUserId(Integer userId);
    
    /**
     * 创建订单
     * @param order 订单信息
     * @return 是否成功
     */
    boolean createOrder(Order order);
    
    /**
     * 更新订单
     * @param order 订单信息
     * @return 是否成功
     */
    boolean updateOrder(Order order);
    
    /**
     * 删除订单
     * @param id 订单ID
     * @return 是否成功
     */
    boolean deleteOrder(Integer id);
}
