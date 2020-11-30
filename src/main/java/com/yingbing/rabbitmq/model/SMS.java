package com.yingbing.rabbitmq.model;

import lombok.Data;

@Data
public class SMS {
    public SMS(String name, String phone, String content) {
        this.name = name;
        this.phone = phone;
        this.content = content;
    }
    private String name;
    private String phone;
    private String content;
}
