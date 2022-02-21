package com.ee.employeesportal.controller;

import com.ee.employeesportal.model.Employee;
import com.ee.employeesportal.module.EmployeeResult;
import com.ee.employeesportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeeDetails(@PathVariable("id") long id, @RequestBody Employee employee) {
        Employee employeeDetails = employeeService.updateEmployeeDetails(employee, id);
        if (employeeDetails == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(employeeDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployeeDetails(@PathVariable(name = "id") long id) {
        Employee employee = employeeService.deleteEmployee(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping(value = "/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long empId) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeById(empId));
    }

    @GetMapping("/search")
    public EmployeeResult getEmployeeByQuery(@RequestParam String query, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize) {
        return employeeService.searchEmployeeBy(query, page, pageSize);
    }

    @PostMapping("")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }
}
