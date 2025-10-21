package com.study.ssm.controller;

import com.study.ssm.entity.Order;
import com.study.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单控制器
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    /**
     * 获取订单详情（包含用户和商品信息）
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Map<String, Object> getOrderDetail(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            Order order = orderService.getOrderWithDetails(id);
            if (order != null) {
                result.put("success", true);
                result.put("data", order);
                result.put("message", "查询成功");
            } else {
                result.put("success", false);
                result.put("message", "订单不存在");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败：" + e.getMessage());
        }
        return result;
    }
    
    /**
     * 获取所有订单（包含详情）
     */
    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> getAllOrders() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Order> orders = orderService.getAllOrdersWithDetails();
            result.put("success", true);
            result.put("data", orders);
            result.put("total", orders.size());
            result.put("message", "查询成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败：" + e.getMessage());
        }
        return result;
    }
    
    /**
     * 根据用户ID获取订单
     */
    @GetMapping("/user/{userId}")
    @ResponseBody
    public Map<String, Object> getOrdersByUser(@PathVariable Integer userId) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Order> orders = orderService.getOrdersByUserId(userId);
            result.put("success", true);
            result.put("data", orders);
            result.put("total", orders.size());
            result.put("message", "查询成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败：" + e.getMessage());
        }
        return result;
    }
    
    /**
     * 创建订单
     */
    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> createOrder(@RequestBody Order order) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = orderService.createOrder(order);
            result.put("success", success);
            result.put("message", success ? "创建成功" : "创建失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "创建失败：" + e.getMessage());
        }
        return result;
    }
    
    /**
     * 更新订单
     */
    @PutMapping("/update")
    @ResponseBody
    public Map<String, Object> updateOrder(@RequestBody Order order) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = orderService.updateOrder(order);
            result.put("success", success);
            result.put("message", success ? "更新成功" : "更新失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "更新失败：" + e.getMessage());
        }
        return result;
    }
    
    /**
     * 删除订单
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Map<String, Object> deleteOrder(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = orderService.deleteOrder(id);
            result.put("success", success);
            result.put("message", success ? "删除成功" : "删除失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "删除失败：" + e.getMessage());
        }
        return result;
    }
}
