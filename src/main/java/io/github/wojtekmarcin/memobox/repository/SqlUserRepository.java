package io.github.wojtekmarcin.memobox.repository;

import io.github.wojtekmarcin.memobox.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
interface SqlUserRepository extends UserRepository, JpaRepository<User, Long> {
}
