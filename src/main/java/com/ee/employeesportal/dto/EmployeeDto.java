package com.ee.employeesportal.dto;

import com.ee.employeesportal.model.Address;
import com.ee.employeesportal.model.Employee;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String everestEmailId;
    private String personalEmailId;
    private LocalDate dob;
    private LocalDate doj;
    private String designation;
    private int experienceInYears;
    private String bio;
    private AddressDto presentAddress;
    private AddressDto permanentAddress;

    public EmployeeDto(Employee employee) {
        this.id = employee.getEmpId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.dob = employee.getDateOfBirth();
        this.doj = employee.getDateOfJoin();
        this.everestEmailId = employee.getEverestMailId();
        this.personalEmailId = employee.getPersonalMailId();
        this.designation = employee.getDesignation();
        this.bio = employee.getBio();
        this.presentAddress = new AddressDto(employee.getPresentAddress());
        this.permanentAddress = new AddressDto(employee.getPermanentAddress());
        this.experienceInYears = employee.getExperience();
    }
}
