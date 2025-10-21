package com.study.ssm.controller;

import com.study.ssm.entity.Clazz;
import com.study.ssm.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 班级控制器
 */
@RestController
@RequestMapping("/clazz")
public class ClazzController {
    
    @Autowired
    private ClazzService clazzService;
    
    /**
     * 根据ID查询班级信息（包含学生列表）
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getClazzById(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            Clazz clazz = clazzService.getClazzWithStudentsById(id);
            if (clazz != null) {
                result.put("success", true);
                result.put("message", "查询成功");
                result.put("data", clazz);
                return ResponseEntity.ok(result);
            } else {
                result.put("success", false);
                result.put("message", "未找到该班级");
                return ResponseEntity.ok(result);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败：" + e.getMessage());
            return ResponseEntity.ok(result);
        }
    }
    
    /**
     * 查询所有班级信息（包含学生列表）
     */
    @GetMapping("/classes")
    public ResponseEntity<Map<String, Object>> getAllClasses() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Clazz> classes = clazzService.getAllClazzWithStudents();
            result.put("success", true);
            result.put("message", "查询成功");
            result.put("data", classes);
            result.put("count", classes.size());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败：" + e.getMessage());
            return ResponseEntity.ok(result);
        }
    }
    
    /**
     * 根据班级编码查询班级信息（包含学生列表）
     */
    @GetMapping("/code/{code}")
    public ResponseEntity<Map<String, Object>> getClazzByCode(@PathVariable String code) {
        Map<String, Object> result = new HashMap<>();
        try {
            Clazz clazz = clazzService.getClazzWithStudentsByCode(code);
            if (clazz != null) {
                result.put("success", true);
                result.put("message", "查询成功");
                result.put("data", clazz);
                return ResponseEntity.ok(result);
            } else {
                result.put("success", false);
                result.put("message", "未找到该班级");
                return ResponseEntity.ok(result);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败：" + e.getMessage());
            return ResponseEntity.ok(result);
        }
    }
    
    /**
     * 根据班级名称查询班级信息（包含学生列表）
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<Map<String, Object>> getClazzByName(@PathVariable String name) {
        Map<String, Object> result = new HashMap<>();
        try {
            Clazz clazz = clazzService.getClazzWithStudentsByName(name);
            if (clazz != null) {
                result.put("success", true);
                result.put("message", "查询成功");
                result.put("data", clazz);
                return ResponseEntity.ok(result);
            } else {
                result.put("success", false);
                result.put("message", "未找到该班级");
                return ResponseEntity.ok(result);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败：" + e.getMessage());
            return ResponseEntity.ok(result);
        }
    }
    
    /**
     * 添加班级信息
     */
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addClazz(@RequestBody Clazz clazz) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = clazzService.addClazz(clazz);
            if (success) {
                result.put("success", true);
                result.put("message", "添加成功");
                result.put("data", clazz);
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
     * 更新班级信息
     */
    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateClazz(@RequestBody Clazz clazz) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = clazzService.updateClazz(clazz);
            if (success) {
                result.put("success", true);
                result.put("message", "更新成功");
                result.put("data", clazz);
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
     * 根据ID删除班级
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteClazz(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = clazzService.deleteClazz(id);
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
