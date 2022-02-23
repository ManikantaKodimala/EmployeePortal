package com.ee.employeesportal.controller;

import com.ee.employeesportal.dto.EmployeeDto;
import com.ee.employeesportal.model.Employee;
import com.ee.employeesportal.module.EmployeeResult;
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
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeDetails(@PathVariable("id") long id, @RequestBody Employee employee) {
        EmployeeDto employeeDetails = new EmployeeDto(employeeService.updateEmployeeDetails(employee, id));
        if (employeeDetails == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(employeeDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployeeDetails(@PathVariable(name = "id") long id) {
        EmployeeDto employee = new EmployeeDto(employeeService.deleteEmployee(id));
        return ResponseEntity.ok(employee);
    }

    @GetMapping(value = "/{empId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long empId) {
        return ResponseEntity.status(HttpStatus.OK).body(new EmployeeDto(employeeService.getEmployeeById(empId)));
    }

    @GetMapping("/search")
    public EmployeeResult getEmployeeByQuery(@RequestParam String query, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize) {
        return employeeService.searchEmployeeBy(query, page, pageSize);
    }

    @PostMapping("")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new EmployeeDto(employeeService.createEmployee(employee)));
    }

    private Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Direction.ASC;
        } else if (direction.equals("desc")) {
            return Direction.DESC;
        }
        return Direction.ASC;
    }

    @GetMapping("")
    public EmployeeResult getAllEmployees(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "page_size", defaultValue = "5") int pageSize, @RequestParam(name = "sort_by", required = false) Optional<List<String>> sortBy) {
        List<Order> orders;
        if (sortBy.isPresent()) {
            orders = getAllSortOrders(sortBy.get());
            return employeeService.getAllEmployees(page, pageSize, Sort.by(orders));
        }
        return employeeService.getAllEmployees(page, pageSize, null);
    }

    private List<Order> getAllSortOrders(List<String> sort_by) {
        List<Order> orders = new ArrayList<>();
        if (sort_by.get(0).contains(",")) {
            for (String sortOrder : sort_by) {
                String[] sort = sortOrder.split(",");
                orders.add(new Order(getSortDirection(sort[1]), sort[0]));
            }
        } else {
            orders.add(new Order(getSortDirection(sort_by.get(1)), sort_by.get(0)));
        }
        return orders;
    }

}
