package com.example.User.Repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.User.Entity.entitlement;

public interface EntitlementRepository extends JpaRepository<entitlement, UUID> {
}