package com.example.User.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.User.Entity.meta;

public interface metarepository extends JpaRepository<meta, UUID> {

    List<meta> findByResourceType(String resourceType);

    List<meta> findByVersion(String version);

    List<meta> findByLocation(String location);
}
