/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.service.impl;

import com.lpl.pojo.Employee;
import com.lpl.repository.EmployeeRepository;
import com.lpl.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lephi
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Override
    public List<Employee> getEmployee() {
        return this.employeeRepository.getEmployee();
    }

    @Override
    public boolean addOrUpdateEmployee(Employee e) {
        return this.employeeRepository.addOrUpdateEmployee(e);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return this.employeeRepository.getEmployeeById(id);
    }

    @Override
    public boolean deleteEmployee(int id) {
        return this.employeeRepository.deleteEmployee(id);
    }
    
}
