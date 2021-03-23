package io.github.wojtekmarcin.memobox.model;

import io.github.wojtekmarcin.memobox.entities.Audit;
import io.github.wojtekmarcin.memobox.entities.MemoBox;
import io.github.wojtekmarcin.memobox.entities.WordsSet;

import java.util.List;

public class UserReadModel {
    private long userId;

    private String login;

    private String password;

    private List<MemoBox> memoBoxId;

    private List<WordsSet> wordsSetId;

    private Audit audit;

    public long getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<MemoBox> getMemoBoxId() {
        return memoBoxId;
    }

    public List<WordsSet> getWordsSetId() {
        return wordsSetId;
    }

    public Audit getAudit() {
        return audit;
    }
}
