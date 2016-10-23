package com.teamdev.dropbox.dto;

/**
 * @author Bogdan Kovalev.
 */
public class UserDTO {

    public final long id;
    public final String name;
    public final String email;

    public UserDTO(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
