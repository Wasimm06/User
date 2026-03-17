package com.example.User.Entity;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class meta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String resourceType;

    private String location;

    private String created;

    private String lastModified;
}
