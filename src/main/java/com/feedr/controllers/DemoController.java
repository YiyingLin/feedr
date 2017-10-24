package com.feedr.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping(path = "/hello")
    public String helloWorld() {
        return "Hello World";
    }
}
