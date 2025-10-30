package com.study.ssm.controller;

import com.study.ssm.entity.Catalog;
import com.study.ssm.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 图书分类管理控制器 - 对应catalog表
 */
@Controller
@RequestMapping("/test")
public class CatalogController {
    
    @Autowired
    private CatalogService catalogService;
    
    /**
     * 显示所有图书分类信息（主页）
     * 访问路径：http://localhost:8080/test/index
     */
    @GetMapping("/index")
    public String index(Model model) {
        List<Catalog> catalogList = catalogService.getAllCatalog();
        model.addAttribute("catalogList", catalogList);
        return "catalog/index";
    }
    
    /**
     * 跳转到添加图书分类页面
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("catalog", new Catalog());
        return "catalog/add";
    }
    
    /**
     * 添加图书分类信息
     */
    @PostMapping("/add")
    public String addCatalog(@ModelAttribute Catalog catalog, RedirectAttributes redirectAttributes) {
        try {
            boolean success = catalogService.addCatalog(catalog);
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
     * 跳转到修改图书分类页面
     */
    @GetMapping("/edit/{catalogid}")
    public String showEditForm(@PathVariable Integer catalogid, Model model) {
        Catalog catalog = catalogService.getCatalogById(catalogid);
        if (catalog == null) {
            model.addAttribute("message", "图书分类不存在！");
            return "redirect:/test/index";
        }
        model.addAttribute("catalog", catalog);
        return "catalog/edit";
    }
    
    /**
     * 更新图书分类信息
     */
    @PostMapping("/update")
    public String updateCatalog(@ModelAttribute Catalog catalog, RedirectAttributes redirectAttributes) {
        try {
            boolean success = catalogService.updateCatalog(catalog);
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
     * 删除图书分类信息
     */
    @GetMapping("/delete/{catalogid}")
    public String deleteCatalog(@PathVariable Integer catalogid, RedirectAttributes redirectAttributes) {
        try {
            boolean success = catalogService.deleteCatalog(catalogid);
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
