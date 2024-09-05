package com.CodeWithMe.springmvcsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/mylogin")
    public String showLogin(){
        return "login";
    }

    @GetMapping("/systems")
    public String systems(){
        return "systems";
    }

    @GetMapping("/leaders")
    public String leaders(){
        return "leaders";
    }

}
