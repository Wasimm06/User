package com.example.User.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.User.Entity.phonenumber;

public interface phonenumberrepository extends JpaRepository<phonenumber, UUID> {

    Optional<phonenumber> findByValue(String value);

    List<phonenumber> findByType(String type);

    List<phonenumber> findByPrimary(Boolean primary);

    List<phonenumber> findByTypeAndPrimary(String type, Boolean primary);
}
