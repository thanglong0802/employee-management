package com.longnt.employeemanagementsystem.service;

import com.longnt.employeemanagementsystem.exception.ResourceNotFoundException;
import com.longnt.employeemanagementsystem.model.Employee;
import com.longnt.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return repository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return repository.findEmployeeById(id).orElseThrow(() -> new ResourceNotFoundException("Not Fount " + id));
    }

    @Override
    public ResponseEntity<Employee> updateEmployee(Long id, Employee employee) {
        Employee employeeUpdate = repository.findEmployeeById(id).orElseThrow(() -> new ResourceNotFoundException("Not Fount" + id));
        employeeUpdate.setFirstName(employee.getFirstName());
        employeeUpdate.setLastName(employee.getLastName());
        employeeUpdate.setEmailId(employeeUpdate.getEmailId());
        return ResponseEntity.ok(repository.save(employeeUpdate));
//        return repository.findEmployeeById(id)
//                .map(employee1 -> {
//                    employee1.setFirstName(employee.getFirstName());
//                    employee1.setLastName(employee.getLastName());
//                    employee1.setEmailId(employee.getEmailId());
//                    return ResponseEntity.ok(employee1);
//                }).orElseGet(() ->
//                    ResponseEntity.ok(repository.save(employee))
//                );
    }

    @Override
    public void deleteEmployeeById(Long id) {
        repository.deleteById(id);
    }
}
