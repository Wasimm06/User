package com.example.User.Entity;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class phonenumber {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "phone_value")
    private String value;

    private String type;

    private Boolean primaryNumber;
}
