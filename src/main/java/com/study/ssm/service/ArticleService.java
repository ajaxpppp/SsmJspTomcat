package com.study.ssm.service;

import com.study.ssm.entity.Article;
import java.util.List;

/**
 * 商品服务接口
 */
public interface ArticleService {
    
    /**
     * 根据ID查询商品
     * @param id 商品ID
     * @return 商品信息
     */
    Article getArticleById(Integer id);
    
    /**
     * 查询所有商品
     * @return 商品列表
     */
    List<Article> getAllArticles();
    
    /**
     * 根据订单ID查询商品列表
     * @param orderId 订单ID
     * @return 商品列表
     */
    List<Article> getArticlesByOrderId(Integer orderId);
    
    /**
     * 创建商品
     * @param article 商品信息
     * @return 是否成功
     */
    boolean createArticle(Article article);
    
    /**
     * 更新商品
     * @param article 商品信息
     * @return 是否成功
     */
    boolean updateArticle(Article article);
    
    /**
     * 删除商品
     * @param id 商品ID
     * @return 是否成功
     */
    boolean deleteArticle(Integer id);
}
