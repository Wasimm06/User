package com.example.User.Repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.User.Entity.name;

public interface NameRepository extends JpaRepository<name, UUID> {
}