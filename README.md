# Study SSM Project

这是一个基于Spring + Spring MVC + MyBatis的Java Web项目示例。

## 项目结构

```
Study_ssm/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── study/
│   │   │           └── ssm/
│   │   │               ├── controller/     # 控制器层
│   │   │               ├── service/        # 服务层
│   │   │               │   └── impl/       # 服务实现类
│   │   │               ├── mapper/         # 数据访问层
│   │   │               └── entity/         # 实体类
│   │   ├── resources/
│   │   │   ├── mapper/                     # MyBatis映射文件
│   │   │   ├── applicationContext.xml      # Spring配置文件
│   │   │   ├── spring-mvc.xml              # Spring MVC配置文件
│   │   │   ├── mybatis-config.xml          # MyBatis配置文件
│   │   │   └── jdbc.properties             # 数据库配置文件
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   ├── web.xml                 # Web应用配置文件
│   │       │   └── views/                  # JSP视图文件目录
│   │       └── index.jsp                   # 首页
│   └── test/
│       └── java/                           # 测试代码目录
└── pom.xml                                 # Maven配置文件
```

## 技术栈

- **Spring Framework 5.3.21** - 核心框架
- **Spring MVC** - Web框架
- **MyBatis 3.5.10** - 持久层框架
- **MySQL 8.0.29** - 数据库
- **Druid 1.2.11** - 数据库连接池
- **Jackson 2.13.3** - JSON处理
- **Maven** - 项目管理工具

## 环境要求

- JDK 8+
- Maven 3.6+
- MySQL 5.7+
- Tomcat 8.5+

## 快速开始

### 1. 数据库准备

创建数据库和用户表：

```sql
-- 创建数据库
CREATE DATABASE study_ssm CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE study_ssm;

-- 创建用户表
CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    email VARCHAR(100) COMMENT '邮箱',
    phone VARCHAR(20) COMMENT '手机号',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 插入测试数据
INSERT INTO user (username, password, email, phone) VALUES
('admin', '123456', 'admin@example.com', '13800138000'),
('user1', '123456', 'user1@example.com', '13800138001'),
('user2', '123456', 'user2@example.com', '13800138002');
```

### 2. 配置数据库连接

修改 `src/main/resources/jdbc.properties` 文件中的数据库连接信息：

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/study_ssm?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
jdbc.username=root
jdbc.password=你的数据库密码
```

### 3. 编译和部署

```bash
# 编译项目
mvn clean compile

# 打包项目
mvn clean package

# 部署到Tomcat
# 将target目录下的Study_ssm.war文件部署到Tomcat的webapps目录
```

### 4. 访问应用

启动Tomcat后，访问：`http://localhost:8080/Study_ssm/`

## API接口

### 用户管理接口

| 方法 | URL | 描述 | 参数 |
|------|-----|------|------|
| GET | `/user/users` | 获取所有用户 | 无 |
| GET | `/user/{id}` | 根据ID获取用户 | id: 用户ID |
| POST | `/user/add` | 添加新用户 | JSON格式的用户信息 |
| PUT | `/user/update` | 更新用户信息 | JSON格式的用户信息 |
| DELETE | `/user/{id}` | 删除用户 | id: 用户ID |
| POST | `/user/login` | 用户登录 | username, password |
| GET | `/user/page` | 分页查询用户 | page, size |

### 请求示例

#### 添加用户
```bash
curl -X POST http://localhost:8080/Study_ssm/user/add \
  -H "Content-Type: application/json" \
  -d '{
    "username": "newuser",
    "password": "123456",
    "email": "newuser@example.com",
    "phone": "13800138888"
  }'
```

#### 用户登录
```bash
curl -X POST http://localhost:8080/Study_ssm/user/login \
  -d "username=admin&password=123456"
```

#### 获取所有用户
```bash
curl http://localhost:8080/Study_ssm/user/users
```

## 项目特点

1. **标准的三层架构**：Controller -> Service -> Mapper
2. **完整的配置文件**：Spring、Spring MVC、MyBatis配置
3. **统一的异常处理**：全局异常处理机制
4. **JSON响应格式**：统一的API响应格式
5. **事务管理**：基于注解的声明式事务
6. **连接池配置**：Druid数据库连接池
7. **RESTful API**：符合REST规范的接口设计

## 开发说明

### 添加新功能

1. 在 `entity` 包中创建实体类
2. 在 `mapper` 包中创建Mapper接口
3. 在 `resources/mapper` 目录中创建对应的XML映射文件
4. 在 `service` 包中创建服务接口和实现类
5. 在 `controller` 包中创建控制器类

### 配置说明

- **applicationContext.xml**：Spring核心配置，包括数据源、事务管理等
- **spring-mvc.xml**：Spring MVC配置，包括视图解析器、静态资源处理等
- **mybatis-config.xml**：MyBatis配置，包括类型别名、插件等
- **web.xml**：Web应用配置，包括DispatcherServlet、过滤器等

## 常见问题

1. **数据库连接失败**：检查jdbc.properties中的数据库连接信息
2. **404错误**：检查URL映射和控制器注解
3. **500错误**：查看日志文件，通常是配置或代码问题
4. **中文乱码**：确保所有配置文件都使用UTF-8编码

## 许可证

MIT License
