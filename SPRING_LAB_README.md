# Spring框架实验说明

## 实验位置

所有实验代码位于 `com.study.ssm.lab` 包下，完全独立，不影响原有SSM项目功能。

## 项目结构

```
src/main/java/com/study/ssm/lab/
├── experiment1/          # 实验1：Spring简单应用
│   ├── dao/             # 数据访问层
│   └── service/         # 业务层
├── experiment2/          # 实验2：Setter注入
│   ├── dao/
│   └── service/
├── experiment3/          # 实验3：构造注入
│   ├── dao/
│   └── service/
└── experiment4/          # 实验4：基于注解的装配
    ├── dao/
    └── service/

src/main/resources/
├── spring-lab-experiment1.xml    # 实验1配置
├── spring-lab-experiment2.xml    # 实验2配置
├── spring-lab-experiment3.xml    # 实验3配置
├── spring-lab-experiment4.xml    # 实验4配置
└── lab-experiment4.properties    # 实验4属性配置

src/test/java/com/study/ssm/lab/
├── Test_Experiment1.java         # 实验1测试
├── Test_Experiment2.java         # 实验2测试
├── Test_Experiment3.java         # 实验3测试
├── Test_Experiment4.java         # 实验4测试
└── Test_AllExperiments.java      # 综合测试
```

## 运行方式

### 方式1：在IDE中运行单个实验

右键点击测试类，选择"Run As" → "JUnit Test"：
- `Test_Experiment1` - 运行实验1
- `Test_Experiment2` - 运行实验2
- `Test_Experiment3` - 运行实验3
- `Test_Experiment4` - 运行实验4

### 方式2：运行所有实验

右键点击 `Test_AllExperiments.java`，选择"Run As" → "JUnit Test"

### 方式3：使用Maven命令

```bash
# 运行所有测试
mvn test

# 运行指定测试类
mvn test -Dtest=Test_Experiment1
mvn test -Dtest=Test_Experiment2
mvn test -Dtest=Test_Experiment3
mvn test -Dtest=Test_Experiment4
mvn test -Dtest=Test_AllExperiments
```

## 实验内容

### 实验1：Spring的简单应用
- **目标**：理解Spring IoC容器的基本使用
- **内容**：通过XML配置Bean，实现依赖注入
- **配置文件**：`spring-lab-experiment1.xml`

### 实验2：Setter注入（设置注入）
- **目标**：掌握通过Setter方法注入依赖
- **内容**：注入对象引用和多种基本类型属性
- **配置文件**：`spring-lab-experiment2.xml`
- **特点**：灵活，可选择性注入

### 实验3：构造注入
- **目标**：掌握通过构造函数注入依赖
- **内容**：在对象创建时注入所有依赖
- **配置文件**：`spring-lab-experiment3.xml`
- **特点**：保证对象完整性，适合必需依赖

### 实验4：基于注解的装配
- **目标**：掌握使用注解进行依赖注入
- **内容**：使用@Repository、@Service、@Autowired、@Value等注解
- **配置文件**：`spring-lab-experiment4.xml` + `lab-experiment4.properties`
- **特点**：配置简洁，现代Spring开发主流方式

## 三种注入方式对比

| 特性 | Setter注入 | 构造注入 | 注解装配 |
|------|-----------|---------|---------|
| 配置方式 | `<property>` | `<constructor-arg>` | `@Autowired` |
| 灵活性 | 高 | 中 | 高 |
| 依赖完整性 | 弱 | 强 | 中 |
| 适用场景 | 可选依赖 | 必需依赖 | 现代开发 |

## 注意事项

1. **不影响原项目**：所有实验代码在独立的`lab`包下
2. **Bean命名**：所有实验Bean都加了`lab`前缀，避免与原项目Bean冲突
3. **配置文件**：使用独立的配置文件，不影响原有配置
4. **依赖管理**：使用项目已有的Spring依赖，无需额外添加

## 实验报告

运行实验后，可以将控制台输出截图，作为实验报告的运行结果部分。

每个实验都包含：
- Bean创建过程输出
- 依赖注入过程输出
- 业务方法调用输出
- 实验总结输出

## 常见问题

**Q: 运行测试时找不到配置文件？**
A: 确保配置文件在`src/main/resources`目录下，Maven会自动将其复制到classpath。

**Q: 注解扫描不生效？**
A: 检查`spring-lab-experiment4.xml`中的`<context:component-scan>`配置是否正确。

**Q: 属性注入显示默认值？**
A: 检查`lab-experiment4.properties`文件是否存在且路径正确。

## 联系方式

如有问题，请查看项目文档或联系开发者。
