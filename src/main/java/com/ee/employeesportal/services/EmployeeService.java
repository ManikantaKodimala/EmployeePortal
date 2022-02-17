package com.ee.employeesportal.services;

import com.ee.employeesportal.advices.EmployeeException;
import com.ee.employeesportal.model.Employee;
import com.ee.employeesportal.repositories.JpaEmployeeRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Data
public class EmployeeService {
    private final JpaEmployeeRepository jpaEmployeeRepository;

    public Employee getEmployeeById(Long empId) {
        Optional<Employee> byId = jpaEmployeeRepository.findById(empId);
        if(byId.isEmpty()){
            throw new EmployeeException("employ id is not found");
        }
        return byId.get();
    }

}
