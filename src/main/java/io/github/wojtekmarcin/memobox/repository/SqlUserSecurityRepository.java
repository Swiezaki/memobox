package io.github.wojtekmarcin.memobox.repository;

import io.github.wojtekmarcin.memobox.security.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlUserSecurityRepository extends UserSecurityRepository, JpaRepository<UserSecurity, Long> {
}
