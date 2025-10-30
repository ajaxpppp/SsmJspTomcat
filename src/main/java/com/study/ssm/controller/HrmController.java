package com.study.ssm.controller;

import com.study.ssm.entity.User;
import com.study.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * HRM人力资源管理系统控制器
 * 实现用户的增删改查功能
 */
@Controller
@RequestMapping("/hrm")
public class HrmController {

    @Autowired
    private UserService userService;

    /**
     * 显示用户列表页面
     * 访问路径：/hrm/userList
     */
    @RequestMapping("/userList")
    public String userList(Model model) {
        try {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            model.addAttribute("userCount", users.size());
            System.out.println(">>> 查询到 " + users.size() + " 个用户");
            return "hrm/userList";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "查询用户列表失败：" + e.getMessage());
            return "hrm/userList";
        }
    }

    /**
     * 跳转到添加用户页面
     * 访问路径：/hrm/toAddUser
     */
    @RequestMapping("/toAddUser")
    public String toAddUser() {
        return "hrm/addUser";
    }

    /**
     * 添加用户
     * 访问路径：/hrm/addUser
     */
    @PostMapping("/addUser")
    public String addUser(User user, RedirectAttributes redirectAttributes) {
        try {
            System.out.println(">>> 准备添加用户：" + user);
            boolean success = userService.addUser(user);
            if (success) {
                redirectAttributes.addFlashAttribute("message", "添加用户成功！");
                System.out.println(">>> 添加用户成功");
            } else {
                redirectAttributes.addFlashAttribute("error", "添加用户失败，用户名可能已存在！");
                System.out.println(">>> 添加用户失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "添加用户异常：" + e.getMessage());
        }
        return "redirect:/hrm/userList";
    }

    /**
     * 跳转到编辑用户页面
     * 访问路径：/hrm/toEditUser?id=1
     */
    @RequestMapping("/toEditUser")
    public String toEditUser(@RequestParam("id") Integer id, Model model) {
        try {
            User user = userService.getUserById(id);
            if (user == null) {
                model.addAttribute("error", "用户不存在！");
                return "redirect:/hrm/userList";
            }
            model.addAttribute("user", user);
            System.out.println(">>> 准备编辑用户：" + user);
            return "hrm/editUser";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "查询用户失败：" + e.getMessage());
            return "redirect:/hrm/userList";
        }
    }

    /**
     * 更新用户信息
     * 访问路径：/hrm/updateUser
     */
    @PostMapping("/updateUser")
    public String updateUser(User user, RedirectAttributes redirectAttributes) {
        try {
            System.out.println(">>> 准备更新用户：" + user);
            boolean success = userService.updateUser(user);
            if (success) {
                redirectAttributes.addFlashAttribute("message", "更新用户成功！");
                System.out.println(">>> 更新用户成功");
            } else {
                redirectAttributes.addFlashAttribute("error", "更新用户失败！");
                System.out.println(">>> 更新用户失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "更新用户异常：" + e.getMessage());
        }
        return "redirect:/hrm/userList";
    }

    /**
     * 删除用户
     * 访问路径：/hrm/deleteUser?id=1
     */
    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            System.out.println(">>> 准备删除用户，ID：" + id);
            boolean success = userService.deleteUser(id);
            if (success) {
                redirectAttributes.addFlashAttribute("message", "删除用户成功！");
                System.out.println(">>> 删除用户成功");
            } else {
                redirectAttributes.addFlashAttribute("error", "删除用户失败！");
                System.out.println(">>> 删除用户失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "删除用户异常：" + e.getMessage());
        }
        return "redirect:/hrm/userList";
    }

    /**
     * 根据ID查看用户详情
     * 访问路径：/hrm/viewUser?id=1
     */
    @RequestMapping("/viewUser")
    public String viewUser(@RequestParam("id") Integer id, Model model) {
        try {
            User user = userService.getUserById(id);
            if (user == null) {
                model.addAttribute("error", "用户不存在！");
                return "redirect:/hrm/userList";
            }
            model.addAttribute("user", user);
            System.out.println(">>> 查看用户详情：" + user);
            return "hrm/viewUser";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "查询用户失败：" + e.getMessage());
            return "redirect:/hrm/userList";
        }
    }
}
