package com.ee.employeesportal.controller;

import com.ee.employeesportal.model.Employee;
import com.ee.employeesportal.repositories.EmployeeRepository;
import com.ee.employeesportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
//    private final EmployeeRepository employeeRepository;

    private final EmployeeService employeeService;
    @GetMapping("")
    public List<Employee> getAllEmployees(){
//    return employeeRepository.getAllEmployees();
       return employeeService.getAllEmployees();
    }

    @GetMapping("/sort/firstName")
    public List<Employee> getAllEmployeesSortByName(){
        return employeeService.getAllEmployeesSortByName();
    }
    @GetMapping("/sort/dateOfJoin")
    public List<Employee> getAllEmployeesSortByDateOfJoin(){
        return employeeService.getAllEmployeesSortByDateOfJoin();
    }
    @PostMapping("")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }
}
