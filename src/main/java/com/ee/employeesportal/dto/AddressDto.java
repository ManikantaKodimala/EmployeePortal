package com.ee.employeesportal.dto;

import com.ee.employeesportal.model.Address;
import lombok.Data;

@Data
public class AddressDto {
    private int id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private long zipCode;
    private String state;
    private String country;

    public AddressDto(Address address) {
        this.id = address.getId();
        this.addressLine1 = address.getAddressLine1();
        this.addressLine2 = address.getAddressLine2();
        this.city = address.getCity();
        this.zipCode = address.getZipCode();
        this.state = address.getState();
        this.country = address.getCountry();
    }
}
