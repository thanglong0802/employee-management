package com.longnt.employeemanagementsystem.controller;

import com.longnt.employeemanagementsystem.exception.ResourceNotFoundException;
import com.longnt.employeemanagementsystem.model.Employee;
import com.longnt.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000/")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // get all employee
    @GetMapping("/employees")
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    // create employee rest api
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // get employee by id
    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return employeeRepository.findEmployeeById(id);
    }

    // update employee by id
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable("id") Long id, @RequestBody Employee employee) {
//        Employee employeeUpdate = employeeRepository.findEmployeeById(id);
//
//        employeeUpdate.setFirstName(employee.getFirstName());
//        employeeUpdate.setLastName(employee.getLastName());
//        employeeUpdate.setEmailId(employee.getEmailId());
//
//        Employee update = employeeRepository.save(employeeUpdate);
//        return ResponseEntity.ok(update);

        return employeeRepository.findById(id)
                .map(employee1 -> {
                    employee1.setFirstName(employee.getFirstName());
                    employee1.setLastName(employee.getLastName());
                    employee1.setEmailId(employee.getEmailId());
                    return ResponseEntity.ok(employee1);
                }).orElseGet(() ->
                    ResponseEntity.ok(employeeRepository.save(employee))
                );
    }

    // delete employee by id
    @DeleteMapping("/employees/{id}")
    public void deleteEmployeeById(@PathVariable("id") Long id) {
        employeeRepository.deleteById(id);
    }
}
