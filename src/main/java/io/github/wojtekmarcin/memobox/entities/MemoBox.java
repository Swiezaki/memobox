package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "memoBoxes")
public class MemoBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memoBox_id;
    private long user_id;
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

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
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
