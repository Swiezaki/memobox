package io.github.wojtekmarcin.memobox.repository.SQL;

import io.github.wojtekmarcin.memobox.entities.Word;
import io.github.wojtekmarcin.memobox.repository.WordRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlWordReposiotry extends WordRepository, JpaRepository<Word, Long> {
}
