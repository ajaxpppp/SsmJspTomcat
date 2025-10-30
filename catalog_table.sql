-- 图书分类表 (catalog)
-- 用于图书管理系统的图书分类管理模块

-- 如果表存在则删除
DROP TABLE IF EXISTS `catalog`;

-- 创建图书分类表
CREATE TABLE `catalog` (
  `catalogid` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID，自动生成',
  `catalogname` VARCHAR(50) NOT NULL COMMENT '分类名称',
  PRIMARY KEY (`catalogid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图书分类表';

-- 插入示例数据
INSERT INTO `catalog` (`catalogname`) VALUES
('小说'),
('科技'),
('历史'),
('艺术'),
('教育'),
('经济'),
('哲学'),
('文学');

-- 查询验证
SELECT * FROM catalog;
