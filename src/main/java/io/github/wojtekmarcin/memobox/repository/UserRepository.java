package io.github.wojtekmarcin.memobox.repository;

import io.github.wojtekmarcin.memobox.entities.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository {
    List<User> findAllUsers();

    List<User> findUserByUser_id(@Param("id") Integer userId);

    User save(User entity);
}
