package com.ee.employeesportal.services;

import com.ee.employeesportal.advices.EmployeeException;
import com.ee.employeesportal.dto.EmployeeDto;
import com.ee.employeesportal.model.Employee;
import com.ee.employeesportal.module.EmployeeResult;
import com.ee.employeesportal.repositories.JpaEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final JpaEmployeeRepository jpaEmployeeRepository;

    public Employee updateEmployeeDetails(Employee newEmployee, long id) {
        Optional<Employee> employee = jpaEmployeeRepository.findById(id);
        if (employee.isEmpty()) {
            return null;
        }
        newEmployee.setId(id);
        return jpaEmployeeRepository.save(newEmployee);
    }

    public Employee deleteEmployee(long id) {
        Optional<Employee> employee = jpaEmployeeRepository.findById(id);
        if (employee.isPresent()) {
            jpaEmployeeRepository.deleteById(id);
            return employee.get();
        }
        throw new EmployeeException("Employee with "+id+" does not exist");
    }

    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long empId) {
        Optional<Employee> byId = jpaEmployeeRepository.findById(empId);
        if (byId.isEmpty()) {
            throw new EmployeeException("employ id is not found");
        }
        return byId.get();
    }

    @Transactional(readOnly = true)
    public EmployeeResult searchEmployeeBy(String query, int page, int pageSize) {
        Sort sort = Sort.by(Direction.ASC, "firstName");
        Pageable pageableEmployee = PageRequest.of(page - 1, pageSize, sort);
        Page<Employee> employees = jpaEmployeeRepository.findByFirstNameContainingOrLastNameContaining(query, query, pageableEmployee);
        return new EmployeeResult(employees.map(EmployeeDto::new));

    }
    public Employee createEmployee(Employee employee) {
        Employee byEverestMailId = jpaEmployeeRepository.findByEverestEmailId(employee.getEverestEmailId());
        if(byEverestMailId != null){
            throw new EmployeeException("There Exist a employee with "+byEverestMailId.getEverestEmailId());
        }
        return jpaEmployeeRepository.save(employee);
    }
    @Transactional(readOnly = true)
    public EmployeeResult getAllEmployees(int page, int pageSize, Sort sortBy) {
        if(sortBy == null){
            sortBy= Sort.by(Sort.Direction.ASC,"firstName");
        }
        Pageable pageableEmployee = PageRequest.of(page-1, pageSize, sortBy);
        Page<Employee> employees = jpaEmployeeRepository.findAll(pageableEmployee);
        return new EmployeeResult(employees.map(EmployeeDto::new));
    }
}
