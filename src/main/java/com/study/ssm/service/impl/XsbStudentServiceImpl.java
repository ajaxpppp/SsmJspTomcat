package com.study.ssm.service.impl;

import com.study.ssm.entity.XsbStudent;
import com.study.ssm.mapper.XsbStudentMapper;
import com.study.ssm.service.XsbStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 学生服务实现类 - 对应xsb表
 */
@Service
@Transactional
public class XsbStudentServiceImpl implements XsbStudentService {
    
    private static final Logger logger = LoggerFactory.getLogger(XsbStudentServiceImpl.class);
    
    @Autowired
    private XsbStudentMapper xsbStudentMapper;
    
    @Override
    public XsbStudent getStudentBySno(String sno) {
        if (sno == null || sno.trim().isEmpty()) {
            return null;
        }
        return xsbStudentMapper.selectStudentBySno(sno);
    }
    
    @Override
    public List<XsbStudent> getAllStudents() {
        return xsbStudentMapper.selectAllStudents();
    }
    
    @Override
    public boolean addStudent(XsbStudent student) {
        if (student == null || student.getSno() == null || student.getSno().trim().isEmpty()) {
            return false;
        }
        try {
            int result = xsbStudentMapper.insertStudent(student);
            return result > 0;
        } catch (Exception e) {
            logger.error("新增学生失败: {}", student, e);
            return false;
        }
    }
    
    @Override
    public boolean updateStudent(XsbStudent student) {
        if (student == null || student.getSno() == null || student.getSno().trim().isEmpty()) {
            return false;
        }
        try {
            int result = xsbStudentMapper.updateStudent(student);
            return result > 0;
        } catch (Exception e) {
            logger.error("更新学生失败: {}", student, e);
            return false;
        }
    }
    
    @Override
    public boolean deleteStudent(String sno) {
        if (sno == null || sno.trim().isEmpty()) {
            return false;
        }
        try {
            int result = xsbStudentMapper.deleteStudent(sno);
            return result > 0;
        } catch (Exception e) {
            logger.error("删除学生失败, sno={}", sno, e);
            return false;
        }
    }
}
