package io.github.wojtekmarcin.memobox.repository;

import io.github.wojtekmarcin.memobox.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    @RestResource(exported = false)
    void delete(User user);

    @RestResource(path = "deleteUserId", rel = "deleteUserId")
    void deleteById(@Param("id") Long userId);

    @RestResource(path = "user", rel = "user")
    List<User> findUserByUser_id(@Param("id") Long userId);
}
