package com.origin.tool.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lixiewen
 * @create 2019-11-26 11:58
 */
@RestController
public class Hello {
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello");
        return "HelloTomcat";
    }
}
