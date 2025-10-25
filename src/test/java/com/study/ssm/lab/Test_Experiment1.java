package com.study.ssm.lab;

import com.study.ssm.lab.experiment1.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 实验1：Spring的简单应用 - 测试类
 */
public class Test_Experiment1 {
    
    @Test
    public void testSimpleApplication() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║    实验1：Spring的简单应用                        ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println();
        
        // 1. 加载Spring配置文件，创建IoC容器
        System.out.println(">>> 步骤1：加载Spring配置文件");
        ApplicationContext context = new ClassPathXmlApplicationContext(
            "spring-lab-experiment1.xml"
        );
        System.out.println(">>> Spring容器创建成功！");
        System.out.println();
        
        // 2. 从容器中获取Bean
        System.out.println(">>> 步骤2：从容器获取UserService Bean");
        UserService userService = (UserService) context.getBean("labUserService");
        System.out.println(">>> 获取Bean成功！");
        System.out.println();
        
        // 3. 调用业务方法
        System.out.println(">>> 步骤3：调用业务方法");
        System.out.println("--------------------------------------------------");
        String userName = userService.getUserInfo(1001);
        System.out.println(">>> 查询结果：" + userName);
        System.out.println();
        
        userService.registerUser("张三");
        System.out.println(">>> 用户注册完成");
        System.out.println("--------------------------------------------------");
        
        System.out.println();
        System.out.println("【实验总结】");
        System.out.println("1. 通过XML配置文件定义Bean");
        System.out.println("2. Spring容器负责创建和管理Bean");
        System.out.println("3. 通过ApplicationContext获取Bean实例");
        System.out.println("4. 实现了业务层和数据层的解耦");
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║    实验1完成！                                    ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }
}
