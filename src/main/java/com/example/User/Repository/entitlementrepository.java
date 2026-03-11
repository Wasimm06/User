package com.example.User.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.User.Entity.entitlement;

public interface entitlementrepository extends JpaRepository<entitlement, UUID> {

    Optional<entitlement> findByValue(String value);
}
