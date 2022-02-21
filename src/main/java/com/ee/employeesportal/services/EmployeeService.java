package com.ee.employeesportal.services;

import com.ee.employeesportal.model.Employee;
import com.ee.employeesportal.repositories.JpaEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final JpaEmployeeRepository jpaEmployeeRepository;

    public Employee createEmployee(Employee employee) {
        return jpaEmployeeRepository.save(employee);
    }

}
