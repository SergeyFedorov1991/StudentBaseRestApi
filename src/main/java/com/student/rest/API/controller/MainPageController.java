package com.student.rest.API.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPageController {

    @GetMapping
    public String HelloPage(){
        return "Hello! For accessing to database follow this link: /students";
    }

}
