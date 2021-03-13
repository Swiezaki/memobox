package io.github.wojtekmarcin.memobox.repository;

import io.github.wojtekmarcin.memobox.entities.MemoBox;

import java.util.List;

public interface MemoBoxRepository {
    List<MemoBox> findAll();

    boolean existsById(Long id);

    MemoBox findMemoBoxByMemoBoxId(Long id);

    void deleteByMemoBoxId(Long id);

    MemoBox save(MemoBox entity);
}
