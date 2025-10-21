package com.study.ssm.service.impl;

import com.study.ssm.entity.Clazz;
import com.study.ssm.mapper.ClazzMapper;
import com.study.ssm.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 班级服务实现类
 */
@Service
@Transactional
public class ClazzServiceImpl implements ClazzService {
    
    private static final Logger logger = LoggerFactory.getLogger(ClazzServiceImpl.class);
    
    @Autowired
    private ClazzMapper clazzMapper;
    
    @Override
    public Clazz getClazzWithStudentsById(Integer id) {
        if (id == null) {
            return null;
        }
        return clazzMapper.selectClazzWithStudentsById(id);
    }
    
    @Override
    public List<Clazz> getAllClazzWithStudents() {
        return clazzMapper.selectAllClazzWithStudents();
    }
    
    @Override
    public Clazz getClazzWithStudentsByCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            return null;
        }
        return clazzMapper.selectClazzWithStudentsByCode(code);
    }
    
    @Override
    public Clazz getClazzWithStudentsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        return clazzMapper.selectClazzWithStudentsByName(name);
    }
    
    @Override
    public boolean addClazz(Clazz clazz) {
        if (clazz == null) {
            return false;
        }
        try {
            int result = clazzMapper.insertClazz(clazz);
            return result > 0;
        } catch (Exception e) {
            logger.error("新增班级失败: {}", clazz, e);
            return false;
        }
    }
    
    @Override
    public boolean updateClazz(Clazz clazz) {
        if (clazz == null || clazz.getId() == null) {
            return false;
        }
        try {
            int result = clazzMapper.updateClazz(clazz);
            return result > 0;
        } catch (Exception e) {
            logger.error("更新班级失败: {}", clazz, e);
            return false;
        }
    }
    
    @Override
    public boolean deleteClazz(Integer id) {
        if (id == null) {
            return false;
        }
        try {
            int result = clazzMapper.deleteClazz(id);
            return result > 0;
        } catch (Exception e) {
            logger.error("删除班级失败, id={}", id, e);
            return false;
        }
    }
}
