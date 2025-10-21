package com.study.ssm.service;

import com.study.ssm.entity.StudentRegister;

import java.util.List;

public interface StudentRegisterService {

    boolean register(StudentRegister student);

    List<StudentRegister> listAll();
}
