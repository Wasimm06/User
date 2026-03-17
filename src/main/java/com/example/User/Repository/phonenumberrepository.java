package com.example.User.Repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.User.Entity.phonenumber;

public interface PhoneNumberRepository extends JpaRepository<phonenumber, UUID> {
}