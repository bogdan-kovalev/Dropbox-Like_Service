package com.teamdev.dropbox.serviceobjects;

import com.teamdev.dropbox.tinytypes.Email;
import com.teamdev.dropbox.tinytypes.Password;
import com.teamdev.dropbox.tinytypes.UserName;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Bogdan Kovalev.
 */

@RequiredArgsConstructor
@ToString
public class UserRegistrationInfo {

    public final UserName name;
    public final Email email;
    public final Password password;
}
