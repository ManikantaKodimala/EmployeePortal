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

    public List<Employee> getAllEmployeesSortByName() {
        return jpaEmployeeRepository.findAllByOrderByFirstNameAsc();
    }

    public List<Employee> getAllEmployeesSortByDateOfJoin() {
        return jpaEmployeeRepository.findAllByOrderByDateOfJoin();
    }

    public Employee getEmployeeById(Long empId) {
        Optional<Employee> byId = jpaEmployeeRepository.findById(empId);
        if(byId.isEmpty()){
            throw new RuntimeException("employ id is not found");
        }
        return byId.get();
    }

    public List<Employee> getEmployeeByFirstName(String firstName) {
        return jpaEmployeeRepository.findAllByFirstName(firstName);
    }
}
