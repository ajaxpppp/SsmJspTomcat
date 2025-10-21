package com.study.ssm.controller;

import com.study.ssm.entity.StudentRegister;
import com.study.ssm.service.StudentRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
import java.util.List;

/**
 * 学生注册控制器
 * 使用@RequestMapping的method属性区分GET和POST请求
 * 处理器方法名均为zhuce
 */
@Controller
public class ZhuceController {
    
    @Autowired
    private StudentRegisterService studentRegisterService;
    
    /**
     * 显示注册页面 - GET请求
     * 处理器名为zhuce，通过GET方法访问
     * 
     * @return 返回register视图名称（对应/WEB-INF/jsp/register.jsp）
     */
    @RequestMapping(value = "/zhuce", method = RequestMethod.GET)
    public String zhuce() {
        // 返回注册页面视图名
        return "register";
    }
    
    /**
     * 处理注册表单提交 - POST请求
     * 处理器名为zhuce，通过POST方法访问
     * 接收表单数据并传递到显示页面
     * 
     * @param studentRegister 学生注册信息（自动绑定表单数据）
     * @param model Spring MVC的Model对象，用于传递数据到视图
     * @return 返回display视图名称（对应/WEB-INF/jsp/display.jsp）
     */
    @RequestMapping(value = "/zhuce", method = RequestMethod.POST)
    public String zhuce(StudentRegister studentRegister, Model model, RedirectAttributes redirectAttributes) {
        // 先持久化到数据库
        boolean saved = false;
        try {
            saved = studentRegisterService.register(studentRegister);
        } catch (Exception e) {
            // 保存失败也不影响展示页面，但会提示
            model.addAttribute("saveError", e.getMessage());
        }
        if (saved) {
            // 使用重定向，跳转到用户列表页，显示最新数据
            redirectAttributes.addFlashAttribute("message", "学生注册信息已成功提交并保存！");
            return "redirect:/listuser";
        } else {
            // 失败：回到注册页面并回填表单 + 错误提示
            model.addAttribute("student", studentRegister);
            model.addAttribute("messageError", "学生注册信息已提交（保存失败，请联系管理员）");
            return "register";
        }
    }

    /**
     * 已注册用户列表页面 - GET请求
     * 访问 /listuser 展示所有已注册的学生信息
     */
    @RequestMapping(value = "/listuser", method = RequestMethod.GET)
    public String listuser(Model model) {
        List<StudentRegister> users = studentRegisterService.listAll();
        model.addAttribute("users", users);
        return "listuser";
    }
}
