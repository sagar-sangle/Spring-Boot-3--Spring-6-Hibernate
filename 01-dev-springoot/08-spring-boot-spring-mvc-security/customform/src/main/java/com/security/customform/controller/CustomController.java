package com.security.customform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomController {

    @GetMapping("/showMyLoginPage")
    public String showLogin()
    {
        return "login";
    }
//    @GetMapping("/welcome")
//    public String welcome()
//    {
//        return "welcome";
//    }


}
