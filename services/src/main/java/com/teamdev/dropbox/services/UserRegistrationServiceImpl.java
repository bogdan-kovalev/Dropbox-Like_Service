package com.teamdev.dropbox.services;

import com.teamdev.dropbox.dto.UserDTO;
import com.teamdev.dropbox.entity.User;
import com.teamdev.dropbox.repository.UserRepository;
import com.teamdev.dropbox.serviceobjects.UserRegistrationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Bogdan Kovalev.
 */
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO register(UserRegistrationInfo registrationInfo) throws Exception {
        final User newUser = new User(registrationInfo.name, registrationInfo.email, registrationInfo.password);
        this.userRepository.save(newUser);
        return new UserDTO(newUser.getId(), newUser.getName(), newUser.getEmail());
    }
}
