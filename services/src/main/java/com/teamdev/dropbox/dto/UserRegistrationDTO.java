package com.teamdev.dropbox.dto;

/**
 * @author Bogdan Kovalev.
 */
public class UserRegistrationDTO {

    public final String name;
    public final String email;
    public final String password;

    public UserRegistrationDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
