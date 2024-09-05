package com.mvc.validation.controller;

import com.mvc.validation.entity.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {


    //theres little catch in our form when we send spaces it is still gettting processed bacause
    // white space is also getting considered as some text in our form

    //to resolve this issue we will add init binder method
    //it automaticallly pre processes the form whenever a request is made to our controller
    //it removes leading and trailing white spaces

    //it processes every String form data
    //if string has only white space it will trim it to null
    //method annoateed with @initBinder gets executed first

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class , trimmerEditor);
    }


    @GetMapping("/hello")
    public String hello(){
        return "index";
    }


    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("customer",new Customer());
        return "form";
    }
    @PostMapping("/formhandle")
    public String showDetail(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult){
        //the binding result actully holds the result of validation if everything suucessfull

        //but user still enters invalid data then you can perfomr any other logic with the help
        //of below code
        if(bindingResult.hasErrors()){
            return "form";
        }
        else {

            System.out.println("firstName : |"+customer.getFirstName()+"|");
            //here spring mvc tells to perfrom the validation rules by help of @valid annotation
            return "showDetails";
        }
    }

}
