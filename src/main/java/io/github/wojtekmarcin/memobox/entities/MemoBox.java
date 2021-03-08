package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Memoboxes")
public class MemoBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memoBoxId;
    private Integer wordSlot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User userId;

    @ManyToMany
    @JoinTable(
            name = "memoBoxesWordsSets",
            joinColumns = @JoinColumn(name = "memoBoxSetId"),
            inverseJoinColumns = @JoinColumn(name = "wordSetId"))
    private List<WordsSet> wordSetId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wordId")
    private Word wordId;

    public MemoBox() {
    }

    public long getMemoBoxId() {
        return memoBoxId;
    }

    public void setMemoBoxId(long memoBoxId) {
        this.memoBoxId = memoBoxId;
    }

    public Integer getWordSlot() {
        return wordSlot;
    }

    public void setWordSlot(Integer wordSlot) {
        this.wordSlot = wordSlot;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public List<WordsSet> getWordSetId() {
        return wordSetId;
    }

    public void setWordSetId(List<WordsSet> wordSetId) {
        this.wordSetId = wordSetId;
    }

    public Word getWordId() {
        return wordId;
    }

    public void setWordId(Word wordId) {
        this.wordId = wordId;
    }
}
