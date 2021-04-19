package io.github.wojtekmarcin.memobox.repository.SQL;

import io.github.wojtekmarcin.memobox.entities.MemoBox;
import io.github.wojtekmarcin.memobox.repository.MemoBoxRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlMemoBoxRepository extends MemoBoxRepository, JpaRepository<MemoBox, Long> {
}
