package io.github.wojtekmarcin.memobox.repository;

import io.github.wojtekmarcin.memobox.entities.MemoBox;
import io.github.wojtekmarcin.memobox.entities.WordsSet;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface WordsSetRepository {
    List<WordsSet> findAll();

    boolean existsById(Long id);

    MemoBox findMemoBoxByWordSetId(Long id);

    void deleteByWordSetId(Long id);

    WordsSet save(WordsSet entity);
}
