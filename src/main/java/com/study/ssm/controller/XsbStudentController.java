package com.study.ssm.controller;

import com.study.ssm.entity.XsbStudent;
import com.study.ssm.service.XsbStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 学生管理控制器 - 对应xsb表
 * 已禁用，改为课程类型管理
 */
//@Controller
@RequestMapping("/test_student")
public class XsbStudentController {
    
    @Autowired
    private XsbStudentService xsbStudentService;
    
    /**
     * 显示所有学生信息（主页）
     * 访问路径：http://localhost:8080/test/index
     */
    @GetMapping("/index")
    public String index(Model model) {
        List<XsbStudent> students = xsbStudentService.getAllStudents();
        model.addAttribute("students", students);
        return "test/index";
    }
    
    /**
     * 跳转到添加学生页面
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new XsbStudent());
        return "test/add";
    }
    
    /**
     * 添加学生信息
     */
    @PostMapping("/add")
    public String addStudent(@ModelAttribute XsbStudent student, RedirectAttributes redirectAttributes) {
        try {
            boolean success = xsbStudentService.addStudent(student);
            if (success) {
                redirectAttributes.addFlashAttribute("message", "添加成功！");
                redirectAttributes.addFlashAttribute("messageType", "success");
            } else {
                redirectAttributes.addFlashAttribute("message", "添加失败！");
                redirectAttributes.addFlashAttribute("messageType", "error");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "添加失败：" + e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/test/index";
    }
    
    /**
     * 跳转到修改学生页面
     */
    @GetMapping("/edit/{sno}")
    public String showEditForm(@PathVariable String sno, Model model) {
        XsbStudent student = xsbStudentService.getStudentBySno(sno);
        if (student == null) {
            model.addAttribute("message", "学生不存在！");
            return "redirect:/test/index";
        }
        model.addAttribute("student", student);
        return "test/edit";
    }
    
    /**
     * 更新学生信息
     */
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute XsbStudent student, RedirectAttributes redirectAttributes) {
        try {
            boolean success = xsbStudentService.updateStudent(student);
            if (success) {
                redirectAttributes.addFlashAttribute("message", "修改成功！");
                redirectAttributes.addFlashAttribute("messageType", "success");
            } else {
                redirectAttributes.addFlashAttribute("message", "修改失败！");
                redirectAttributes.addFlashAttribute("messageType", "error");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "修改失败：" + e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/test/index";
    }
    
    /**
     * 删除学生信息
     */
    @GetMapping("/delete/{sno}")
    public String deleteStudent(@PathVariable String sno, RedirectAttributes redirectAttributes) {
        try {
            boolean success = xsbStudentService.deleteStudent(sno);
            if (success) {
                redirectAttributes.addFlashAttribute("message", "删除成功！");
                redirectAttributes.addFlashAttribute("messageType", "success");
            } else {
                redirectAttributes.addFlashAttribute("message", "删除失败！");
                redirectAttributes.addFlashAttribute("messageType", "error");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "删除失败：" + e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/test/index";
    }
}
