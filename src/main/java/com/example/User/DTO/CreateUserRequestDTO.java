package com.example.User.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CreateUserRequestDTO {

    private List<String> schemas;
    private String externalId;
    private String userName;
    private Boolean active;   

    private NameDTO name;
    private List<EmailDTO> emails;
    private List<PhoneNumberDTO> phoneNumbers;
    private List<AddressDTO> addresses;
    private List<EntitlementDTO> entitlements;

    private MetaDTO meta;

    private String preferredLanguage;
    private String timezone;
}
