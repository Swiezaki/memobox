package io.github.wojtekmarcin.memobox.repository;

import io.github.wojtekmarcin.memobox.entities.Word;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface WordRepository {
    List<Word> findAll();

    boolean existsById(Long id);

    Optional<Word> findWordByWordId(Long id);

    Word save(Word entity);

    void deleteWordByWordId(Long id);
}
