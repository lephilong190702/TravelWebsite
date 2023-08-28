/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.controllers;

import com.lpl.service.EmployeeService;
import com.lpl.service.NewsService;
import com.lpl.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lephi
 */
@RestController
@RequestMapping("/admin")
public class ApiAdminController {

    @Autowired
    private TourService tourService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private EmployeeService employeeService;

    @DeleteMapping("/tours/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTour(@PathVariable(value = "id") int id) {
        this.tourService.deleteTour(id);
    }

    @DeleteMapping("/news/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNew(@PathVariable(value = "id") int id) {
        this.newsService.deleteNew(id);
    }

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable(value = "id") int id) {
        this.employeeService.deleteEmployee(id);
    }

}
