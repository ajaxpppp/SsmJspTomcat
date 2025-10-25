package com.study.ssm.lab;

import org.junit.Test;

/**
 * Spring框架实验 - 综合测试类
 * 依次运行四个实验
 */
public class Test_AllExperiments {
    
    @Test
    public void runAllExperiments() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                            ║");
        System.out.println("║         Spring框架实验 - 综合测试程序                       ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        try {
            // 实验1：Spring简单应用
            System.out.println("\n\n");
            new Test_Experiment1().testSimpleApplication();
            Thread.sleep(2000);
            
            // 实验2：Setter注入
            System.out.println("\n\n");
            new Test_Experiment2().testSetterInjection();
            Thread.sleep(2000);
            
            // 实验3：构造注入
            System.out.println("\n\n");
            new Test_Experiment3().testConstructorInjection();
            Thread.sleep(2000);
            
            // 实验4：基于注解的装配
            System.out.println("\n\n");
            new Test_Experiment4().testAnnotationInjection();
            
            // 总结
            System.out.println("\n\n");
            System.out.println("╔════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                            ║");
            System.out.println("║                    实验总结                                 ║");
            System.out.println("║                                                            ║");
            System.out.println("╚════════════════════════════════════════════════════════════╝");
            System.out.println();
            System.out.println("✓ 实验1：掌握了Spring IoC容器的基本使用");
            System.out.println("✓ 实验2：掌握了通过Setter方法注入依赖");
            System.out.println("✓ 实验3：掌握了通过构造函数注入依赖");
            System.out.println("✓ 实验4：掌握了使用注解进行依赖注入");
            System.out.println();
            System.out.println("【三种注入方式对比】");
            System.out.println("┌────────────┬──────────────┬──────────────┬──────────────┐");
            System.out.println("│ 特性       │ Setter注入   │ 构造注入     │ 注解装配     │");
            System.out.println("├────────────┼──────────────┼──────────────┼──────────────┤");
            System.out.println("│ 配置方式   │ <property>   │ <constructor>│ @Autowired   │");
            System.out.println("│ 灵活性     │ 高           │ 中           │ 高           │");
            System.out.println("│ 依赖完整性 │ 弱           │ 强           │ 中           │");
            System.out.println("│ 适用场景   │ 可选依赖     │ 必需依赖     │ 现代开发     │");
            System.out.println("└────────────┴──────────────┴──────────────┴──────────────┘");
            System.out.println();
            System.out.println("所有实验已成功完成！");
            System.out.println();
            
        } catch (Exception e) {
            System.err.println("实验执行出错：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
