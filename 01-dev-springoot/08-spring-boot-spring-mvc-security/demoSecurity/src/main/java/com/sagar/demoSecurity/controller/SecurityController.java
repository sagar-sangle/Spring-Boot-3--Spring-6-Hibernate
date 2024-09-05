package com.sagar.demoSecurity.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SecurityController {
    @GetMapping("/home")
    public String home(Model model, Authentication authentication){
        String username = authentication.getName();
        model.addAttribute("username",username);
        return "home";
    }






}
