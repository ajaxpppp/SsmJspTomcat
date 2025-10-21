package com.study.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "needLogin", required = false) String needLogin,
                            @RequestParam(value = "redirect", required = false) String redirect,
                            Model model){
        model.addAttribute("needLogin", needLogin);
        model.addAttribute("redirect", redirect);
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam(value = "redirect", required = false) String redirect,
                          HttpServletRequest request,
                          Model model){
        if ("admin".equals(username) && "123456".equals(password)){
            HttpSession session = request.getSession(true);
            session.setAttribute("loginUser", username);
            String target = (redirect == null || redirect.isEmpty()) ? "/books" : redirect;
            return "redirect:" + target;
        }
        model.addAttribute("error", "用户名或密码错误");
        model.addAttribute("redirect", redirect);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
        }
        // 回到登录页
        String loginUrl = "/login?needLogin=1";
        return "redirect:" + loginUrl;
    }
}
