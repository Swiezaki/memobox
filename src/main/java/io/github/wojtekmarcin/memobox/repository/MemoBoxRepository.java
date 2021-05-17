package io.github.wojtekmarcin.memobox.repository;

import io.github.wojtekmarcin.memobox.entities.MemoBox;
import io.github.wojtekmarcin.memobox.entities.User;

import java.util.List;
import java.util.Optional;

public interface MemoBoxRepository {
    List<MemoBox> findAll();

    List<MemoBox> findAllByUser(User user);

    boolean existsById(Long id);

    Optional<MemoBox> findMemoBoxByMemoBoxId(Long id);

    void deleteByMemoBoxId(Long id);

    Optional<MemoBox> save(MemoBox entity);

    Optional<MemoBox> findMemoBoxByUser(User user);
}
