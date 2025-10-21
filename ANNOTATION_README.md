# MyBatis注解方式实现 - 用户管理与订单懒加载

## 功能概述
在不改变原有代码的基础上，使用MyBatis注解方式新增以下功能：
1. ✅ 对tb_user表的增删改查操作（User类属性：id、username、password、phone、address）
2. ✅ 从tb_order表查询订单信息，利用懒加载包含用户信息和商品信息

## 新增文件结构

### 1. POJO类（实体类）
```
src/main/java/com/study/ssm/entity/
├── UserAnnotation.java      # 用户实体类（注解版本）
└── OrderAnnotation.java     # 订单实体类（注解版本）
```

#### UserAnnotation.java 关键代码
```java
@Data
public class UserAnnotation {
    private Integer id;
    private String username;    // 符合新要求的属性名
    private String password;    
    private String phone;       
    private String address;     
    private List<Order> orders; // 一对多关系
}
```

#### OrderAnnotation.java 关键代码
```java
@Data
public class OrderAnnotation {
    private Integer id;
    private String code;
    private BigDecimal total;
    private Integer userId;
    private UserAnnotation user;  // 多对一关系（懒加载）
    private List<Item> items;     // 多对多关系（懒加载）
}
```

### 2. Mapper接口（注解方式）
```
src/main/java/com/study/ssm/mapper/
├── UserAnnotationMapper.java     # 用户Mapper接口（注解版）
└── OrderAnnotationMapper.java    # 订单Mapper接口（注解版）
```

#### UserAnnotationMapper.java 核心注解
```java
public interface UserAnnotationMapper {
    
    // 1. 增加操作
    @Insert("INSERT INTO tb_user(username, password, phone, address) " +
            "VALUES(#{username}, #{password}, #{phone}, #{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(UserAnnotation user);
    
    // 2. 删除操作
    @Delete("DELETE FROM tb_user WHERE id = #{id}")
    int deleteUserById(@Param("id") Integer id);
    
    // 3. 修改操作
    @Update("UPDATE tb_user SET username = #{username}, password = #{password}, " +
            "phone = #{phone}, address = #{address} WHERE id = #{id}")
    int updateUser(UserAnnotation user);
    
    // 4. 查询操作
    @Select("SELECT id, username, password, phone, address FROM tb_user WHERE id = #{id}")
    @Results(id = "userResultMap", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "username", column = "username"),
        @Result(property = "password", column = "password"),
        @Result(property = "phone", column = "phone"),
        @Result(property = "address", column = "address")
    })
    UserAnnotation selectUserById(@Param("id") Integer id);
}
```

#### OrderAnnotationMapper.java 懒加载注解配置
```java
public interface OrderAnnotationMapper {
    
    // 查询订单，懒加载用户和商品信息
    @Select("SELECT id, code, total, user_id FROM tb_order WHERE id = #{id}")
    @Results(id = "orderWithUserAndItemsMap", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "code", column = "code"),
        @Result(property = "total", column = "total"),
        @Result(property = "userId", column = "user_id"),
        // 一对一懒加载：@One注解
        @Result(property = "user", column = "user_id",
                one = @One(select = "com.study.ssm.mapper.UserAnnotationMapper.selectUserById",
                          fetchType = FetchType.LAZY)),
        // 一对多懒加载：@Many注解
        @Result(property = "items", column = "id",
                many = @Many(select = "com.study.ssm.mapper.ItemMapper.selectItemsWithArticleByOrderId",
                            fetchType = FetchType.LAZY))
    })
    OrderAnnotation selectOrderWithUserAndItems(@Param("id") Integer id);
}
```

### 3. 测试类
```
src/test/java/com/study/ssm/test/annotation/
├── UserAnnotationTest.java              # 用户CRUD测试
└── OrderAnnotationLazyLoadTest.java     # 订单懒加载测试
```

## 注解方式 vs XML配置方式对比

| 特性 | XML配置 | 注解方式 |
|------|---------|----------|
| **懒加载配置** | `<association fetchType="lazy">` | `@One(fetchType = FetchType.LAZY)` |
| **一对多关系** | `<collection fetchType="lazy">` | `@Many(fetchType = FetchType.LAZY)` |
| **结果映射** | `<resultMap>` | `@Results` + `@Result` |
| **SQL语句** | 在XML文件中 | 在`@Select`/`@Insert`等注解中 |
| **动态SQL** | 支持完整的动态SQL | 使用`<script>`标签支持 |
| **代码位置** | 分离到XML文件 | 直接在接口方法上 |

## 运行测试

### 1. 测试用户CRUD操作
```bash
# 运行用户注解测试
mvn test -Dtest=UserAnnotationTest

# 或运行特定测试方法
mvn test -Dtest=UserAnnotationTest#testCompleteCRUD
```

### 2. 测试订单懒加载
```bash
# 运行订单懒加载测试
mvn test -Dtest=OrderAnnotationLazyLoadTest

# 推荐运行核心测试方法
mvn test -Dtest=OrderAnnotationLazyLoadTest#testSelectOrderWithUserAndItems
```

## 懒加载效果验证

运行测试时，观察控制台SQL日志：

```
步骤1：查询订单基本信息
SQL: SELECT id, code, total, user_id FROM tb_order WHERE id = ?

步骤2：访问user属性时触发懒加载
SQL: SELECT id, username, password, phone, address FROM tb_user WHERE id = ?

步骤3：访问items属性时触发懒加载
SQL: SELECT * FROM tb_item JOIN tb_article WHERE order_id = ?
```

## 关键注解说明

### 1. CRUD操作注解
- `@Select` - 查询操作
- `@Insert` - 插入操作
- `@Update` - 更新操作
- `@Delete` - 删除操作
- `@Options` - 配置选项（如自动生成主键）

### 2. 结果映射注解
- `@Results` - 定义结果映射
- `@Result` - 单个字段映射
- `@ResultMap` - 引用已定义的结果映射

### 3. 懒加载注解
- `@One` - 一对一或多对一关系
- `@Many` - 一对多关系
- `fetchType = FetchType.LAZY` - 启用懒加载

### 4. 参数注解
- `@Param` - 指定参数名称

## 项目特点

1. **完全独立**：注解版本的代码与原有XML配置代码完全独立，互不影响
2. **功能完整**：实现了完整的CRUD操作和懒加载功能
3. **代码简洁**：使用注解方式，代码更加紧凑
4. **易于测试**：提供了完整的测试类，可直接运行验证
5. **对比清晰**：测试类中包含了XML和注解方式的对比说明

## 注意事项

1. 注解方式适合简单SQL，复杂SQL建议使用XML配置
2. 懒加载需要在同一个SqlSession中才能生效
3. 使用`@Results`定义的结果映射可以通过id被`@ResultMap`引用
4. 动态SQL在注解中需要使用`<script>`标签包裹
