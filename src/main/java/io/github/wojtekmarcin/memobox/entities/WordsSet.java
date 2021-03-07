package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Words_Set")
public class WordsSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wordSet_id;
    private String set_name;
    private boolean visible_flag_id;
    private boolean edition_flag_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wordsSet_word_id")
    private Word word_id;

    @ManyToMany(mappedBy = "wordSet_id")
    private List<MemoBox> memoBox_set_id;

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

    public List<MemoBox> getMemoBox_set_id() {
        return memoBox_set_id;
    }

    public void setMemoBox_set_id(List<MemoBox> memoBox_set_id) {
        this.memoBox_set_id = memoBox_set_id;
    }
}
