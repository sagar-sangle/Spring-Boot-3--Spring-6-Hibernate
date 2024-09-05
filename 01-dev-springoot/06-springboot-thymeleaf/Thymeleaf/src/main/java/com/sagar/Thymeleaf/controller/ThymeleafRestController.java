package com.sagar.Thymeleaf.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class ThymeleafRestController {

    @GetMapping("/show")
    public String show(Model model){
        model.addAttribute("theDate",new Date());
        return "index";
    }
}
