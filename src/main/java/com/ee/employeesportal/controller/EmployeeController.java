package com.ee.employeesportal.controller;

import com.ee.employeesportal.model.Employee;
import com.ee.employeesportal.repositories.EmployeeRepository;
import com.ee.employeesportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    private Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Direction.ASC;
        } else if (direction.equals("desc")) {
            return Direction.DESC;
        }
        return Direction.ASC;
    }
    @GetMapping("")
    public List<Employee> getAllEmployees(){
       return employeeService.getAllEmployees();
    }

    @GetMapping("/sort")
    public List<Employee> getAllEmployeeSortBy(@RequestParam List<String> sort_by){
     List<Order> orders=new ArrayList<>();
        if (sort_by.get(0).contains(",")) {
            for (String sortOrder : sort_by) {
                String[] sort = sortOrder.split(",");
                orders.add(new Order(getSortDirection(sort[1]), sort[0]));
            }
        } else {
            orders.add(new Order(getSortDirection(sort_by.get(1)), sort_by.get(0)));
        }
        return employeeService.getAllEmployeesSortBy(Sort.by(orders));
    }
}
