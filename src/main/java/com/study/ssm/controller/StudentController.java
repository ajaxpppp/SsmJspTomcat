package com.study.ssm.controller;

import com.study.ssm.entity.Student;
import com.study.ssm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生控制器
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    /**
     * 根据ID查询学生信息（包含班级信息）
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStudentById(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            Student student = studentService.getStudentWithClazzById(id);
            if (student != null) {
                result.put("success", true);
                result.put("message", "查询成功");
                result.put("data", student);
                return ResponseEntity.ok(result);
            } else {
                result.put("success", false);
                result.put("message", "未找到该学生");
                return ResponseEntity.ok(result);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败：" + e.getMessage());
            return ResponseEntity.ok(result);
        }
    }
    
    /**
     * 查询所有学生信息（包含班级信息）
     */
    @GetMapping("/students")
    public ResponseEntity<Map<String, Object>> getAllStudents() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Student> students = studentService.getAllStudentsWithClazz();
            result.put("success", true);
            result.put("message", "查询成功");
            result.put("data", students);
            result.put("count", students.size());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败：" + e.getMessage());
            return ResponseEntity.ok(result);
        }
    }
    
    /**
     * 根据班级ID查询学生列表（包含班级信息）
     */
    @GetMapping("/clazz/{clazzId}")
    public ResponseEntity<Map<String, Object>> getStudentsByClazzId(@PathVariable Integer clazzId) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Student> students = studentService.getStudentsByClazzId(clazzId);
            result.put("success", true);
            result.put("message", "查询成功");
            result.put("data", students);
            result.put("count", students != null ? students.size() : 0);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败：" + e.getMessage());
            return ResponseEntity.ok(result);
        }
    }
    
    /**
     * 根据学生姓名查询学生信息（包含班级信息）
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<Map<String, Object>> getStudentByName(@PathVariable String name) {
        Map<String, Object> result = new HashMap<>();
        try {
            Student student = studentService.getStudentWithClazzByName(name);
            if (student != null) {
                result.put("success", true);
                result.put("message", "查询成功");
                result.put("data", student);
                return ResponseEntity.ok(result);
            } else {
                result.put("success", false);
                result.put("message", "未找到该学生");
                return ResponseEntity.ok(result);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败：" + e.getMessage());
            return ResponseEntity.ok(result);
        }
    }
    
    /**
     * 添加学生信息
     */
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addStudent(@RequestBody Student student) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = studentService.addStudent(student);
            if (success) {
                result.put("success", true);
                result.put("message", "添加成功");
                result.put("data", student);
                return ResponseEntity.ok(result);
            } else {
                result.put("success", false);
                result.put("message", "添加失败");
                return ResponseEntity.ok(result);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "添加失败：" + e.getMessage());
            return ResponseEntity.ok(result);
        }
    }
    
    /**
     * 更新学生信息
     */
    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateStudent(@RequestBody Student student) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = studentService.updateStudent(student);
            if (success) {
                result.put("success", true);
                result.put("message", "更新成功");
                result.put("data", student);
                return ResponseEntity.ok(result);
            } else {
                result.put("success", false);
                result.put("message", "更新失败");
                return ResponseEntity.ok(result);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "更新失败：" + e.getMessage());
            return ResponseEntity.ok(result);
        }
    }
    
    /**
     * 根据ID删除学生
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteStudent(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = studentService.deleteStudent(id);
            if (success) {
                result.put("success", true);
                result.put("message", "删除成功");
                return ResponseEntity.ok(result);
            } else {
                result.put("success", false);
                result.put("message", "删除失败");
                return ResponseEntity.ok(result);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "删除失败：" + e.getMessage());
            return ResponseEntity.ok(result);
        }
    }
}
