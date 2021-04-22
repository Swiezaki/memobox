package io.github.wojtekmarcin.memobox.repository;

import io.github.wojtekmarcin.memobox.entities.MemoBox;
import io.github.wojtekmarcin.memobox.entities.User;

import java.util.List;

public interface MemoBoxRepository {
    List<MemoBox> findAll();

    List<MemoBox> findAllByUser(User user);

    boolean existsById(Long id);

    MemoBox findMemoBoxByMemoBoxId(Long id);

    void deleteByMemoBoxId(Long id);

    MemoBox save(MemoBox entity);

    MemoBox findMemoBoxByUser(User user);
}
