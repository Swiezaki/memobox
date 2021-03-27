package io.github.wojtekmarcin.memobox.repository;

import io.github.wojtekmarcin.memobox.entities.Word;

import java.util.List;

public interface WordRepository {
    List<Word> findAll();

    boolean existsById(Long id);

    List<Word> findByWordId(Long id);

    Word save(Word entity);
}
