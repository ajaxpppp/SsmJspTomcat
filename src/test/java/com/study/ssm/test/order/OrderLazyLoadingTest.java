package com.study.ssm.test.order;

import com.study.ssm.entity.Order;
import com.study.ssm.entity.User;
import com.study.ssm.entity.Item;
import com.study.ssm.entity.Article;
import com.study.ssm.mapper.OrderMapper;
import com.study.ssm.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * 订单懒加载测试类
 * 测试要求：
 * 1. 从tb_order表中查询订单信息
 * 2. 在订单信息中利用懒加载包含用户的全部信息
 * 3. 在订单信息中利用懒加载包含订单中商品的具体信息
 */
public class OrderLazyLoadingTest {
    
    /**
     * 测试1：查询订单基本信息（不包含关联信息）
     */
    @Test
    public void testSelectOrderById() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            
            System.out.println("========== 测试1：查询订单基本信息 ==========");
            Order order = orderMapper.selectOrderById(1);
            
            System.out.println("订单ID: " + order.getId());
            System.out.println("订单编号: " + order.getCode());
            System.out.println("订单总额: " + order.getTotal());
            System.out.println("用户ID: " + order.getUserId());
            
        } finally {
            sqlSession.close();
        }
    }
    
    /**
     * 测试2：查询订单信息，懒加载用户信息
     */
    @Test
    public void testSelectOrderWithUser() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            
            System.out.println("========== 测试2：查询订单信息（懒加载用户信息） ==========");
            Order order = orderMapper.selectOrderWithUser(1);
            
            System.out.println("步骤1：获取订单基本信息");
            System.out.println("订单ID: " + order.getId());
            System.out.println("订单编号: " + order.getCode());
            System.out.println("订单总额: " + order.getTotal());
            
            System.out.println("\n步骤2：触发懒加载，获取用户信息");
            System.out.println("（注意观察SQL日志，此时才会执行查询用户的SQL）");
            User user = order.getUser();
            if (user != null) {
                System.out.println("用户ID: " + user.getId());
                System.out.println("用户名: " + user.getName());
                System.out.println("用户登录名: " + user.getLoginname());
                System.out.println("用户电话: " + user.getPhone());
                System.out.println("用户地址: " + user.getAddress());
            }
            
        } finally {
            sqlSession.close();
        }
    }
    
    /**
     * 测试3：查询订单信息，懒加载商品信息
     */
    @Test
    public void testSelectOrderWithItems() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            
            System.out.println("========== 测试3：查询订单信息（懒加载商品信息） ==========");
            Order order = orderMapper.selectOrderWithItems(1);
            
            System.out.println("步骤1：获取订单基本信息");
            System.out.println("订单ID: " + order.getId());
            System.out.println("订单编号: " + order.getCode());
            System.out.println("订单总额: " + order.getTotal());
            
            System.out.println("\n步骤2：触发懒加载，获取商品信息");
            System.out.println("（注意观察SQL日志，此时才会执行查询商品的SQL）");
            List<Item> items = order.getItems();
            if (items != null && !items.isEmpty()) {
                System.out.println("订单包含 " + items.size() + " 个商品：");
                for (Item item : items) {
                    Article article = item.getArticle();
                    System.out.println("  - 商品名称: " + article.getName());
                    System.out.println("    商品价格: " + article.getPrice());
                    System.out.println("    商品备注: " + article.getRemark());
                    System.out.println("    购买数量: " + item.getAmount());
                    System.out.println();
                }
            }
            
        } finally {
            sqlSession.close();
        }
    }
    
    /**
     * 测试4：查询订单信息，同时懒加载用户和商品信息
     */
    @Test
    public void testSelectOrderWithUserAndItems() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            
            System.out.println("========== 测试4：查询订单信息（同时懒加载用户和商品信息） ==========");
            Order order = orderMapper.selectOrderWithUserAndItems(1);
            
            System.out.println("步骤1：获取订单基本信息");
            System.out.println("订单ID: " + order.getId());
            System.out.println("订单编号: " + order.getCode());
            System.out.println("订单总额: " + order.getTotal());
            
            System.out.println("\n步骤2：触发懒加载，获取用户信息");
            System.out.println("（注意观察SQL日志，此时才会执行查询用户的SQL）");
            User user = order.getUser();
            if (user != null) {
                System.out.println("用户名: " + user.getName());
                System.out.println("用户电话: " + user.getPhone());
                System.out.println("用户地址: " + user.getAddress());
            }
            
            System.out.println("\n步骤3：触发懒加载，获取商品信息");
            System.out.println("（注意观察SQL日志，此时才会执行查询商品的SQL）");
            List<Item> items = order.getItems();
            if (items != null && !items.isEmpty()) {
                System.out.println("订单包含 " + items.size() + " 个商品：");
                for (Item item : items) {
                    Article article = item.getArticle();
                    System.out.println("  - 商品名称: " + article.getName());
                    System.out.println("    商品价格: " + article.getPrice());
                    System.out.println("    商品备注: " + article.getRemark());
                    System.out.println("    购买数量: " + item.getAmount());
                    System.out.println();
                }
            }
            
        } finally {
            sqlSession.close();
        }
    }
    
    /**
     * 测试5：查询所有订单（包含详细信息）
     */
    @Test
    public void testSelectAllOrdersWithDetails() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            
            System.out.println("========== 测试5：查询所有订单（懒加载用户和商品信息） ==========");
            List<Order> orders = orderMapper.selectAllOrdersWithDetails();
            
            System.out.println("共查询到 " + orders.size() + " 个订单\n");
            
            for (Order order : orders) {
                System.out.println("订单 " + order.getId() + ":");
                System.out.println("  订单编号: " + order.getCode());
                System.out.println("  订单总额: " + order.getTotal());
                
                // 触发懒加载获取用户信息
                User user = order.getUser();
                if (user != null) {
                    System.out.println("  下单用户: " + user.getName() + " (电话: " + user.getPhone() + ")");
                }
                
                // 触发懒加载获取商品信息
                List<Item> items = order.getItems();
                if (items != null && !items.isEmpty()) {
                    System.out.println("  订单商品:");
                    for (Item item : items) {
                        Article article = item.getArticle();
                        System.out.println("    - " + article.getName() + 
                                         " (价格: " + article.getPrice() + 
                                         ", 数量: " + item.getAmount() + ")");
                    }
                }
                System.out.println();
            }
            
        } finally {
            sqlSession.close();
        }
    }
    
    /**
     * 测试6：演示懒加载的优势
     */
    @Test
    public void testLazyLoadingAdvantage() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            
            System.out.println("========== 测试6：演示懒加载的优势 ==========");
            System.out.println("场景：只需要订单基本信息，不需要用户和商品信息");
            
            Order order = orderMapper.selectOrderWithUserAndItems(1);
            
            System.out.println("只访问订单基本信息（不会触发关联查询）：");
            System.out.println("订单ID: " + order.getId());
            System.out.println("订单编号: " + order.getCode());
            System.out.println("订单总额: " + order.getTotal());
            
            System.out.println("\n注意：由于没有访问user和items属性，");
            System.out.println("所以不会执行查询用户和商品的SQL，提高了性能！");
            
        } finally {
            sqlSession.close();
        }
    }
}
