# 快速修复指南

## 问题描述
启动项目时出现错误：`java.lang.ClassNotFoundException: org.apache.commons.fileupload.FileItemFactory`

## 解决方案

### 1. 添加缺失的依赖（已完成）
已在 `pom.xml` 中添加以下依赖：
```xml
<!-- 文件上传 -->
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.4</version>
</dependency>
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.11.0</version>
</dependency>
```

### 2. 重新构建项目
在项目根目录执行以下命令：
```bash
mvn clean install
```

或者在IDE中：
- **IntelliJ IDEA**: 点击右侧 Maven 面板 -> 点击刷新按钮 -> 执行 clean 和 install
- **Eclipse**: 右键项目 -> Maven -> Update Project

### 3. 重新部署到Tomcat
1. 清理Tomcat的部署目录
2. 重新部署war包
3. 重启Tomcat服务器

### 4. 正确的访问路径

#### 学生注册功能入口：
```
http://localhost:8888/Study_ssm_war_exploded/zhuce
```

#### 其他可用路径：
- 首页：`http://localhost:8888/Study_ssm_war_exploded/`
- 用户API：`http://localhost:8888/Study_ssm_war_exploded/user/users`
- 学生API：`http://localhost:8888/Study_ssm_war_exploded/student/students`

## 注意事项
1. **不要直接访问** `/jsp/register.jsp`，因为JSP文件在 `/WEB-INF/jsp/` 目录下，不能直接访问
2. 必须通过控制器 `/zhuce` 来访问注册页面
3. 确保数据库连接正常（检查 `db.properties` 配置）

## 验证步骤
1. 访问 `http://localhost:8888/Study_ssm_war_exploded/zhuce`
2. 填写注册表单（学号、姓名、班级、电话、邮箱、出生日期）
3. 提交表单
4. 查看显示页面是否正确显示提交的信息

## 如果问题仍然存在
1. 检查 Tomcat 的 lib 目录是否包含所需的 jar 包
2. 清理浏览器缓存
3. 查看 Tomcat 日志获取更多错误信息
