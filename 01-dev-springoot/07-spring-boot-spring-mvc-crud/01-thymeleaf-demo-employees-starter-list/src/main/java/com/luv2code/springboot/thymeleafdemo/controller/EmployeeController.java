package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	//Add mapping for /
	@GetMapping ("/")
	public String showIndex(){
		return "index";
		}

	// load employee data
	private EmployeeService employeeService;
	public EmployeeController(EmployeeService theEmployeeService){
		employeeService=theEmployeeService;
	}
	private List<Employee> theEmployees;

	// add mapping for "/list"
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		//get the employee from the database
		List<Employee> theEmployees = employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	@GetMapping("/form")
	public String formshow(){

		return "form";
	}
	@GetMapping("/showFormForAdd")
	public String showForm(Model themodel){

		//create model attribute to bind the data
		Employee theEmployee = new Employee();

		themodel.addAttribute("employee",theEmployee);
		return "employees/form";
	}

	@GetMapping("/showFormForUpdate")
	public String ShowFormForUpdate(@RequestParam("employeeId") int theId,Model theModel){

		//get the employee from the service
		Employee theEmployee = employeeService.findById(theId);

		//set employee in the model to prepopulate the form
		theModel.addAttribute("employee",theEmployee);

		//send to our from
		return "employees/form";

	}
	@GetMapping("/delete")
	public String Delete(@RequestParam("employeeId") int theId,Model theModel){

		//get the employee from the service
	employeeService.deleteById(theId);

		//deleting the emoployee



		//redirct to employee / list
		return "redirect:/employees/list";

	}


	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

		
		//save the employeee
		employeeService.save(theEmployee);
		//use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}


}









