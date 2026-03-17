package com.example.User.ServiceImplementation;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Resource resource = resourceRepository.getReferenceById(id);

        if (resource == null) {
            return null; // or throw an exception
        }

        ShowUserDetailResponseDTO response = new ShowUserDetailResponseDTO();

        response.setId(resource.getId().toString());
        response.setExternalId(resource.getExternalId());
        response.setUserName(resource.getUserName());
        response.setActive(resource.getActive());
        response.setSchemas(List.of("urn:ietf:params:scim:schemas:core:2.0:User"));

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
        return response; 
    }
}