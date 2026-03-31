package com.example.User.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String externalId;
    private String userName;
    private Boolean active;
    private String preferredLanguage;
    private String timezone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "name_id")
    private name name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meta_id")
    private meta meta;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "resource_id")
    private List<address> addresses;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "resource_id")
    private List<email> emails;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "resource_id")
    private List<phonenumber> phoneNumbers;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "resource_id")
    private List<entitlement> entitlements;
}