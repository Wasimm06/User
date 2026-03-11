package com.example.User.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.User.Entity.email;

public interface emailrepository extends JpaRepository<email, UUID> {

    Optional<email> findByValue(String value);

    List<email> findByType(String type);

    List<email> findByPrimary(Boolean primary);

    List<email> findByTypeAndPrimary(String type, Boolean primary);
}
