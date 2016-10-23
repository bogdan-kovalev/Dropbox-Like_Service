package com.teamdev.dropbox.services;

import com.teamdev.dropbox.dto.UserDTO;
import com.teamdev.dropbox.dto.UserRegistrationDTO;
import com.teamdev.dropbox.entity.User;
import com.teamdev.dropbox.repository.UserRepository;
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
    public UserDTO register(UserRegistrationDTO registrationData) {
        final User newUser = new User(registrationData.name, registrationData.email, registrationData.password);
        this.userRepository.save(newUser);
        return new UserDTO(newUser.getId(), newUser.getName(), newUser.getEmail());
    }
}
