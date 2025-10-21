package com.study.ssm.mapper;

import com.study.ssm.entity.Item;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 订单明细Mapper接口
 */
public interface ItemMapper {
    
    /**
     * 根据订单ID查询订单明细
     * @param orderId 订单ID
     * @return 订单明细列表
     */
    List<Item> selectItemsByOrderId(Integer orderId);
    
    /**
     * 根据订单ID查询订单明细（包含商品信息）
     * @param orderId 订单ID
     * @return 订单明细列表（包含商品信息）
     */
    List<Item> selectItemsWithArticleByOrderId(Integer orderId);
    
    /**
     * 插入订单明细
     * @param item 订单明细
     * @return 影响行数
     */
    int insertItem(Item item);
    
    /**
     * 更新订单明细
     * @param item 订单明细
     * @return 影响行数
     */
    int updateItem(Item item);
    
    /**
     * 删除订单明细
     * @param orderId 订单ID
     * @param articleId 商品ID
     * @return 影响行数
     */
    int deleteItem(@Param("orderId") Integer orderId, @Param("articleId") Integer articleId);
    
    /**
     * 根据订单ID删除所有订单明细
     * @param orderId 订单ID
     * @return 影响行数
     */
    int deleteItemsByOrderId(Integer orderId);
}
