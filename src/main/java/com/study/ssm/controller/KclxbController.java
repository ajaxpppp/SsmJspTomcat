package com.study.ssm.controller;

import com.study.ssm.entity.Kclxb;
import com.study.ssm.service.KclxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 课程类型管理控制器 - 对应kclxb表
 * 已禁用，改为图书分类管理
 */
//@Controller
@RequestMapping("/test_kclx")
public class KclxbController {
    
    @Autowired
    private KclxbService kclxbService;
    
    /**
     * 显示所有课程类型信息（主页）
     * 访问路径：http://localhost:8080/test/index
     */
    @GetMapping("/index")
    public String index(Model model) {
        List<Kclxb> kclxbList = kclxbService.getAllKclxb();
        model.addAttribute("kclxbList", kclxbList);
        return "kclx/index";
    }
    
    /**
     * 跳转到添加课程类型页面
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("kclxb", new Kclxb());
        return "kclx/add";
    }
    
    /**
     * 添加课程类型信息
     */
    @PostMapping("/add")
    public String addKclxb(@ModelAttribute Kclxb kclxb, RedirectAttributes redirectAttributes) {
        try {
            boolean success = kclxbService.addKclxb(kclxb);
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
     * 跳转到修改课程类型页面
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Kclxb kclxb = kclxbService.getKclxbById(id);
        if (kclxb == null) {
            model.addAttribute("message", "课程类型不存在！");
            return "redirect:/test/index";
        }
        model.addAttribute("kclxb", kclxb);
        return "kclx/edit";
    }
    
    /**
     * 更新课程类型信息
     */
    @PostMapping("/update")
    public String updateKclxb(@ModelAttribute Kclxb kclxb, RedirectAttributes redirectAttributes) {
        try {
            boolean success = kclxbService.updateKclxb(kclxb);
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
     * 删除课程类型信息
     */
    @GetMapping("/delete/{id}")
    public String deleteKclxb(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            boolean success = kclxbService.deleteKclxb(id);
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
