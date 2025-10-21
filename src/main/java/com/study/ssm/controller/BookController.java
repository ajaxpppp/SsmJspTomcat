package com.study.ssm.controller;

import com.study.ssm.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @GetMapping
    public String list(Model model){
        List<Book> books = Arrays.asList(
                new Book("/static/img/java.jpg", "疯狂Java讲义", "李刚", 74.2),
                new Book("/static/img/ee.jpg", "轻量级Java EE企业应用实战", "李刚", 59.2),
                new Book("/static/img/android.jpg", "疯狂Android讲义", "李刚", 60.6),
                new Book("/static/img/ajax.jpg", "疯狂Ajax讲义", "李刚", 66.6)
        );
        model.addAttribute("books", books);
        return "books";
    }
}
