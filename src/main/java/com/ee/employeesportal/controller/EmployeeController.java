package com.ee.employeesportal.controller;

import com.ee.employeesportal.model.Employee;
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
    private final EmployeeService employeeService;

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeeDetails(@PathVariable("id") long id,@RequestBody Employee employee){
        Employee employeeDetails = employeeService.updateEmployeeDetails(employee, id);
        if( employeeDetails== null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(employeeDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployeeDetails(@PathVariable(name = "id") long id){
        Employee employee = employeeService.deleteEmployee(id);
        if (employee == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(employee);
    }
}
