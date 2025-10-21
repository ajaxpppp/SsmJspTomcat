package com.study.ssm.mapper;

import com.study.ssm.entity.Article;
import java.util.List;

/**
 * 商品Mapper接口
 */
public interface ArticleMapper {
    
    /**
     * 根据ID查询商品信息
     * @param id 商品ID
     * @return 商品信息
     */
    Article selectArticleById(Integer id);
    
    /**
     * 查询所有商品
     * @return 商品列表
     */
    List<Article> selectAllArticles();
    
    /**
     * 根据订单ID查询商品列表
     * @param orderId 订单ID
     * @return 商品列表
     */
    List<Article> selectArticlesByOrderId(Integer orderId);
    
    /**
     * 插入商品
     * @param article 商品信息
     * @return 影响行数
     */
    int insertArticle(Article article);
    
    /**
     * 更新商品
     * @param article 商品信息
     * @return 影响行数
     */
    int updateArticle(Article article);
    
    /**
     * 删除商品
     * @param id 商品ID
     * @return 影响行数
     */
    int deleteArticle(Integer id);
}
