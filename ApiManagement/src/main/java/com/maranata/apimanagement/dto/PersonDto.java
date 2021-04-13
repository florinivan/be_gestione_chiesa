package com.maranata.apimanagement.dto;

import lombok.Data;

@Data
public class PersonDto {

    private Long id;
    private String personalNumber;
    private String name;
    private String surname;
    private String dateOfBirth;
    private AddressDto adress;

}
