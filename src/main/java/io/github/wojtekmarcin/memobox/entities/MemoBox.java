package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.*;

@Entity
@Table(name = "memoBoxes")
public class MemoBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memoBox_id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private long wordSet_id;
    private Integer word_id;
    private Integer word_slot;

    public MemoBox() {
    }

    public long getMemoBox_id() {
        return memoBox_id;
    }

    public void setMemoBox_id(long memoBox_id) {
        this.memoBox_id = memoBox_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getWordSet_id() {
        return wordSet_id;
    }

    public void setWordSet_id(long wordSet_id) {
        this.wordSet_id = wordSet_id;
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
}
