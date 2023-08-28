/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lpl.service;

import com.lpl.pojo.Employee;
import java.util.List;

/**
 *
 * @author lephi
 */
public interface EmployeeService {
    List<Employee> getEmployee();
    boolean addOrUpdateEmployee(Employee e);
    Employee getEmployeeById(int id);
    boolean deleteEmployee(int id);
}
