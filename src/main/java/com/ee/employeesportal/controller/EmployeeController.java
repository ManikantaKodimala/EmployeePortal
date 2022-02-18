package com.ee.employeesportal.controller;

import com.ee.employeesportal.model.Employee;
import com.ee.employeesportal.module.EmployeeResult;
import com.ee.employeesportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(value = "/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long empId){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeById(empId));
    }

    @GetMapping("/search")
    public EmployeeResult getEmployeeByQuery(@RequestParam String query, @RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "5") int pageSize){
        return employeeService.searchEmployeeBy(query,page,pageSize);
    }
}
