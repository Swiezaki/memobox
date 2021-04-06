package io.github.wojtekmarcin.memobox.repository;

import io.github.wojtekmarcin.memobox.entities.Word;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface WordRepository {
    List<Word> findAll();

    boolean existsById(Long id);

    List<Word> findByWordId(Long id);

    Word save(Word entity);

    void deleteWordByWordId(Long id);
}
