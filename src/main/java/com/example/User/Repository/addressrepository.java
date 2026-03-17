package com.example.User.Repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.User.Entity.address;

public interface AddressRepository extends JpaRepository<address, UUID> {
    
}

