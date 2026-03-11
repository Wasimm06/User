package com.example.User.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.User.Entity.address;

public interface addressrepository extends JpaRepository<address, UUID> {

    List<address> findByStreetAddress(String streetAddress);

    List<address> findByLocality(String locality);

    List<address> findByRegion(String region);

    List<address> findByPostalCode(String postalCode);

    List<address> findByCountry(String country);

    List<address> findByType(String type);
}
