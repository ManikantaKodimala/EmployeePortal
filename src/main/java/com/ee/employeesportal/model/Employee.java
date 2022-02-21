
package com.ee.employeesportal.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;
    private String firstName;
    private String lastName;
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(everest\\.engineering)$")
    private String everestMailId;
    @Email
    private String personalMailId;
    @Size(min = 4)
    private String password;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoin;
    private String designation;
    @Min(0)
    private int experience;
    private String bio;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "presentAddressId")
    private Address presentAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanentAddressId")
    private Address permanentAddress;
}