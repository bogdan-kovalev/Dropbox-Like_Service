package com.teamdev.dropbox.repository;

import com.teamdev.dropbox.entity.User;

import java.util.Collection;

/**
 * @author Bogdan Kovalev.
 */

public interface UserRepository {

    void save(User user) throws Exception;

    User getById(Long id) throws Exception;

    User getByEmail(String email) throws Exception;

    Collection<User> getAll();

    void delete(Long id) throws Exception;

    void deleteAll();
}
