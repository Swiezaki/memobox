package io.github.wojtekmarcin.memobox.repository;

import io.github.wojtekmarcin.memobox.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();

    boolean existsById(Long id);

    User findUserByUserId(Long id);

    void deleteUserByUserId(Long id);

    User save(User entity);
}
