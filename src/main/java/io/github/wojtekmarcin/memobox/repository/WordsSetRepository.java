package io.github.wojtekmarcin.memobox.repository;

import io.github.wojtekmarcin.memobox.entities.MemoBox;
import io.github.wojtekmarcin.memobox.entities.WordsSet;

import java.util.List;

public interface WordsSetRepository {
    List<WordsSet> findAll();

    boolean existsById(Long id);

    MemoBox findMemoBoxByWordsSetId(Long id);

    void deleteByWordsSetId(Long id);
}
