package com.ee.employeesportal.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private long zipCode;
    private String state;
    private String country;
}