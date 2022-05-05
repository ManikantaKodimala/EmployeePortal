package com.ee.employeesportal.services;

import com.ee.employeesportal.advices.EmployeeException;
import com.ee.employeesportal.model.Employee;
import com.ee.employeesportal.repositories.JpaEmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {
    private EmployeeService employeeService;
    private JpaEmployeeRepository jpaEmployeeRepository;

    @BeforeEach
    void setUp() {
        jpaEmployeeRepository = mock(JpaEmployeeRepository.class);
        employeeService = new EmployeeService(jpaEmployeeRepository);
    }

    @Test
    void shouldSaveEmployeeSuccessfully() {
        Employee newEmployee = new Employee();
        newEmployee.setEverestMailId("manikanta@everest.engineering");
        given(jpaEmployeeRepository.findByEverestMailId(newEmployee.getEverestMailId())).willReturn(null);
        given(jpaEmployeeRepository.save(newEmployee)).willReturn(newEmployee);

        final Employee savedEmployee = employeeService.createEmployee(newEmployee);

        assertThat(savedEmployee).isNotNull();

        verify(jpaEmployeeRepository).save(any(Employee.class));
    }

    @Test
    void shouldReturnEmployeeWithGivenEmployeeId(){
        Long employeeId=1L;
        Employee newEmployee = new Employee();
        newEmployee.setEmpId(employeeId);
        given(jpaEmployeeRepository.findById(employeeId)).willReturn(Optional.of(newEmployee));

        Employee resultEmployee=employeeService.getEmployeeById(employeeId);

        assertThat(employeeId).isNotNull();
    }

    @Test
    void shouldThrowAnExceptionWhenEmployeeWithEverestEmailAlreadyExists() {
        Employee newEmployee = new Employee();
        newEmployee.setEverestMailId("manikanta@everest.engineering");
        given(jpaEmployeeRepository.findByEverestMailId(newEmployee.getEverestMailId())).willReturn(newEmployee);

        assertThrows(EmployeeException.class, () -> employeeService.createEmployee(newEmployee));

        verify(jpaEmployeeRepository, never()).save(any(Employee.class));

    }

    @Test
    void shouldThrowAnExceptionWhenEmployeeNotExist(){
        Long employeeId=1L;

        given(jpaEmployeeRepository.findById(employeeId)).willReturn(Optional.empty());

        assertThrows(EmployeeException.class,()->employeeService.getEmployeeById(employeeId));
    }

    @Test
    void shouldThrowAnExceptionWhileDeletingAnEmployeeWithEmployeeIdDoesNotExist(){
        Long employeeId=1L;

        given(jpaEmployeeRepository.findById(employeeId)).willReturn(Optional.empty());

        assertThrows(EmployeeException.class,()->employeeService.deleteEmployee(employeeId));
    }

    @Test
    void shouldReturnDescendingOrder() {
        assertTrue(employeeService.getSortDirection("desc").isDescending());
    }

    @Test
    void shouldNotReturnAscendingOrder() {
        assertFalse(employeeService.getSortDirection("desc").isAscending());
    }

}
