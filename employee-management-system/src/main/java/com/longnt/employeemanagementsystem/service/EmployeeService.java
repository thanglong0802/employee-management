package com.longnt.employeemanagementsystem.service;

import com.longnt.employeemanagementsystem.model.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();
    Employee createEmployee(Employee employee);
    Employee findEmployeeById(Long id);
    ResponseEntity<Employee> updateEmployee(Long id, Employee employee);
    void deleteEmployeeById(Long id);
}
