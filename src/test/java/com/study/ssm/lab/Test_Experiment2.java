package com.study.ssm.lab;

import com.study.ssm.lab.experiment2.service.ProductService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 实验2：Setter注入（设置注入）- 测试类
 */
public class Test_Experiment2 {
    
    @Test
    public void testSetterInjection() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║    实验2：Setter注入（设置注入）                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println();
        
        // 1. 加载Spring配置文件
        System.out.println(">>> 步骤1：加载Spring配置文件");
        ApplicationContext context = new ClassPathXmlApplicationContext(
            "spring-lab-experiment2.xml"
        );
        System.out.println(">>> Spring容器创建成功！");
        System.out.println();
        
        // 2. 获取Bean（此时所有Setter注入已完成）
        System.out.println(">>> 步骤2：从容器获取ProductService Bean");
        ProductService productService = (ProductService) context.getBean("labProductService");
        System.out.println(">>> 获取Bean成功！");
        System.out.println();
        
        // 3. 调用业务方法，验证注入是否成功
        System.out.println(">>> 步骤3：调用业务方法");
        System.out.println("--------------------------------------------------");
        String productInfo = productService.getProductInfo(2001);
        System.out.println(">>> 查询结果：" + productInfo);
        System.out.println();
        
        productService.createProduct("华为Mate60", 6999.0);
        System.out.println(">>> 商品创建完成");
        System.out.println("--------------------------------------------------");
        
        System.out.println();
        System.out.println("【实验总结】");
        System.out.println("1. Setter注入使用<property>标签配置");
        System.out.println("2. 对象注入使用ref属性，基本类型使用value属性");
        System.out.println("3. 可以注入多个不同类型的属性（String、int、double等）");
        System.out.println("4. Setter注入灵活，可以选择性注入部分属性");
        System.out.println("5. 适用场景：可选依赖、需要重新配置的场景");
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║    实验2完成！                                    ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }
}
