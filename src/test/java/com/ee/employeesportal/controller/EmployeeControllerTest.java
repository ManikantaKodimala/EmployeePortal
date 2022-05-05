package com.ee.employeesportal.controller;

import com.ee.employeesportal.dto.EmployeeDto;
import com.ee.employeesportal.model.Address;
import com.ee.employeesportal.model.Employee;
import com.ee.employeesportal.module.EmployeeResult;
import com.ee.employeesportal.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private EmployeeResult employeeResult;

    private List<EmployeeDto> employees;
    private Employee employee;
    @BeforeEach
    void  setUp(){
        this.employees=new ArrayList<>();
        Address presentAddress = new Address();
        employee= new Employee();
        employee.setFirstName("mani");
        employee.setId(1l);
        employee.setPresentAddress(presentAddress);
        employee.setPermanentAddress(presentAddress);
        EmployeeDto employeeDto= new EmployeeDto(employee);
        this.employees.add(employeeDto);
        Page<EmployeeDto> employeePage = new PageImpl<>(this.employees);
        employeeResult = new EmployeeResult(employeePage);
    }
    @Test
    void shouldFetchAllUsers() throws Exception {
        given(employeeService.getAllEmployees(1,5,null)).willReturn(this.employeeResult);
        this.mockMvc.perform(get("/api/employees?page=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", is(employees.size())));
    }

    @Test
    void shouldCreateNewEmployee() throws Exception {
        EmployeeDto newEmployee = this.employees.get(0);
        given(employeeService.createEmployee(any(Employee.class))).willAnswer((invocation) -> invocation.getArgument(0));
        this.mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEmployee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(newEmployee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(newEmployee.getLastName())));
        verify(employeeService).createEmployee(any(Employee.class));
    }

    @Test
    void shouldFetchEmployeeById() throws Exception {

        when(employeeService.getEmployeeById(employee.getId())).thenReturn(employee);
        this.mockMvc.perform(get("/api/employees/{empId}", employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(employee.getFirstName())));
        verify(employeeService).getEmployeeById(anyLong());
    }

    @Test
    void shouldSearchEmployeeByName() throws Exception {
        System.out.println(this.employeeResult.getData());
        given(employeeService.searchEmployeeBy("mani",1,5)).willReturn(this.employeeResult);

        this.mockMvc.perform(get("/api/employees/search?query=mani"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", is(employees.size())));
        verify(employeeService).searchEmployeeBy(anyString(),anyInt(),anyInt());
    }

}
