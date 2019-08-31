package com.origin.tool.controller;

import com.origin.tool.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenertatorController {

    @Autowired
    private GeneratorService generatorService;

    @RequestMapping("/genertedByMyBatis")
    public String genertedByMyBatis() {
        return "success";

    }

    @RequestMapping("/genertedByOrigin")
    public void genertedByOrigin() {

    }
}
