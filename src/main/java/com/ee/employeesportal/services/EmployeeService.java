package com.ee.employeesportal.services;

import com.ee.employeesportal.model.Employee;
import com.ee.employeesportal.module.EmployeeResult;
import com.ee.employeesportal.repositories.JpaEmployeeRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final JpaEmployeeRepository jpaEmployeeRepository;

    @Transactional(readOnly = true)
    public EmployeeResult getAllEmployees(int page, int pageSize, Sort sortBy) {
        if(sortBy == null){
            sortBy= Sort.by(Sort.Direction.ASC,"firstName");
        }
        Pageable pageableEmployee = PageRequest.of(page-1, pageSize, sortBy);
        return new EmployeeResult(jpaEmployeeRepository.findAll(pageableEmployee));
    }
}
