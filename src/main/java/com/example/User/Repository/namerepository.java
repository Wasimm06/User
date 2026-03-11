package com.example.User.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.User.Entity.name;

public interface namerepository extends JpaRepository<name, UUID> {

    List<name> findByGivenName(String givenName);

    List<name> findByFamilyName(String familyName);

    List<name> findByMiddleName(String middleName);

    List<name> findByHonorificPrefix(String honorificPrefix);

    List<name> findByHonorificSuffix(String honorificSuffix);
}
