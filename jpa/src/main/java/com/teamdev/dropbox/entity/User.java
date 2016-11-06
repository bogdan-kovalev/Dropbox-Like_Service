package com.teamdev.dropbox.entity;

import lombok.Data;

import java.util.UUID;

/**
 * @author Bogdan Kovalev.
 */

@Data
public class User {

    private final Long id = UUID.randomUUID().getLeastSignificantBits();

    private String name;
    private String email;
    private String passwordHash;
    private byte[] passwordSalt;

    public User(String name, String email, String passwordHash, byte[] passwordSalt) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
    }
}
