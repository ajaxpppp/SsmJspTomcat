package com.study.ssm.lab;

import com.study.ssm.lab.experiment4.service.CustomerService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 实验4：基于注解的装配 - 测试类
 */
public class Test_Experiment4 {
    
    @Test
    public void testAnnotationInjection() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║    实验4：基于注解的装配                          ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println();
        
        // 1. 加载Spring配置文件，启用组件扫描
        System.out.println(">>> 步骤1：加载Spring配置文件并扫描注解");
        ApplicationContext context = new ClassPathXmlApplicationContext(
            "spring-lab-experiment4.xml"
        );
        System.out.println(">>> Spring容器创建成功！");
        System.out.println();
        
        // 2. 获取Bean（通过@Service注解自动注册）
        System.out.println(">>> 步骤2：从容器获取CustomerService Bean");
        CustomerService customerService = (CustomerService) context.getBean("labCustomerService");
        System.out.println(">>> 获取Bean成功！");
        System.out.println();
        
        // 3. 显示服务配置信息
        System.out.println(">>> 步骤3：显示服务配置信息（@Value注入的属性）");
        customerService.showServiceInfo();
        System.out.println();
        
        // 4. 调用业务方法，验证依赖注入是否成功
        System.out.println(">>> 步骤4：调用业务方法");
        System.out.println("--------------------------------------------------");
        String customerInfo = customerService.getCustomerInfo(4001);
        System.out.println(">>> 查询结果：" + customerInfo);
        System.out.println();
        
        customerService.registerCustomer("王五", "13900139000");
        System.out.println(">>> 客户注册完成");
        System.out.println("--------------------------------------------------");
        
        System.out.println();
        System.out.println("【实验总结】");
        System.out.println("1. 使用@Repository标注数据访问层组件");
        System.out.println("2. 使用@Service标注业务层组件");
        System.out.println("3. 使用@Autowired自动装配依赖对象");
        System.out.println("4. 使用@Value注入配置属性值（支持默认值）");
        System.out.println("5. 使用<context:component-scan>启用组件扫描");
        System.out.println("6. 使用<context:property-placeholder>加载属性文件");
        System.out.println("7. 注解方式配置简洁，开发效率高，是现代Spring开发的主流方式");
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║    实验4完成！                                    ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }
}
