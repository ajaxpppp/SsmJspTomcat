package com.study.ssm.service.impl;

import com.study.ssm.entity.Student;
import com.study.ssm.mapper.StudentMapper;
import com.study.ssm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 学生服务实现类
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    
    @Autowired
    private StudentMapper studentMapper;
    
    @Override
    public Student getStudentWithClazzById(Integer id) {
        if (id == null) {
            return null;
        }
        return studentMapper.selectStudentWithClazzById(id);
    }
    
    @Override
    public List<Student> getAllStudentsWithClazz() {
        return studentMapper.selectAllStudentsWithClazz();
    }
    
    @Override
    public List<Student> getStudentsByClazzId(Integer clazzId) {
        if (clazzId == null) {
            return null;
        }
        return studentMapper.selectStudentsByClazzId(clazzId);
    }
    
    @Override
    public Student getStudentWithClazzByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        return studentMapper.selectStudentWithClazzByName(name);
    }
    
    @Override
    public boolean addStudent(Student student) {
        if (student == null) {
            return false;
        }
        try {
            int result = studentMapper.insertStudent(student);
            return result > 0;
        } catch (Exception e) {
            logger.error("新增学生失败: {}", student, e);
            return false;
        }
    }
    
    @Override
    public boolean updateStudent(Student student) {
        if (student == null || student.getId() == null) {
            return false;
        }
        try {
            int result = studentMapper.updateStudent(student);
            return result > 0;
        } catch (Exception e) {
            logger.error("更新学生失败: {}", student, e);
            return false;
        }
    }
    
    @Override
    public boolean deleteStudent(Integer id) {
        if (id == null) {
            return false;
        }
        try {
            int result = studentMapper.deleteStudent(id);
            return result > 0;
        } catch (Exception e) {
            logger.error("删除学生失败, id={}", id, e);
            return false;
        }
    }
}
