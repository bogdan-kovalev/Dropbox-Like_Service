package com.teamdev.dropbox.repository;

import com.teamdev.dropbox.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bogdan Kovalev.
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
