package com.study.ssm.service.impl;

import com.study.ssm.entity.Catalog;
import com.study.ssm.mapper.CatalogMapper;
import com.study.ssm.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 图书分类服务实现类 - 对应catalog表
 */
@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {
    
    private static final Logger logger = LoggerFactory.getLogger(CatalogServiceImpl.class);
    
    @Autowired
    private CatalogMapper catalogMapper;
    
    @Override
    public Catalog getCatalogById(Integer catalogid) {
        if (catalogid == null) {
            return null;
        }
        return catalogMapper.selectCatalogById(catalogid);
    }
    
    @Override
    public List<Catalog> getAllCatalog() {
        return catalogMapper.selectAllCatalog();
    }
    
    @Override
    public boolean addCatalog(Catalog catalog) {
        if (catalog == null || catalog.getCatalogname() == null || catalog.getCatalogname().trim().isEmpty()) {
            return false;
        }
        try {
            int result = catalogMapper.insertCatalog(catalog);
            return result > 0;
        } catch (Exception e) {
            logger.error("新增图书分类失败: {}", catalog, e);
            return false;
        }
    }
    
    @Override
    public boolean updateCatalog(Catalog catalog) {
        if (catalog == null || catalog.getCatalogid() == null) {
            return false;
        }
        try {
            int result = catalogMapper.updateCatalog(catalog);
            return result > 0;
        } catch (Exception e) {
            logger.error("更新图书分类失败: {}", catalog, e);
            return false;
        }
    }
    
    @Override
    public boolean deleteCatalog(Integer catalogid) {
        if (catalogid == null) {
            return false;
        }
        try {
            int result = catalogMapper.deleteCatalog(catalogid);
            return result > 0;
        } catch (Exception e) {
            logger.error("删除图书分类失败, catalogid={}", catalogid, e);
            return false;
        }
    }
}
