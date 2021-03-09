package io.github.wojtekmarcin.memobox.repository;

import io.github.wojtekmarcin.memobox.entities.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlWordReposiotry extends WordRepository, JpaRepository<Word, Long> {
}
