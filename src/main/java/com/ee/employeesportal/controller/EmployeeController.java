package com.ee.employeesportal.controller;

import com.ee.employeesportal.model.Employee;
import com.ee.employeesportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
//    private final EmployeeRepository employeeRepository;

    private final EmployeeService employeeService;
    @GetMapping("/")
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
    @GetMapping(value = "/{empId}")
    public Employee getEmployeeById(@PathVariable Long empId){
        return employeeService.getEmployeeById(empId);
    }
    @GetMapping(value = "/firstName/{firstName}")
    public List<Employee> getEmployeeByName(@PathVariable(value="firstName" ) String  firstName){
        return employeeService.getEmployeeByFirstName(firstName);
    }
}
