-- 创建数据库
# CREATE DATABASE databa;

-- 使用数据库
USE database_setup;

-- 创建tb_user表
CREATE TABLE tb_user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(18) DEFAULT NULL,
    sex CHAR(2) DEFAULT NULL,
    age INT DEFAULT NULL
);

-- 插入测试数据
INSERT INTO tb_user (name, sex, age) VALUES
('张三', '男', 25),
('李四', '女', 23),
('王五', '男', 28),
('赵六', '女', 22);

-- 创建tb_employee表
CREATE TABLE tb_employee(
    id INT PRIMARY KEY AUTO_INCREMENT,
    loginname VARCHAR(18),
    password VARCHAR(18),
    name VARCHAR(18),
    sex CHAR(2),
    age INT,
    phone VARCHAR(21),
    sal DECIMAL(12,2),
    state VARCHAR(18)
);

-- 插入员工测试数据（10条中文数据）
INSERT INTO tb_employee (loginname, password, name, sex, age, phone, sal, state) VALUES
('zhangsan', '123456', '张三', '男', 25, '13800138001', 5000.00, '在职'),
('lisi', '123456', '李四', '女', 23, '13800138002', 4500.00, '在职'),
('wangwu', '123456', '王五', '男', 28, '13800138003', 6000.00, '在职'),
('zhaoliu', '123456', '赵六', '女', 22, '', 4000.00, '离职'),
('qianqi', '123456', '钱七', '男', 30, '13800138005', 7000.00, '在职'),
('sunba', '123456', '孙八', '女', 26, '13900139006', 5200.00, '在职'),
('zhoujiu', '123456', '周九', '男', 32, '13700137007', 7500.00, '在职'),
('wushi', '123456', '吴十', '女', 24, '13600136008', 4800.00, '试用期'),
('zhengyi', '123456', '郑一', '男', 29, '13500135009', 6200.00, '在职'),
('chener', '123456', '陈二', '女', 27, '13400134010', 5500.00, '在职');

-- 创建tb_clazz表（班级表）
CREATE TABLE tb_clazz(
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(18),
    name VARCHAR(18)
);

-- 插入班级测试数据
INSERT INTO tb_clazz(code, name) VALUES('j1601', '计科1班');
INSERT INTO tb_clazz(code, name) VALUES('j1602', '计科2班');

-- 创建tb_student表（学生表）
CREATE TABLE tb_student(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(18),
    sex VARCHAR(18),
    age INT,
    clazz_id INT,
    FOREIGN KEY(clazz_id) REFERENCES tb_clazz(id)
);

-- 插入学生测试数据
INSERT INTO tb_student(name, sex, age, clazz_id) VALUES('jack', '男', 23, 1);
INSERT INTO tb_student(name, sex, age, clazz_id) VALUES('rose', '女', 18, 1);
INSERT INTO tb_student(name, sex, age, clazz_id) VALUES('tom', '男', 21, 2);
INSERT INTO tb_student(name, sex, age, clazz_id) VALUES('alice', '女', 20, 2);

-- ========================================
-- 订单管理系统相关表
-- ========================================

-- 修改tb_user表，添加订单系统需要的字段
ALTER TABLE tb_user 
ADD COLUMN username VARCHAR(18) AFTER name,
ADD COLUMN loginname VARCHAR(18) AFTER username,
ADD COLUMN password VARCHAR(18) AFTER loginname,
ADD COLUMN phone VARCHAR(18) AFTER age,
ADD COLUMN address VARCHAR(18) AFTER phone;

-- 更新现有用户数据
UPDATE tb_user SET 
    username = name,
    loginname = LOWER(name),
    password = '123456',
    phone = '13920001616',
    address = '广州'
WHERE id = 1;

-- 创建tb_article表（商品表）
CREATE TABLE IF NOT EXISTS tb_article(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(18),
    price DECIMAL(10,2),
    remark VARCHAR(50)
);

-- 插入商品测试数据
INSERT INTO tb_article(name, price, remark) VALUES
('疯狂Java讲义', 108.9, '李刚老师经典著作'),
('疯狂Android讲义', 99.9, '李刚老师经典著作'),
('疯狂iOS讲义', 89.9, '李刚老师经典著作'),
('SpringMVC企业开发', 69.9, '肖文吉老师经典著作');

-- 创建tb_order表（订单表）
CREATE TABLE IF NOT EXISTS tb_order(
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(32),
    total DECIMAL(18,2),
    user_id INT,
    FOREIGN KEY(user_id) REFERENCES tb_user(id)
);

-- 插入订单测试数据
INSERT INTO tb_order(code, total, user_id) VALUES
('6aa3fa359ff14619b77fab5990940a2d', 388.6, 1),
('6aa3fa359ff14619b77fab5990940b3c', 248.7, 1);

-- 创建tb_item表（订单明细表）
CREATE TABLE IF NOT EXISTS tb_item(
    order_id INT,
    article_id INT,
    amount INT,
    PRIMARY KEY(order_id, article_id),
    FOREIGN KEY(order_id) REFERENCES tb_order(id),
    FOREIGN KEY(article_id) REFERENCES tb_article(id)
);

-- 插入订单明细测试数据
INSERT INTO tb_item VALUES
(1, 1, 1),
(1, 2, 1),
(1, 3, 2),
(2, 4, 2),
(2, 1, 1);


CREATE TABLE IF NOT EXISTS tb_student_register (
  id INT PRIMARY KEY AUTO_INCREMENT,
  student_id VARCHAR(32) NOT NULL,
  name VARCHAR(64) NOT NULL,
  class_name VARCHAR(128) NOT NULL,
  phone VARCHAR(32) NOT NULL,
  email VARCHAR(128) NOT NULL,
  birth_date DATE NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
