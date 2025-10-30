package com.study.ssm.service.impl;

import com.study.ssm.entity.Kclxb;
import com.study.ssm.mapper.KclxbMapper;
import com.study.ssm.service.KclxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 课程类型服务实现类 - 对应kclxb表
 */
@Service
@Transactional
public class KclxbServiceImpl implements KclxbService {
    
    private static final Logger logger = LoggerFactory.getLogger(KclxbServiceImpl.class);
    
    @Autowired
    private KclxbMapper kclxbMapper;
    
    @Override
    public Kclxb getKclxbById(Integer id) {
        if (id == null) {
            return null;
        }
        return kclxbMapper.selectKclxbById(id);
    }
    
    @Override
    public List<Kclxb> getAllKclxb() {
        return kclxbMapper.selectAllKclxb();
    }
    
    @Override
    public boolean addKclxb(Kclxb kclxb) {
        if (kclxb == null || kclxb.getLxm() == null || kclxb.getLxm().trim().isEmpty()) {
            return false;
        }
        try {
            int result = kclxbMapper.insertKclxb(kclxb);
            return result > 0;
        } catch (Exception e) {
            logger.error("新增课程类型失败: {}", kclxb, e);
            return false;
        }
    }
    
    @Override
    public boolean updateKclxb(Kclxb kclxb) {
        if (kclxb == null || kclxb.getId() == null) {
            return false;
        }
        try {
            int result = kclxbMapper.updateKclxb(kclxb);
            return result > 0;
        } catch (Exception e) {
            logger.error("更新课程类型失败: {}", kclxb, e);
            return false;
        }
    }
    
    @Override
    public boolean deleteKclxb(Integer id) {
        if (id == null) {
            return false;
        }
        try {
            int result = kclxbMapper.deleteKclxb(id);
            return result > 0;
        } catch (Exception e) {
            logger.error("删除课程类型失败, id={}", id, e);
            return false;
        }
    }
}
