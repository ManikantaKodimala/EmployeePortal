package com.ee.employeesportal.controller;

import com.ee.employeesportal.model.Employee;
import com.ee.employeesportal.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    @GetMapping("")
    public List<Employee> getAllEmployees(){
    return employeeRepository.getAllEmployees();
    }

    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "empId") Long empId){
        Employee employeeById = employeeRepository.getEmployeeById(empId);
        if(employeeById != null)
            return ResponseEntity.ok(employeeById);

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        employee = employeeRepository.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping("/{empId}")
    public Employee updateEmployeeById(@PathVariable(name = "empId") Long empId,@RequestBody Employee employee) {
        employee.setEmpId(empId);
        return employeeRepository.updateEmployeeById(employee);
    }

    @DeleteMapping("/{empId}")
    public Employee deleteEmployeeById(@PathVariable(name = "empId") Long empId) {
        return employeeRepository.deleteEmployeeById(empId);
    }
}
