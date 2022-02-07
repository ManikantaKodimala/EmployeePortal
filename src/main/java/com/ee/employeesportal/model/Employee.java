package com.ee.employeesportal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    private Long empId;
    private String firstName;
    private String lastName;
    private String everestMailId;
    private String personalMailId;
    private String password;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoin;
    private String designation;
    private int experience;
    private String bio;
//    private Address presentAddress;
//    private Address permanentAddress;

}
