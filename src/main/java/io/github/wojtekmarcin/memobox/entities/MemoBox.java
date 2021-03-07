package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Memoboxes")
public class MemoBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memoBox_id;
    private Integer word_slot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToMany
    @JoinTable(
            name = "memoBoxes_wordsSets",
            joinColumns = @JoinColumn(name = "memoBox_set_id"),
            inverseJoinColumns = @JoinColumn(name = "wordSet_id"))
    private List<WordsSet> wordSet_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "word_id")
    private Word word_id;

    public MemoBox() {
    }

    public long getMemoBox_id() {
        return memoBox_id;
    }

    public void setMemoBox_id(long memoBox_id) {
        this.memoBox_id = memoBox_id;
    }

    public Integer getWord_slot() {
        return word_slot;
    }

    public void setWord_slot(Integer word_slot) {
        this.word_slot = word_slot;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user) {
        this.user_id = user;
    }

    public List<WordsSet> getWordSet_id() {
        return wordSet_id;
    }

    public void setWordSet_id(List<WordsSet> wordSet_id) {
        this.wordSet_id = wordSet_id;
    }

    public Word getWord_id() {
        return word_id;
    }

    public void setWord_id(Word word_id) {
        this.word_id = word_id;
    }
}
