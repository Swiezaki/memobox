package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "WORDS_SETS")
public class WordsSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wordSet_id;
    private String set_name;
    private boolean visible_flag_id;
    private boolean edition_flag_id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user_id;

    @OneToOne(mappedBy = "WORD_ID")
    private Word word_id;

    @ManyToMany(mappedBy = "MEMOBOX_ID")
    private List<MemoBox> memoBox_id;

    public WordsSet() {
    }

    public long getWordSet_id() {
        return wordSet_id;
    }

    public void setWordSet_id(long wordSet_id) {
        this.wordSet_id = wordSet_id;
    }

    public String getSet_name() {
        return set_name;
    }

    public void setSet_name(String set_name) {
        this.set_name = set_name;
    }

    public boolean isVisible_flag_id() {
        return visible_flag_id;
    }

    public void setVisible_flag_id(boolean visible_flag_id) {
        this.visible_flag_id = visible_flag_id;
    }

    public boolean isEdition_flag_id() {
        return edition_flag_id;
    }

    public void setEdition_flag_id(boolean edition_flag_id) {
        this.edition_flag_id = edition_flag_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Word getWord_id() {
        return word_id;
    }

    public void setWord_id(Word word_id) {
        this.word_id = word_id;
    }

    public List<MemoBox> getMemoBox_id() {
        return memoBox_id;
    }

    public void setMemoBox_id(List<MemoBox> memoBox_id) {
        this.memoBox_id = memoBox_id;
    }
}
