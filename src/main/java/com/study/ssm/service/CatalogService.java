package com.study.ssm.service;

import com.study.ssm.entity.Catalog;
import java.util.List;

/**
 * 图书分类服务接口 - 对应catalog表
 */
public interface CatalogService {
    
    /**
     * 根据ID查询图书分类信息
     * @param catalogid 主键ID
     * @return 图书分类对象
     */
    Catalog getCatalogById(Integer catalogid);
    
    /**
     * 查询所有图书分类信息
     * @return 图书分类列表
     */
    List<Catalog> getAllCatalog();
    
    /**
     * 添加图书分类信息
     * @param catalog 图书分类对象
     * @return 是否添加成功
     */
    boolean addCatalog(Catalog catalog);
    
    /**
     * 更新图书分类信息
     * @param catalog 图书分类对象
     * @return 是否更新成功
     */
    boolean updateCatalog(Catalog catalog);
    
    /**
     * 根据ID删除图书分类
     * @param catalogid 主键ID
     * @return 是否删除成功
     */
    boolean deleteCatalog(Integer catalogid);
}
