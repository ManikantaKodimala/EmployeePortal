package com.ee.employeesportal.services;

import com.ee.employeesportal.advices.EmployeeException;
import com.ee.employeesportal.model.Employee;
import com.ee.employeesportal.module.EmployeeResult;
import com.ee.employeesportal.repositories.JpaEmployeeRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Data
@Transactional
public class EmployeeService {
    private final JpaEmployeeRepository jpaEmployeeRepository;

    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long empId) {
        Optional<Employee> byId = jpaEmployeeRepository.findById(empId);
        if(byId.isEmpty()){
            throw new EmployeeException("employ id is not found");
        }
        return byId.get();
    }

    @Transactional(readOnly = true)
    public EmployeeResult searchEmployeeBy(String query, int page, int pageSize) {
        Sort sort= Sort.by(Direction.ASC,"firstName");
        Pageable pageableEmployee = PageRequest.of(page-1, pageSize, sort);
        return new EmployeeResult(jpaEmployeeRepository.findByFirstNameContainingOrLastNameContaining(query,query,pageableEmployee));

    }
}
