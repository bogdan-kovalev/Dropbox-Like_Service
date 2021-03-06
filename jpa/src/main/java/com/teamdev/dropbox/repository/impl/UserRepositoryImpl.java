package com.teamdev.dropbox.repository.impl;

import com.teamdev.dropbox.entity.User;
import com.teamdev.dropbox.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bogdan Kovalev.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Map<Long, User> idToUserMap;
    private final Map<String, Long> emailToIdMap;

    public UserRepositoryImpl() {
        idToUserMap = new HashMap<>();
        emailToIdMap = new HashMap<>();
    }

    @Override
    public void save(User user) throws Exception {
        final Long userId = user.getId();
        final boolean newUser = !idToUserMap.containsKey(userId);
        if (newUser) {
            final String userEmail = user.getEmail();
            final boolean userEmailAlreadyRegistered = emailToIdMap.containsKey(userEmail);
            if (userEmailAlreadyRegistered) {
                throw new Exception(String.format("User with email %s already registered", userEmail));
            } else {
                idToUserMap.put(userId, user);
                emailToIdMap.put(userEmail, userId);
            }
        }
    }

    @Override
    public User getById(Long id) throws Exception {
        final User user = idToUserMap.get(id);
        if (user == null) {
            throw new Exception(String.format("Can't find user with id %s", id));
        }
        return user;
    }

    @Override
    public User getByEmail(String email) throws Exception {
        final Long id = emailToIdMap.get(email);
        if (id == null) {
            throw new Exception(String.format("Can't find user with email %s", email));
        }
        return getById(id);
    }

    @Override
    public Collection<User> getAll() {
        return idToUserMap.values();
    }

    @Override
    public void delete(Long id) throws Exception {
        final String email = getById(id).getEmail();
        idToUserMap.remove(id);
        emailToIdMap.remove(email);
    }

    @Override
    public void deleteAll() {
        idToUserMap.clear();
        emailToIdMap.clear();
    }
}
