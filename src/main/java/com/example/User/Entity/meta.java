package com.example.User.Entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class meta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "resource_type")
    private String resourceType;

    @Column(name = "created")
    private OffsetDateTime created;

    @Column(name = "last_modified")
    private OffsetDateTime lastModified;

    @Column(name = "version")
    private String version;

    @Column(name = "location")
    private String location;
}

