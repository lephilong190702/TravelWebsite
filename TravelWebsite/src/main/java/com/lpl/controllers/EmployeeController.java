/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.controllers;

import com.lpl.pojo.Employee;
import com.lpl.service.EmployeeService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lephi
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    
    @RequestMapping("/employees")
    public String listEmployee(Model model) {
        model.addAttribute("employees", this.employeeService.getEmployee());
        return "employees";
    }
    
    @GetMapping("/admin/employees")
    public String list(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee";
    }
    
    @GetMapping("/admin/employees/{id}")
    public String update(Model model, @PathVariable(value = "id")int id) {
        model.addAttribute("employee", this.employeeService.getEmployeeById(id));
        return "employee";
    }
    
    @PostMapping("/admin/employees")
    public String add(@ModelAttribute(value = "employee") @Valid Employee e,
            BindingResult rs) {
        if(!rs.hasErrors())
        {
            if (this.employeeService.addOrUpdateEmployee(e) == true)
                return "redirect:/employees";
        }
        
        return "employee";
    }
    
}
