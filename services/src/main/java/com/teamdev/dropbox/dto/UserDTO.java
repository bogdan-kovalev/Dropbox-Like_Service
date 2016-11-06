package com.teamdev.dropbox.dto;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Bogdan Kovalev.
 */

@RequiredArgsConstructor
@ToString
public class UserDTO {

    public final Long id;
    public final String name;
    public final String email;
}
