package com.maranata.Api.dto;

import lombok.Data;

@Data
public class AddressDto {

    private String street;
    private String city;
    private String district;
    private String region;
    private String country;
}

