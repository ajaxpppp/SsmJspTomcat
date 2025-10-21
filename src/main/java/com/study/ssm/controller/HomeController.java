package com.study.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 根路径首页控制器
 * 解决在 DispatcherServlet 使用 "/" 映射时，容器欢迎页不生效导致的 404 问题。
 * 将根路径统一重定向到 /page/index（由 PageController 渲染 /WEB-INF/jsp/index.jsp）。
 */
@Controller
public class HomeController {

    @GetMapping({"/", ""})
    public String home() {
        return "redirect:/page/index";
    }
}
