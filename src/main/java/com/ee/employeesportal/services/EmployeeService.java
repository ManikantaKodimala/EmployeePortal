package com.ee.employeesportal.services;

import com.ee.employeesportal.model.Employee;
import com.ee.employeesportal.repositories.JpaEmployeeRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final JpaEmployeeRepository jpaEmployeeRepository;

    public List<Employee> getAllEmployees(){
        return jpaEmployeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee) {
        return jpaEmployeeRepository.save(employee);
    }

    public List<Employee> getAllEmployeesSortBy(Sort sortBy) {
        return jpaEmployeeRepository.findAll(sortBy);
    }
}
