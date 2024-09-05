package com.security.customform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String show()
    {
        return "welcome";
    }

    @GetMapping("/welcome")
    public String showWelcome()
    {
        return "welcome";
    }

    @GetMapping("/leaders")
    public String showLeaders()
    {
        return "leaders";
    }

    @GetMapping("/admin")
    public String showAdmin()
    {
        return "admin";
    }

    @GetMapping("/access-denied")
    public String showdenied()
    {
        return "access-denied";
    }
}
