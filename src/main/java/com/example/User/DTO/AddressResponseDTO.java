package com.example.User.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDTO {

    private String streetAddress;
    private String locality;
    private String region;
    private String postalCode;
    private String type;
    private String country;
}