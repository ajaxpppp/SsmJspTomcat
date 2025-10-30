package com.study.ssm.mapper;

import com.study.ssm.entity.Catalog;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 图书分类数据访问接口 - 对应catalog表
 */
public interface CatalogMapper {
    
    /**
     * 根据ID查询图书分类信息
     * @param catalogid 主键ID
     * @return 图书分类对象
     */
    Catalog selectCatalogById(@Param("catalogid") Integer catalogid);
    
    /**
     * 查询所有图书分类信息
     * @return 图书分类列表
     */
    List<Catalog> selectAllCatalog();
    
    /**
     * 插入图书分类信息
     * @param catalog 图书分类对象
     * @return 影响行数
     */
    int insertCatalog(Catalog catalog);
    
    /**
     * 更新图书分类信息
     * @param catalog 图书分类对象
     * @return 影响行数
     */
    int updateCatalog(Catalog catalog);
    
    /**
     * 根据ID删除图书分类
     * @param catalogid 主键ID
     * @return 影响行数
     */
    int deleteCatalog(@Param("catalogid") Integer catalogid);
}
