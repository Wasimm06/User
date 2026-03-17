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
public class ResourceResponseDTO {

    private List<String> schemas;   // optional inside list item

    private String externalId;
    private String userName;
    private NameResponseDTO name;
    private Boolean active;

    private List<EmailResponseDTO> emails;
    private List<PhoneNumberResponseDTO> phoneNumbers;
    private List<AddressResponseDTO> addresses;
    private List<EntitlementResponseDTO> entitlements;

    private String id;
    private MetaResponseDTO meta;

    private String preferredLanguage;
    private String timezone;
}