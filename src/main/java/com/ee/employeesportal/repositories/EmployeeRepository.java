package com.ee.employeesportal.repositories;

import com.ee.employeesportal.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmployeeRepository {
    private AtomicLong NEXT_ID = new AtomicLong(0);
    private Map<Long, Employee> EMPLOYEES = new HashMap<>();

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(EMPLOYEES.values());
    }

    public Employee getEmployeeById(Long id) {
        return EMPLOYEES.get(id);
    }

    public Employee createEmployee(Employee employee) {
        employee.setEmpId(NEXT_ID.incrementAndGet());
        EMPLOYEES.put(employee.getEmpId(), employee);
        return employee;
    }

    public Employee updateEmployeeById(Employee employee) {
        if (!EMPLOYEES.containsKey(employee.getEmpId())) {
            System.out.println("wrong ID");
            return null;
        }
        EMPLOYEES.put(employee.getEmpId(), employee);
        return EMPLOYEES.get(employee.getEmpId());
    }

    public Employee deleteEmployeeById(Long empId) {
        if (!EMPLOYEES.containsKey(empId)) {
            System.out.println("wrong ID");
            return null;
        }
        return EMPLOYEES.remove(empId);
    }

}
