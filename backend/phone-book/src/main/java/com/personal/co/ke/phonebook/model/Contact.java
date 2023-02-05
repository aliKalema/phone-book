package com.personal.co.ke.phonebook.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String otherNames;
    private String phone;
    private String email;
    private String image;
    @ManyToOne
    private Country country;
    @Transient
    private int countryId;
}
