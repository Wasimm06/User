package com.example.User.ServiceImplementation;

import java.util.List;
import java.util.UUID;
import java.util.Collections;
import java.util.stream.Collectors;

import com.example.User.DTO.AddressDTO;
import com.example.User.DTO.AddressResponseDTO;
import com.example.User.DTO.CreateUserRequestDTO;
import com.example.User.DTO.CreateUserResponseDTO;
import com.example.User.DTO.EmailDTO;
import com.example.User.DTO.EmailResponseDTO;
import com.example.User.DTO.EntitlementDTO;
import com.example.User.DTO.EntitlementResponseDTO;
import com.example.User.DTO.ListUserResponseDTO;
import com.example.User.DTO.MetaResponseDTO;
import com.example.User.DTO.NameResponseDTO;
import com.example.User.DTO.OperationDTO;
import com.example.User.DTO.PhoneNumberDTO;
import com.example.User.DTO.PhoneNumberResponseDTO;
import com.example.User.DTO.ShowUserDetailResponseDTO;
import com.example.User.DTO.UpdateUserRequestDTO;
import com.example.User.Service.UserService;

public class UserServiceImplementation implements UserService {

    @Override
    public CreateUserResponseDTO createUser(CreateUserRequestDTO requestDTO) {
        CreateUserResponseDTO responseDTO = new CreateUserResponseDTO();
        responseDTO.setSchemas(requestDTO.getSchemas());
        responseDTO.setId(requestDTO.getId() != null && !requestDTO.getId().isBlank() ? requestDTO.getId() : UUID.randomUUID().toString());
        responseDTO.setExternalId(requestDTO.getExternalId());
        responseDTO.setUserName(requestDTO.getUserName());
        responseDTO.setActive(Boolean.TRUE);
        responseDTO.setPreferredLanguage(requestDTO.getPreferredLanguage());
        responseDTO.setTimezone(requestDTO.getTimezone());

        if (requestDTO.getName() != null) {
            responseDTO.setName(NameResponseDTO.builder()
                    .familyName(requestDTO.getName().getFamilyName())
                    .givenName(requestDTO.getName().getGivenName())
                    .middleName(requestDTO.getName().getMiddleName())
                    .honorificPrefix(requestDTO.getName().getHonorificPrefix())
                    .honorificSuffix(requestDTO.getName().getHonorificSuffix())
                    .build());
        }

        responseDTO.setEmails(mapEmails(requestDTO.getEmails()));
        responseDTO.setPhoneNumbers(mapPhoneNumbers(requestDTO.getPhoneNumbers()));
        responseDTO.setAddresses(mapAddresses(requestDTO.getAddresses()));
        responseDTO.setEntitlements(mapEntitlements(requestDTO.getEntitlements()));

        if (requestDTO.getMeta() != null) {
            responseDTO.setMeta(MetaResponseDTO.builder()
                    .resourceType(requestDTO.getMeta().getResourceType())
                    .location(requestDTO.getMeta().getLocation())
                    .created(requestDTO.getMeta().getCreated())
                    .lastModified(requestDTO.getMeta().getLastModified())
                    .build());
        }

        return responseDTO;
    }

    @Override
    public ListUserResponseDTO listUsers(String filter, int startIndex, int count) {
        ListUserResponseDTO responseDTO = new ListUserResponseDTO();
        responseDTO.setSchemas(Collections.singletonList("urn:ietf:params:scim:api:messages:2.0:ListResponse"));
        responseDTO.setStartIndex(Math.max(startIndex, 1));
        responseDTO.setItemsPerPage(Math.max(count, 0));
        responseDTO.setTotalResults(0);
        responseDTO.setResources(Collections.emptyList());
        return responseDTO;
    }

    @Override
    public CreateUserResponseDTO updateUser(String id, UpdateUserRequestDTO requestDTO) {
        CreateUserResponseDTO responseDTO = new CreateUserResponseDTO();
        responseDTO.setId(id);
        if (requestDTO == null) {
            return responseDTO;
        }

        responseDTO.setSchemas(requestDTO.getSchemas());
        if (requestDTO.getOperations() == null) {
            return responseDTO;
        }

        for (OperationDTO operation : requestDTO.getOperations()) {
            if (operation == null || operation.getPath() == null) {
                continue;
            }

            String path = operation.getPath().trim().toLowerCase();
            String value = firstOperationValue(operation);

            switch (path) {
                case "externalid":
                    responseDTO.setExternalId(value);
                    break;
                case "username":
                    responseDTO.setUserName(value);
                    break;
                case "preferredlanguage":
                    responseDTO.setPreferredLanguage(value);
                    break;
                case "timezone":
                    responseDTO.setTimezone(value);
                    break;
                case "active":
                    if (value != null) {
                        responseDTO.setActive(Boolean.parseBoolean(value));
                    }
                    break;
                case "entitlements":
                    if (operation.getValue() != null) {
                        responseDTO.setEntitlements(mapEntitlements(operation.getValue().stream()
                                .filter(item -> item != null && item.getValue() != null)
                                .map(item -> {
                                    EntitlementDTO entitlementDTO = new EntitlementDTO();
                                    entitlementDTO.setValue(item.getValue());
                                    return entitlementDTO;
                                })
                                .collect(Collectors.toList())));
                    }
                    break;
                default:
                    break;
            }
        }

        return responseDTO;
    }

    @Override
    public ShowUserDetailResponseDTO showUserDetails(String id) {
        ShowUserDetailResponseDTO responseDTO = new ShowUserDetailResponseDTO();
        responseDTO.setSchemas(Collections.singletonList("urn:ietf:params:scim:schemas:core:2.0:User"));
        responseDTO.setId(id);
        responseDTO.setActive(Boolean.TRUE);
        return responseDTO;
    }

    @Override
    public void deleteUser(String id) {
        if (id == null || id.isBlank()) {
            return;
        }
    }

    private List<EmailResponseDTO> mapEmails(List<EmailDTO> emails) {
        if (emails == null) {
            return null;
        }
        return emails.stream()
                .map(email -> EmailResponseDTO.builder()
                        .type(email.getType())
                        .primary(email.getPrimary())
                        .value(email.getValue())
                        .build())
                .collect(Collectors.toList());
    }

    private List<PhoneNumberResponseDTO> mapPhoneNumbers(List<PhoneNumberDTO> phoneNumbers) {
        if (phoneNumbers == null) {
            return null;
        }
        return phoneNumbers.stream()
                .map(phoneNumber -> PhoneNumberResponseDTO.builder()
                        .value(phoneNumber.getValue())
                        .type(phoneNumber.getType())
                        .primary(phoneNumber.getPrimary())
                        .build())
                .collect(Collectors.toList());
    }

    private List<AddressResponseDTO> mapAddresses(List<AddressDTO> addresses) {
        if (addresses == null) {
            return null;
        }
        return addresses.stream()
                .map(address -> AddressResponseDTO.builder()
                        .streetAddress(address.getStreetAddress())
                        .locality(address.getLocality())
                        .region(address.getRegion())
                        .postalCode(address.getPostalCode())
                        .type(address.getType())
                        .country(address.getCountry())
                        .build())
                .collect(Collectors.toList());
    }

    private List<EntitlementResponseDTO> mapEntitlements(List<EntitlementDTO> entitlements) {
        if (entitlements == null) {
            return null;
        }
        return entitlements.stream()
                .map(entitlement -> EntitlementResponseDTO.builder()
                        .value(entitlement.getValue())
                        .build())
                .collect(Collectors.toList());
    }

    private String firstOperationValue(OperationDTO operation) {
        if (operation.getValue() == null || operation.getValue().isEmpty() || operation.getValue().get(0) == null) {
            return null;
        }
        return operation.getValue().get(0).getValue();
    }
}
