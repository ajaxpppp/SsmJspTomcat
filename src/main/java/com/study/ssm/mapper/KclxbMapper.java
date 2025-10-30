package com.study.ssm.mapper;

import com.study.ssm.entity.Kclxb;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 课程类型数据访问接口 - 对应kclxb表
 */
public interface KclxbMapper {
    
    /**
     * 根据ID查询课程类型信息
     * @param id 主键ID
     * @return 课程类型对象
     */
    Kclxb selectKclxbById(@Param("id") Integer id);
    
    /**
     * 查询所有课程类型信息
     * @return 课程类型列表
     */
    List<Kclxb> selectAllKclxb();
    
    /**
     * 插入课程类型信息
     * @param kclxb 课程类型对象
     * @return 影响行数
     */
    int insertKclxb(Kclxb kclxb);
    
    /**
     * 更新课程类型信息
     * @param kclxb 课程类型对象
     * @return 影响行数
     */
    int updateKclxb(Kclxb kclxb);
    
    /**
     * 根据ID删除课程类型
     * @param id 主键ID
     * @return 影响行数
     */
    int deleteKclxb(@Param("id") Integer id);
}
