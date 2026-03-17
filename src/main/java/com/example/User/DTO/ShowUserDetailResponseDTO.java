package com.example.User.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowUserDetailResponseDTO {

    private List<String> schemas;

    private String id;
    private String externalId;
    private String userName;
    private Boolean active;

    private NameResponseDTO name;

    private List<EmailResponseDTO> emails;
    private List<PhoneNumberResponseDTO> phoneNumbers;
    private List<AddressResponseDTO> addresses;
    private List<EntitlementResponseDTO> entitlements;

    private String preferredLanguage;
    private String timezone;

    private MetaResponseDTO meta;
}