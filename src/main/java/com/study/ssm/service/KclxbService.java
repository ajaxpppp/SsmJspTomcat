package com.study.ssm.service;

import com.study.ssm.entity.Kclxb;
import java.util.List;

/**
 * 课程类型服务接口 - 对应kclxb表
 */
public interface KclxbService {
    
    /**
     * 根据ID查询课程类型信息
     * @param id 主键ID
     * @return 课程类型对象
     */
    Kclxb getKclxbById(Integer id);
    
    /**
     * 查询所有课程类型信息
     * @return 课程类型列表
     */
    List<Kclxb> getAllKclxb();
    
    /**
     * 添加课程类型信息
     * @param kclxb 课程类型对象
     * @return 是否添加成功
     */
    boolean addKclxb(Kclxb kclxb);
    
    /**
     * 更新课程类型信息
     * @param kclxb 课程类型对象
     * @return 是否更新成功
     */
    boolean updateKclxb(Kclxb kclxb);
    
    /**
     * 根据ID删除课程类型
     * @param id 主键ID
     * @return 是否删除成功
     */
    boolean deleteKclxb(Integer id);
}
