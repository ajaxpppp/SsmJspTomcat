package com.study.ssm.service.impl;

import com.study.ssm.entity.StudentRegister;
import com.study.ssm.mapper.StudentRegisterMapper;
import com.study.ssm.service.StudentRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentRegisterServiceImpl implements StudentRegisterService {

    @Autowired
    private StudentRegisterMapper studentRegisterMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(StudentRegister student) {
        return studentRegisterMapper.insert(student) > 0;
    }

    @Override
    public List<StudentRegister> listAll() {
        return studentRegisterMapper.findAll();
    }
}
