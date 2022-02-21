package com.ee.employeesportal.repositories;

import com.ee.employeesportal.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmployeeRepository extends JpaRepository<Employee,Integer> {

}
