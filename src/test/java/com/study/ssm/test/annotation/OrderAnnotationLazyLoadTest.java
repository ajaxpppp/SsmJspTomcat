package com.study.ssm.test.annotation;

import com.study.ssm.entity.OrderAnnotation;
import com.study.ssm.entity.UserAnnotation;
import com.study.ssm.entity.Item;
import com.study.ssm.entity.Article;
import com.study.ssm.mapper.OrderAnnotationMapper;
import com.study.ssm.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * 订单注解方式懒加载测试类
 * 测试要求：
 * 从tb_order表中查询订单信息，
 * 在订单信息中利用懒加载包含用户信息和订单中商品的具体信息
 */
public class OrderAnnotationLazyLoadTest {
    
    /**
     * 测试1：查询订单基本信息（不包含关联信息）
     */
    @Test
    public void testSelectOrderById() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            OrderAnnotationMapper mapper = sqlSession.getMapper(OrderAnnotationMapper.class);
            
            System.out.println("========== 注解方式测试1：查询订单基本信息 ==========");
            OrderAnnotation order = mapper.selectOrderById(1);
            
            System.out.println("订单ID: " + order.getId());
            System.out.println("订单编号: " + order.getCode());
            System.out.println("订单总额: " + order.getTotal());
            System.out.println("用户ID: " + order.getUserId());
            System.out.println("\n说明：使用@Select注解查询订单基本信息");
            
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
            OrderAnnotationMapper mapper = sqlSession.getMapper(OrderAnnotationMapper.class);
            
            System.out.println("========== 注解方式测试2：查询订单（懒加载用户） ==========");
            System.out.println("使用@One注解实现一对一懒加载\n");
            
            OrderAnnotation order = mapper.selectOrderWithUser(1);
            
            System.out.println("步骤1：获取订单基本信息");
            System.out.println("订单ID: " + order.getId());
            System.out.println("订单编号: " + order.getCode());
            System.out.println("订单总额: " + order.getTotal());
            
            System.out.println("\n步骤2：触发懒加载，获取用户信息");
            System.out.println("（注意观察SQL日志，此时才会执行查询用户的SQL）");
            UserAnnotation user = order.getUser();
            if (user != null) {
                System.out.println("用户ID: " + user.getId());
                System.out.println("用户名: " + user.getUsername());
                System.out.println("密码: " + user.getPassword());
                System.out.println("电话: " + user.getPhone());
                System.out.println("地址: " + user.getAddress());
            }
            
            System.out.println("\n关键注解：@One(fetchType = FetchType.LAZY)");
            
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
            OrderAnnotationMapper mapper = sqlSession.getMapper(OrderAnnotationMapper.class);
            
            System.out.println("========== 注解方式测试3：查询订单（懒加载商品） ==========");
            System.out.println("使用@Many注解实现一对多懒加载\n");
            
            OrderAnnotation order = mapper.selectOrderWithItems(1);
            
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
            
            System.out.println("关键注解：@Many(fetchType = FetchType.LAZY)");
            
        } finally {
            sqlSession.close();
        }
    }
    
    /**
     * 核心测试4：查询订单信息，同时懒加载用户和商品信息
     * 这是最重要的测试，完整展示注解方式的懒加载功能
     */
    @Test
    public void testSelectOrderWithUserAndItems() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            OrderAnnotationMapper mapper = sqlSession.getMapper(OrderAnnotationMapper.class);
            
            System.out.println("==========================================");
            System.out.println("   注解方式：订单懒加载完整测试");
            System.out.println("==========================================\n");
            
            OrderAnnotation order = mapper.selectOrderWithUserAndItems(1);
            
            System.out.println("【步骤1】获取订单基本信息");
            System.out.println("----------------------------------------");
            System.out.println("订单ID: " + order.getId());
            System.out.println("订单编号: " + order.getCode());
            System.out.println("订单总额: " + order.getTotal());
            System.out.println("SQL执行: SELECT id, code, total, user_id FROM tb_order WHERE id = ?");
            
            System.out.println("\n【步骤2】触发懒加载 - 获取用户信息");
            System.out.println("----------------------------------------");
            UserAnnotation user = order.getUser();
            if (user != null) {
                System.out.println("用户名: " + user.getUsername());
                System.out.println("密码: " + user.getPassword());
                System.out.println("电话: " + user.getPhone());
                System.out.println("地址: " + user.getAddress());
                System.out.println("SQL执行: SELECT * FROM tb_user WHERE id = ?");
            }
            
            System.out.println("\n【步骤3】触发懒加载 - 获取商品信息");
            System.out.println("----------------------------------------");
            List<Item> items = order.getItems();
            if (items != null && !items.isEmpty()) {
                System.out.println("订单包含 " + items.size() + " 个商品：");
                System.out.println("┌─────────────────────┬────────┬────────┬──────────────────────┐");
                System.out.println("│ 商品名称            │ 价格   │ 数量   │ 备注                 │");
                System.out.println("├─────────────────────┼────────┼────────┼──────────────────────┤");
                for (Item item : items) {
                    Article article = item.getArticle();
                    System.out.printf("│ %-19s │ ￥%5.1f │ %6d │ %-20s │%n",
                        article.getName(), 
                        article.getPrice(),
                        item.getAmount(),
                        article.getRemark());
                }
                System.out.println("└─────────────────────┴────────┴────────┴──────────────────────┘");
                System.out.println("SQL执行: SELECT * FROM tb_item JOIN tb_article WHERE order_id = ?");
            }
            
            System.out.println("\n【注解配置说明】");
            System.out.println("----------------------------------------");
            System.out.println("@Results配置:");
            System.out.println("  - @One(fetchType = FetchType.LAZY) → 用户懒加载");
            System.out.println("  - @Many(fetchType = FetchType.LAZY) → 商品懒加载");
            
            System.out.println("\n==========================================");
            System.out.println("         测试完成 - 懒加载成功");
            System.out.println("==========================================");
            
        } finally {
            sqlSession.close();
        }
    }
    
    /**
     * 测试5：查询所有订单（懒加载详情）
     */
    @Test
    public void testSelectAllOrdersWithDetails() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            OrderAnnotationMapper mapper = sqlSession.getMapper(OrderAnnotationMapper.class);
            
            System.out.println("========== 注解方式测试5：查询所有订单（懒加载） ==========");
            
            List<OrderAnnotation> orders = mapper.selectAllOrdersWithDetails();
            
            System.out.println("共查询到 " + orders.size() + " 个订单\n");
            
            for (OrderAnnotation order : orders) {
                System.out.println("订单 " + order.getId() + ":");
                System.out.println("  订单编号: " + order.getCode());
                System.out.println("  订单总额: " + order.getTotal());
                
                // 触发懒加载获取用户信息
                UserAnnotation user = order.getUser();
                if (user != null) {
                    System.out.println("  下单用户: " + user.getUsername() + 
                                     " (电话: " + user.getPhone() + ")");
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
     * 测试6：对比XML配置和注解配置
     */
    @Test
    public void testCompareXMLAndAnnotation() {
        System.out.println("==========================================");
        System.out.println("      XML配置 vs 注解配置 对比");
        System.out.println("==========================================\n");
        
        System.out.println("【XML配置方式】");
        System.out.println("----------------------------------------");
        System.out.println("<association property=\"user\"");
        System.out.println("    select=\"selectUserById\"");
        System.out.println("    column=\"user_id\"");
        System.out.println("    fetchType=\"lazy\"/>");
        
        System.out.println("\n【注解配置方式】");
        System.out.println("----------------------------------------");
        System.out.println("@Result(property = \"user\",");
        System.out.println("    column = \"user_id\",");
        System.out.println("    one = @One(");
        System.out.println("        select = \"selectUserById\",");
        System.out.println("        fetchType = FetchType.LAZY))");
        
        System.out.println("\n【优缺点对比】");
        System.out.println("----------------------------------------");
        System.out.println("XML配置:");
        System.out.println("  ✓ 复杂SQL更清晰");
        System.out.println("  ✓ 支持动态SQL");
        System.out.println("  ✗ 需要额外的XML文件");
        
        System.out.println("\n注解配置:");
        System.out.println("  ✓ 代码更简洁");
        System.out.println("  ✓ 无需额外文件");
        System.out.println("  ✗ 复杂SQL可读性差");
        
        System.out.println("\n==========================================");
    }
}
