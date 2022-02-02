package com.ee.employeesportal.controller;

import com.ee.employeesportal.model.Employee;
import com.ee.employeesportal.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
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
    public Employee getEmployeeById(@PathVariable(name = "empId") Long empId){
        return employeeRepository.getEmployeeById(empId);
    }

    @PostMapping("")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.createEmployee(employee);
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
