package io.github.wojtekmarcin.memobox.repository;

import io.github.wojtekmarcin.memobox.entities.WordsSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlWordsSetRepository extends WordsSetRepository, JpaRepository<WordsSet, Long> {
}
