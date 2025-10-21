package com.study.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 通用页面跳转控制器
 * 访问路径示例：/page/index -> 返回 /WEB-INF/jsp/index.jsp
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @GetMapping("/{view}")
    public String forward(@PathVariable("view") String view) {
        // 简单返回视图名，由视图解析器解析到 /WEB-INF/jsp/{view}.jsp
        return view;
    }
}
