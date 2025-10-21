package com.study.ssm.service.impl;

import com.study.ssm.entity.Order;
import com.study.ssm.mapper.OrderMapper;
import com.study.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单服务实现类
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Override
    public Order getOrderWithDetails(Integer id) {
        // 使用懒加载查询订单，包含用户和商品信息
        return orderMapper.selectOrderWithUserAndItems(id);
    }
    
    @Override
    public List<Order> getAllOrdersWithDetails() {
        // 查询所有订单，包含用户和商品信息（懒加载）
        return orderMapper.selectAllOrdersWithDetails();
    }
    
    @Override
    public List<Order> getOrdersByUserId(Integer userId) {
        return orderMapper.selectOrdersByUserId(userId);
    }
    
    @Override
    public boolean createOrder(Order order) {
        return orderMapper.insertOrder(order) > 0;
    }
    
    @Override
    public boolean updateOrder(Order order) {
        return orderMapper.updateOrder(order) > 0;
    }
    
    @Override
    public boolean deleteOrder(Integer id) {
        return orderMapper.deleteOrder(id) > 0;
    }
}
