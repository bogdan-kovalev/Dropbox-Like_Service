package com.teamdev.dropbox.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Bogdan Kovalev.
 */

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private String id;

    private String name;
    private String email;
    private String passwordHash;

    public User(String name, String email, String passwordHash) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
