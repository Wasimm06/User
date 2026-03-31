package com.example.User.DTO;

import lombok.Data;

@Data
public class ValueDTO {

    private String value;
    private String type;
    private Boolean primary;
    private String givenName;
    private String familyName;
    private String middleName;
    private String honorificPrefix;
    private String honorificSuffix;
    private String streetAddress;
    private String locality;
    private String region;
    private String postalCode;
    private String country;

}