package com.ee.employeesportal.repositories;

import com.ee.employeesportal.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaEmployeeRepository extends JpaRepository<Employee,Integer> {
    List<Employee> findAllByOrderByFirstNameAsc();

    List<Employee> findAllByOrderByDateOfJoin();
}
