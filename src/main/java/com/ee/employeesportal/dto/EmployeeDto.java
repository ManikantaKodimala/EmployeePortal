package com.ee.employeesportal.dto;

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
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.dob = employee.getDob();
        this.doj = employee.getDoj();
        this.everestEmailId = employee.getEverestEmailId();
        this.personalEmailId = employee.getPersonalEmailId();
        this.designation = employee.getDesignation();
        this.bio = employee.getBio();
        this.presentAddress = new AddressDto(employee.getPresentAddress());
        this.permanentAddress = new AddressDto(employee.getPermanentAddress());
        this.experienceInYears = employee.getExperienceInYears();
    }
}
