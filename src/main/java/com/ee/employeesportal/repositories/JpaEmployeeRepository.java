package com.ee.employeesportal.repositories;

import com.ee.employeesportal.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaEmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findAllByOrderByFirstNameAsc();

    List<Employee> findAllByOrderByDateOfJoin();

    List<Employee> findAllByFirstName(String firstName);

    Page<Employee> findByFirstNameContainingOrLastNameContaining(String query, String s, Pageable sortedByName);

}
