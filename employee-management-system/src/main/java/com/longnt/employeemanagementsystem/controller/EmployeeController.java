package com.longnt.employeemanagementsystem.controller;

import com.longnt.employeemanagementsystem.model.Employee;
import com.longnt.employeemanagementsystem.service.EmployeeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000/")
public class EmployeeController {
    private final EmployeeServiceImpl service;

    public EmployeeController(EmployeeServiceImpl service) {
        this.service = service;
    }

    // get all employee
    @GetMapping("/employees")
    public List<Employee> getAll() {
        return service.getAllEmployee();
    }

    // create employee rest api
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return service.createEmployee(employee);
    }

    // get employee by id
    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return service.findEmployeeById(id);
    }

    // update employee by id
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return service.updateEmployee(id, employee);
    }

    // delete employee by id
    @DeleteMapping("/employees/{id}")
    public void deleteEmployeeById(@PathVariable("id") Long id) {
        service.deleteEmployeeById(id);
    }
}
