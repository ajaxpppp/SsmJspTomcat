package com.study.ssm.entity;

import lombok.Data;

@Data
public class Book {
    private String cover;
    private String title;
    private String author;
    private double price;

    public Book() {}

    public Book(String cover, String title, String author, double price) {
        this.cover = cover;
        this.title = title;
        this.author = author;
        this.price = price;
    }
}
