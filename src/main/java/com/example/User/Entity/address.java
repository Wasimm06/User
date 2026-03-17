package com.example.User.Entity;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String streetAddress;

    private String locality;

    private String region;

    private String postalCode;

    private String country;

    private String type;
}
