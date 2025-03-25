package com.project.vpweb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminControllers {

    @GetMapping("")
    public String index() {
        return "Hello World";
    }
}
