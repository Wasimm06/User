package com.example.User.ServiceImplementation;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.User.DTO.*;
import com.example.User.Entity.*;
import com.example.User.Repository.ResourceRepository;
import com.example.User.Service.UserManagementService;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    private ResourceRepository resourceRepository;

    @Autowired
    public UserManagementServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

   @Override
public CreateUserResponseDTO createUser(CreateUserRequestDTO requestDTO) {

    // ---------- ENTITY ----------
    Resource resource = new Resource();

    resource.setExternalId(requestDTO.getExternalId());
    resource.setUserName(requestDTO.getUserName());

    // active generated automatically
    resource.setActive(true);

    resource.setPreferredLanguage(requestDTO.getPreferredLanguage());
    resource.setTimezone(requestDTO.getTimezone());

    // ---------- NAME ----------
    if (requestDTO.getName() != null) {

        NameDTO dto = requestDTO.getName();

        name nameEntity = new name();
        nameEntity.setGivenName(dto.getGivenName());
        nameEntity.setFamilyName(dto.getFamilyName());
        nameEntity.setMiddleName(dto.getMiddleName());
        nameEntity.setHonorificPrefix(dto.getHonorificPrefix());
        nameEntity.setHonorificSuffix(dto.getHonorificSuffix());

        resource.setName(nameEntity);
    }

    // ---------- EMAILS ----------
    if (requestDTO.getEmails() != null) {

        List<email> emailList = new ArrayList<>();

        for (EmailDTO dto : requestDTO.getEmails()) {

            email emailEntity = new email();
            emailEntity.setValue(dto.getValue());
            emailEntity.setType(dto.getType());
            emailEntity.setPrimaryEmail(dto.getPrimary());

            emailList.add(emailEntity);
        }

        resource.setEmails(emailList);
    }

    // ---------- PHONE NUMBERS ----------
    if (requestDTO.getPhoneNumbers() != null) {

        List<phonenumber> phoneList = new ArrayList<>();

        for (PhoneNumberDTO dto : requestDTO.getPhoneNumbers()) {

            phonenumber phone = new phonenumber();
            phone.setValue(dto.getValue());
            phone.setType(dto.getType());
            phone.setPrimaryNumber(dto.getPrimary());

            phoneList.add(phone);
        }

        resource.setPhoneNumbers(phoneList);
    }

    // ---------- ADDRESSES ----------
    if (requestDTO.getAddresses() != null) {

        List<address> addressList = new ArrayList<>();

        for (AddressDTO dto : requestDTO.getAddresses()) {

            address addr = new address();
            addr.setStreetAddress(dto.getStreetAddress());
            addr.setLocality(dto.getLocality());
            addr.setRegion(dto.getRegion());
            addr.setPostalCode(dto.getPostalCode());
            addr.setCountry(dto.getCountry());
            addr.setType(dto.getType());

            addressList.add(addr);
        }

        resource.setAddresses(addressList);
    }

    // ---------- ENTITLEMENTS ----------
    if (requestDTO.getEntitlements() != null) {

        List<entitlement> entList = new ArrayList<>();

        for (EntitlementDTO dto : requestDTO.getEntitlements()) {

            entitlement ent = new entitlement();
            ent.setValue(dto.getValue());

            entList.add(ent);
        }

        resource.setEntitlements(entList);
    }

    // ---------- META (AUTO GENERATED) ----------
    meta metaEntity = new meta();

    metaEntity.setResourceType("User");
    metaEntity.setCreated(Instant.now().toString());
    metaEntity.setLastModified(Instant.now().toString());
    metaEntity.setLocation("/v1/users");

    resource.setMeta(metaEntity);

    // ---------- SAVE ----------
    Resource savedResource = resourceRepository.save(resource);

    // ---------- RESPONSE ----------
    CreateUserResponseDTO response = new CreateUserResponseDTO();

    response.setId(savedResource.getId().toString());
    response.setExternalId(savedResource.getExternalId());
    response.setUserName(savedResource.getUserName());
    response.setActive(savedResource.getActive());
    response.setSchemas(requestDTO.getSchemas());

    // ✅ ADDED (so response also shows it)
    response.setPreferredLanguage(savedResource.getPreferredLanguage());
    response.setTimezone(savedResource.getTimezone());

    // ---------- NAME RESPONSE ----------
    if (savedResource.getName() != null) {

        NameResponseDTO nameResp = new NameResponseDTO();

        nameResp.setGivenName(savedResource.getName().getGivenName());
        nameResp.setFamilyName(savedResource.getName().getFamilyName());
        nameResp.setMiddleName(savedResource.getName().getMiddleName());
        nameResp.setHonorificPrefix(savedResource.getName().getHonorificPrefix());
        nameResp.setHonorificSuffix(savedResource.getName().getHonorificSuffix());

        response.setName(nameResp);
    }

    // ---------- EMAIL RESPONSE ----------
    if (savedResource.getEmails() != null) {

        List<EmailResponseDTO> emailResponses = new ArrayList<>();

        for (email e : savedResource.getEmails()) {

            EmailResponseDTO dto = new EmailResponseDTO();

            dto.setValue(e.getValue());
            dto.setType(e.getType());
            dto.setPrimary(e.getPrimaryEmail());

            emailResponses.add(dto);
        }

        response.setEmails(emailResponses);
    }

    // ---------- PHONE RESPONSE ----------
    if (savedResource.getPhoneNumbers() != null) {

        List<PhoneNumberResponseDTO> phoneResponses = new ArrayList<>();

        for (phonenumber p : savedResource.getPhoneNumbers()) {

            PhoneNumberResponseDTO dto = new PhoneNumberResponseDTO();

            dto.setValue(p.getValue());
            dto.setType(p.getType());
            dto.setPrimary(p.getPrimaryNumber());

            phoneResponses.add(dto);
        }

        response.setPhoneNumbers(phoneResponses);
    }

    // ---------- ADDRESS RESPONSE ----------
    if (savedResource.getAddresses() != null) {

        List<AddressResponseDTO> addrResponses = new ArrayList<>();

        for (address a : savedResource.getAddresses()) {

            AddressResponseDTO dto = new AddressResponseDTO();

            dto.setStreetAddress(a.getStreetAddress());
            dto.setLocality(a.getLocality());
            dto.setRegion(a.getRegion());
            dto.setPostalCode(a.getPostalCode());
            dto.setCountry(a.getCountry());
            dto.setType(a.getType());

            addrResponses.add(dto);
        }

        response.setAddresses(addrResponses);
    }

    return response;
}

    @Override
public ShowUserDetailResponseDTO showUserDetails(UUID id) {

   Resource resource = resourceRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    
    ShowUserDetailResponseDTO response = new ShowUserDetailResponseDTO();

    response.setId(resource.getId().toString());
    response.setExternalId(resource.getExternalId());
    response.setUserName(resource.getUserName());
    response.setActive(resource.getActive());
    response.setSchemas(List.of("urn:ietf:params:scim:schemas:core:2.0:User"));

    // ✅ ADDED (missing before)
    response.setPreferredLanguage(resource.getPreferredLanguage());
    response.setTimezone(resource.getTimezone());

    // ---------- NAME RESPONSE ----------
    if (resource.getName() != null) {

        NameResponseDTO nameResp = new NameResponseDTO();

        nameResp.setGivenName(resource.getName().getGivenName());
        nameResp.setFamilyName(resource.getName().getFamilyName());
        nameResp.setMiddleName(resource.getName().getMiddleName());
        nameResp.setHonorificPrefix(resource.getName().getHonorificPrefix());
        nameResp.setHonorificSuffix(resource.getName().getHonorificSuffix());

        response.setName(nameResp);
    }

    // ---------- EMAIL RESPONSE ----------
    if (resource.getEmails() != null) {

        List<EmailResponseDTO> emailResponses = new ArrayList<>();

        for (email e : resource.getEmails()) {

            EmailResponseDTO dto = new EmailResponseDTO();

            dto.setValue(e.getValue());
            dto.setType(e.getType());
            dto.setPrimary(e.getPrimaryEmail());

            emailResponses.add(dto);
        }

        response.setEmails(emailResponses);
    }

    // ---------- PHONE RESPONSE ----------
    if (resource.getPhoneNumbers() != null) {

        List<PhoneNumberResponseDTO> phoneResponses = new ArrayList<>();

        for (phonenumber p : resource.getPhoneNumbers()) {

            PhoneNumberResponseDTO dto = new PhoneNumberResponseDTO();

            dto.setValue(p.getValue());
            dto.setType(p.getType());
            dto.setPrimary(p.getPrimaryNumber());

            phoneResponses.add(dto);
        }

        response.setPhoneNumbers(phoneResponses);
    }

    // ---------- ADDRESS RESPONSE ----------
    if (resource.getAddresses() != null) {

        List<AddressResponseDTO> addrResponses = new ArrayList<>();

        for (address a : resource.getAddresses()) {

            AddressResponseDTO dto = new AddressResponseDTO();

            dto.setStreetAddress(a.getStreetAddress());
            dto.setLocality(a.getLocality());
            dto.setRegion(a.getRegion());
            dto.setPostalCode(a.getPostalCode());
            dto.setCountry(a.getCountry());
            dto.setType(a.getType());

            addrResponses.add(dto);
        }
        response.setAddresses(addrResponses);
    }

    // ---------- ENTITLEMENTS ----------
    if (resource.getEntitlements() != null) {

        List<EntitlementResponseDTO> entList = new ArrayList<>();

        for (entitlement e : resource.getEntitlements()) {
            EntitlementResponseDTO dto = new EntitlementResponseDTO();
            dto.setValue(e.getValue());
            entList.add(dto);
        }

        response.setEntitlements(entList);
    }
    // ---------- META ----------
    if (resource.getMeta() != null) {
        MetaResponseDTO metaDto = new MetaResponseDTO();
        metaDto.setResourceType(resource.getMeta().getResourceType());
        metaDto.setCreated(resource.getMeta().getCreated());
        metaDto.setLastModified(resource.getMeta().getLastModified());
        metaDto.setLocation(resource.getMeta().getLocation());
        response.setMeta(metaDto);
    }

    return response;
}

  @Override
public ListUserResponseDTO listUsers(String filter, int startIndex, int count) {

    List<Resource> resources = resourceRepository.findAll();

    List<ResourceResponseDTO> users = new ArrayList<>();

    for (Resource resource : resources) {

        ResourceResponseDTO dto = new ResourceResponseDTO();

        dto.setSchemas(List.of("urn:ietf:params:scim:schemas:core:2.0:User"));

        // ---------- BASIC ----------
        dto.setId(resource.getId().toString());
        dto.setUserName(resource.getUserName());
        dto.setExternalId(resource.getExternalId());
        dto.setActive(resource.getActive());

        // ---------- NAME ----------
        if (resource.getName() != null) {
            NameResponseDTO nameResp = new NameResponseDTO();
            nameResp.setGivenName(resource.getName().getGivenName());
            nameResp.setFamilyName(resource.getName().getFamilyName());
            nameResp.setMiddleName(resource.getName().getMiddleName());
            nameResp.setHonorificPrefix(resource.getName().getHonorificPrefix());
            nameResp.setHonorificSuffix(resource.getName().getHonorificSuffix());
            dto.setName(nameResp);
        }

        // ---------- EMAIL ----------
        if (resource.getEmails() != null) {
            List<EmailResponseDTO> emailList = new ArrayList<>();

            for (email e : resource.getEmails()) {
                EmailResponseDTO emailDto = new EmailResponseDTO();
                emailDto.setValue(e.getValue());
                emailDto.setType(e.getType());
                emailDto.setPrimary(e.getPrimaryEmail());
                emailList.add(emailDto);
            }

            dto.setEmails(emailList);
        }

        // ---------- PHONE ----------
        if (resource.getPhoneNumbers() != null) {
            List<PhoneNumberResponseDTO> phoneList = new ArrayList<>();
            for (phonenumber p : resource.getPhoneNumbers()) {
                PhoneNumberResponseDTO phoneDto = new PhoneNumberResponseDTO();
                phoneDto.setValue(p.getValue());
                phoneDto.setType(p.getType());
                phoneDto.setPrimary(p.getPrimaryNumber());
                phoneList.add(phoneDto);
            }
            dto.setPhoneNumbers(phoneList);
        }

        // ---------- ADDRESS ----------
        if (resource.getAddresses() != null) {
            List<AddressResponseDTO> addrList = new ArrayList<>();
            for (address a : resource.getAddresses()) {
                AddressResponseDTO addrDto = new AddressResponseDTO();
                addrDto.setStreetAddress(a.getStreetAddress());
                addrDto.setLocality(a.getLocality());
                addrDto.setRegion(a.getRegion());
                addrDto.setPostalCode(a.getPostalCode());
                addrDto.setCountry(a.getCountry());
                addrDto.setType(a.getType());
                addrList.add(addrDto);
            }
            dto.setAddresses(addrList);
        }

        // ---------- ENTITLEMENTS ----------
        if (resource.getEntitlements() != null) {
            List<EntitlementResponseDTO> entList = new ArrayList<>();

            for (entitlement e : resource.getEntitlements()) {
                EntitlementResponseDTO entDto = new EntitlementResponseDTO();
                entDto.setValue(e.getValue());
                entList.add(entDto);
            }

            dto.setEntitlements(entList);
        }
        dto.setPreferredLanguage(resource.getPreferredLanguage());
        dto.setTimezone(resource.getTimezone());

        users.add(dto);
    }

    ListUserResponseDTO result = new ListUserResponseDTO();

    result.setSchemas(List.of("urn:ietf:params:scim:api:messages:2.0:ListResponse"));
    result.setTotalResults(users.size());
    result.setStartIndex(startIndex);
    result.setItemsPerPage(count);
    result.setResources(users);

    return result;
}

// Patch for update and delete can be added here when needed

@Override
public CreateUserResponseDTO updateUser(UUID id, UpdateUserRequestDTO requestDTO) {

    Resource resource = resourceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (requestDTO.getOperations() == null || requestDTO.getOperations().isEmpty()) {
        throw new RuntimeException("Operations cannot be null");
    }

    for (OperationDTO op : requestDTO.getOperations()) {

        String operation = op.getOp();
        String path = op.getPath();
        List<ValueDTO> values = op.getValue();

        // ================= REPLACE =================
        if ("replace".equalsIgnoreCase(operation)) {

            // ---------- USERNAME ----------
            if ("userName".equalsIgnoreCase(path) && values != null && !values.isEmpty()) {
                resource.setUserName(values.get(0).getValue());
            }

            // ---------- ACTIVE ----------
            if ("active".equalsIgnoreCase(path) && values != null && !values.isEmpty()) {
                resource.setActive(Boolean.parseBoolean(values.get(0).getValue()));
            }

            // ---------- NAME ----------
            if ("name".equalsIgnoreCase(path) && values != null && !values.isEmpty()) {

                ValueDTO v = values.get(0);

                name nameEntity = resource.getName() != null ? resource.getName() : new name();

                nameEntity.setGivenName(v.getGivenName());
                nameEntity.setFamilyName(v.getFamilyName());
                nameEntity.setMiddleName(v.getMiddleName());
                nameEntity.setHonorificPrefix(v.getHonorificPrefix());
                nameEntity.setHonorificSuffix(v.getHonorificSuffix());

                resource.setName(nameEntity);
            }

            // ---------- EMAIL ----------
            if ("emails".equalsIgnoreCase(path) && values != null) {

                List<email> emailList = new ArrayList<>();

                for (ValueDTO v : values) {
                    email e = new email();
                    e.setValue(v.getValue());
                    e.setType(v.getType());
                    e.setPrimaryEmail(v.getPrimary());
                    emailList.add(e);
                }

                resource.setEmails(emailList);
            }

            // ---------- PHONE ----------
            if ("phoneNumbers".equalsIgnoreCase(path) && values != null) {

                List<phonenumber> phoneList = new ArrayList<>();

                for (ValueDTO v : values) {
                    phonenumber p = new phonenumber();
                    p.setValue(v.getValue());
                    p.setType(v.getType());
                    p.setPrimaryNumber(v.getPrimary());
                    phoneList.add(p);
                }

                resource.setPhoneNumbers(phoneList);
            }

            // ---------- ADDRESS ----------
            if ("addresses".equalsIgnoreCase(path) && values != null) {

                List<address> addrList = new ArrayList<>();

                for (ValueDTO v : values) {
                    address a = new address();
                    a.setStreetAddress(v.getStreetAddress());
                    a.setLocality(v.getLocality());
                    a.setRegion(v.getRegion());
                    a.setPostalCode(v.getPostalCode());
                    a.setCountry(v.getCountry());
                    a.setType(v.getType());
                    addrList.add(a);
                }

                resource.setAddresses(addrList);
            }

            // ---------- ENTITLEMENTS ----------
            if ("entitlements".equalsIgnoreCase(path) && values != null) {

                List<entitlement> entList = new ArrayList<>();

                for (ValueDTO v : values) {
                    entitlement ent = new entitlement();
                    ent.setValue(v.getValue());
                    entList.add(ent);
                }

                resource.setEntitlements(entList);
            }
        }

        // ================= ADD =================
        if ("add".equalsIgnoreCase(operation)) {

            if ("entitlements".equalsIgnoreCase(path) && values != null) {

                List<entitlement> entList = resource.getEntitlements();

                if (entList == null) {
                    entList = new ArrayList<>();
                }

                for (ValueDTO v : values) {

    boolean exists = false;

            for (entitlement e : entList) {
         if (e.getValue().equalsIgnoreCase(v.getValue())) {
            exists = true;
            break;
            }
     }

        if (!exists) {
            entitlement ent = new entitlement();
             ent.setValue(v.getValue());
            entList.add(ent);
    }
}

                resource.setEntitlements(entList);
            }
        }

        // ================= REMOVE =================
        if ("remove".equalsIgnoreCase(operation)) {

            if ("entitlements".equalsIgnoreCase(path)) {
                if (resource.getEntitlements() != null) {
                    resource.getEntitlements().clear();
                }
            }

            if ("emails".equalsIgnoreCase(path)) {
                resource.setEmails(null);
            }

            if ("phoneNumbers".equalsIgnoreCase(path)) {
                resource.setPhoneNumbers(null);
            }

            if ("addresses".equalsIgnoreCase(path)) {
                resource.setAddresses(null);
            }

            if ("name".equalsIgnoreCase(path)) {
                resource.setName(null);
            }
        }
    }

    // ================= META =================
    if (resource.getMeta() == null) {
        resource.setMeta(new meta());
    }
    resource.getMeta().setLastModified(Instant.now().toString());

    Resource updated = resourceRepository.save(resource);

    // ================= RESPONSE =================
    CreateUserResponseDTO response = new CreateUserResponseDTO();

    response.setId(updated.getId().toString());
    response.setUserName(updated.getUserName());
    response.setExternalId(updated.getExternalId());
    response.setActive(updated.getActive());

    // OPTIONAL: return entitlements
    if (updated.getEntitlements() != null) {
        List<EntitlementResponseDTO> entResponses = new ArrayList<>();
        for (entitlement e : updated.getEntitlements()) {
            EntitlementResponseDTO dto = new EntitlementResponseDTO();
            dto.setValue(e.getValue());
            entResponses.add(dto);
        }
        response.setEntitlements(entResponses);
    }

    // OPTIONAL: return meta
    if (updated.getMeta() != null) {
        MetaResponseDTO metaResp = new MetaResponseDTO();
        metaResp.setResourceType(updated.getMeta().getResourceType());
        metaResp.setCreated(updated.getMeta().getCreated());
        metaResp.setLastModified(updated.getMeta().getLastModified());
        metaResp.setLocation(updated.getMeta().getLocation());
        response.setMeta(metaResp);
    }

    return response;
}

//  Delete user implementation
@Override
public void deleteUser(UUID id) {    
    if (!resourceRepository.existsById(id)) {
        throw new RuntimeException("User not found");
    }
    resourceRepository.deleteById(id);
}

}


