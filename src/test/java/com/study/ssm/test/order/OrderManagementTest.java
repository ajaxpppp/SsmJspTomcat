package com.study.ssm.test.order;

import com.study.ssm.entity.Order;
import com.study.ssm.entity.Article;
import com.study.ssm.entity.Item;
import com.study.ssm.mapper.OrderMapper;
import com.study.ssm.mapper.ArticleMapper;
import com.study.ssm.mapper.ItemMapper;
import com.study.ssm.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单管理综合测试类
 */
public class OrderManagementTest {
    
    /**
     * 综合测试：完整的订单查询流程
     */
    @Test
    public void testCompleteOrderQuery() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            System.out.println("==========================================");
            System.out.println("      订单管理系统 - 综合查询测试");
            System.out.println("==========================================\n");
            
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
            
            // 1. 查询所有商品
            System.out.println("【1. 商品列表】");
            System.out.println("-----------------------------------------");
            List<Article> articles = articleMapper.selectAllArticles();
            for (Article article : articles) {
                System.out.printf("商品ID: %d | 名称: %-15s | 价格: ￥%.2f | 备注: %s%n",
                    article.getId(), article.getName(), article.getPrice(), article.getRemark());
            }
            
            // 2. 查询订单详情（订单ID=1）
            System.out.println("\n【2. 订单详情查询（订单ID=1）】");
            System.out.println("-----------------------------------------");
            Order order1 = orderMapper.selectOrderWithUserAndItems(1);
            displayOrderDetails(order1);
            
            // 3. 查询订单详情（订单ID=2）
            System.out.println("\n【3. 订单详情查询（订单ID=2）】");
            System.out.println("-----------------------------------------");
            Order order2 = orderMapper.selectOrderWithUserAndItems(2);
            displayOrderDetails(order2);
            
            // 4. 统计信息
            System.out.println("\n【4. 订单统计信息】");
            System.out.println("-----------------------------------------");
            List<Order> allOrders = orderMapper.selectAllOrdersWithDetails();
            BigDecimal totalAmount = BigDecimal.ZERO;
            int totalItems = 0;
            
            for (Order order : allOrders) {
                totalAmount = totalAmount.add(order.getTotal());
                if (order.getItems() != null) {
                    for (Item item : order.getItems()) {
                        totalItems += item.getAmount();
                    }
                }
            }
            
            System.out.println("订单总数: " + allOrders.size() + " 个");
            System.out.println("订单总金额: ￥" + totalAmount);
            System.out.println("商品总件数: " + totalItems + " 件");
            
            System.out.println("\n==========================================");
            System.out.println("           测试完成");
            System.out.println("==========================================");
            
        } finally {
            sqlSession.close();
        }
    }
    
    /**
     * 显示订单详细信息
     */
    private void displayOrderDetails(Order order) {
        if (order == null) {
            System.out.println("订单不存在");
            return;
        }
        
        System.out.println("订单编号: " + order.getCode());
        System.out.println("订单总额: ￥" + order.getTotal());
        
        // 显示用户信息（触发懒加载）
        if (order.getUser() != null) {
            System.out.println("下单用户: " + order.getUser().getName());
            System.out.println("联系电话: " + order.getUser().getPhone());
            System.out.println("收货地址: " + order.getUser().getAddress());
        }
        
        // 显示商品明细（触发懒加载）
        System.out.println("\n商品明细:");
        if (order.getItems() != null && !order.getItems().isEmpty()) {
            System.out.println("┌─────────────────────┬────────┬────────┬──────────┐");
            System.out.println("│ 商品名称            │ 单价   │ 数量   │ 小计     │");
            System.out.println("├─────────────────────┼────────┼────────┼──────────┤");
            
            BigDecimal subtotal = BigDecimal.ZERO;
            for (Item item : order.getItems()) {
                Article article = item.getArticle();
                BigDecimal itemTotal = article.getPrice().multiply(new BigDecimal(item.getAmount()));
                subtotal = subtotal.add(itemTotal);
                
                System.out.printf("│ %-19s │ ￥%5.1f │ %6d │ ￥%7.1f │%n",
                    article.getName(), 
                    article.getPrice(),
                    item.getAmount(),
                    itemTotal);
            }
            System.out.println("└─────────────────────┴────────┴────────┴──────────┘");
            System.out.println("                                   合计: ￥" + subtotal);
        }
    }
    
    /**
     * 测试懒加载性能对比
     */
    @Test
    public void testLazyLoadingPerformance() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            
            System.out.println("==========================================");
            System.out.println("        懒加载性能测试");
            System.out.println("==========================================\n");
            
            // 场景1：只需要订单基本信息
            System.out.println("【场景1】只查询订单基本信息");
            System.out.println("使用懒加载查询，但不访问关联对象：");
            long startTime1 = System.currentTimeMillis();
            
            List<Order> orders1 = orderMapper.selectAllOrdersWithDetails();
            for (Order order : orders1) {
                // 只访问订单基本信息，不触发懒加载
                String code = order.getCode();
                BigDecimal total = order.getTotal();
            }
            
            long endTime1 = System.currentTimeMillis();
            System.out.println("执行时间: " + (endTime1 - startTime1) + "ms");
            System.out.println("说明: 由于没有访问user和items属性，不会执行额外的SQL查询\n");
            
            // 场景2：需要所有关联信息
            System.out.println("【场景2】查询订单及所有关联信息");
            System.out.println("使用懒加载查询，并访问所有关联对象：");
            long startTime2 = System.currentTimeMillis();
            
            List<Order> orders2 = orderMapper.selectAllOrdersWithDetails();
            for (Order order : orders2) {
                // 访问所有关联信息，触发懒加载
                if (order.getUser() != null) {
                    String userName = order.getUser().getName();
                }
                if (order.getItems() != null) {
                    for (Item item : order.getItems()) {
                        String articleName = item.getArticle().getName();
                    }
                }
            }
            
            long endTime2 = System.currentTimeMillis();
            System.out.println("执行时间: " + (endTime2 - startTime2) + "ms");
            System.out.println("说明: 访问关联属性时触发懒加载，执行额外的SQL查询\n");
            
            System.out.println("==========================================");
            System.out.println("结论: 懒加载可以根据实际需要加载数据，");
            System.out.println("避免不必要的查询，提高系统性能。");
            System.out.println("==========================================");
            
        } finally {
            sqlSession.close();
        }
    }
}
