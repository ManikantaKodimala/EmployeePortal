package com.ee.employeesportal.services;

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

    public List<Employee> getAllEmployees(){
        return jpaEmployeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee) {
        return jpaEmployeeRepository.save(employee);
    }

    public Employee updateEmployeeDetails(Employee newEmployee, long id) {
        Optional<Employee> employee=jpaEmployeeRepository.findById(id);
        if(employee.isEmpty()){
            return null;
        }
        newEmployee.setEmpId(id);
        return jpaEmployeeRepository.save(newEmployee);
    }
}
