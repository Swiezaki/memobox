package io.github.wojtekmarcin.memobox.repository;

import io.github.wojtekmarcin.memobox.entities.MemoBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlMemoBoxRepository extends MemoBoxRepository, JpaRepository<MemoBox, Long> {
}
