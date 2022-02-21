package com.ee.employeesportal.repositories;

import com.ee.employeesportal.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findByFirstNameContainingOrLastNameContaining(String query, String s, Pageable sortedByName);

}
