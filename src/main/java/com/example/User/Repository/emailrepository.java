package com.example.User.Repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.User.Entity.email;

public interface EmailRepository extends JpaRepository<email, UUID> {
}