package org.example.proiect_2.models;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String emailAddress;
}

