package io.github.wojtekmarcin.memobox.repository;

import io.github.wojtekmarcin.memobox.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
public interface UserRepository {
    List<User> findAll();

    boolean existsById(Long id);

    User findUserByUserId(Long id);

    void deleteUserByUserId(Long id);

    User save(User entity);

    Optional<User> findById(Long id);

    User findUserByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username LIKE %?1%")
    List<User> findAllUsersByUsername(String keyword);

    @Query("SELECT u FROM User u WHERE u.password LIKE %?1% ")
    List<User> findAllUsersByPassword(String keyword);
}
