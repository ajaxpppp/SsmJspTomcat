package com.study.ssm.lab;

import com.study.ssm.lab.experiment3.service.OrderService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 实验3：构造注入 - 测试类
 */
public class Test_Experiment3 {
    
    @Test
    public void testConstructorInjection() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║    实验3：构造注入（Constructor Injection）       ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println();
        
        // 1. 加载Spring配置文件
        System.out.println(">>> 步骤1：加载Spring配置文件");
        ApplicationContext context = new ClassPathXmlApplicationContext(
            "spring-lab-experiment3.xml"
        );
        System.out.println(">>> Spring容器创建成功！");
        System.out.println();
        
        // 2. 获取Bean（构造注入在对象创建时就已完成）
        System.out.println(">>> 步骤2：从容器获取OrderService Bean");
        OrderService orderService = (OrderService) context.getBean("labOrderService");
        System.out.println(">>> 获取Bean成功！");
        System.out.println();
        
        // 3. 调用业务方法，验证注入是否成功
        System.out.println(">>> 步骤3：调用业务方法");
        System.out.println("--------------------------------------------------");
        String orderInfo = orderService.getOrderInfo(3001);
        System.out.println(">>> 查询结果：" + orderInfo);
        System.out.println();
        
        orderService.submitOrder("ORD20241026001", 10000.0);
        System.out.println(">>> 订单提交完成");
        System.out.println("--------------------------------------------------");
        
        System.out.println();
        System.out.println("【实验总结】");
        System.out.println("1. 构造注入使用<constructor-arg>标签配置");
        System.out.println("2. 可以使用index属性指定参数位置");
        System.out.println("3. 可以使用type属性指定参数类型");
        System.out.println("4. 支持注入对象引用和各种基本类型（String、int、double、boolean等）");
        System.out.println("5. 依赖在对象创建时就确定，保证了对象的完整性和不可变性");
        System.out.println("6. 适用场景：必需依赖、不可变对象、依赖关系明确的场景");
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║    实验3完成！                                    ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }
}
