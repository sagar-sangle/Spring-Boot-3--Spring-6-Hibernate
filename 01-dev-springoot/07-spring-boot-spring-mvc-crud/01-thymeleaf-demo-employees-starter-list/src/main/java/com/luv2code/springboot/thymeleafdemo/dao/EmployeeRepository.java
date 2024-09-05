package com.luv2code.springboot.thymeleafdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	// that's it ... no need to write any code LOL!

    //add a method to sort by first name
    public List<Employee> findAllByOrderByLastNameAsc();
        //spring data jpa will parse the method name
        //look for speccific format and pattern create appropriate way..behind the scene

}
