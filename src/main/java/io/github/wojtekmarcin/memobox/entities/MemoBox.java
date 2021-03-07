package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "memoBoxes")
public class MemoBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memoBox_id;
    private Integer word_id;
    private Integer word_slot;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToMany
    @JoinColumn(name = "WORDSET_ID")
    private List<WordsSet> wordSet_id;

    public MemoBox() {
    }

    public long getMemoBox_id() {
        return memoBox_id;
    }

    public void setMemoBox_id(long memoBox_id) {
        this.memoBox_id = memoBox_id;
    }

    public Integer getWord_id() {
        return word_id;
    }

    public void setWord_id(Integer word_id) {
        this.word_id = word_id;
    }

    public Integer getWord_slot() {
        return word_slot;
    }

    public void setWord_slot(Integer word_slot) {
        this.word_slot = word_slot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<WordsSet> getWordSet_id() {
        return wordSet_id;
    }

    public void setWordSet_id(List<WordsSet> wordSet_id) {
        this.wordSet_id = wordSet_id;
    }
}
