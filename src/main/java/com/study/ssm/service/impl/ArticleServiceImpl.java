package com.study.ssm.service.impl;

import com.study.ssm.entity.Article;
import com.study.ssm.mapper.ArticleMapper;
import com.study.ssm.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品服务实现类
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    
    @Autowired
    private ArticleMapper articleMapper;
    
    @Override
    public Article getArticleById(Integer id) {
        return articleMapper.selectArticleById(id);
    }
    
    @Override
    public List<Article> getAllArticles() {
        return articleMapper.selectAllArticles();
    }
    
    @Override
    public List<Article> getArticlesByOrderId(Integer orderId) {
        return articleMapper.selectArticlesByOrderId(orderId);
    }
    
    @Override
    public boolean createArticle(Article article) {
        return articleMapper.insertArticle(article) > 0;
    }
    
    @Override
    public boolean updateArticle(Article article) {
        return articleMapper.updateArticle(article) > 0;
    }
    
    @Override
    public boolean deleteArticle(Integer id) {
        return articleMapper.deleteArticle(id) > 0;
    }
}
