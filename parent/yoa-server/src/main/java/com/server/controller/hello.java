package com.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {
    @GetMapping("/employee/basic/hello")
    public String he1(){return "hello1";}
    @GetMapping("/employee/advanced/he")
    public String he2(){return "hello2";}
}
