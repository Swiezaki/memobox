package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "wordsSets")
public class WordsSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wordSet_id;
    private String set_name;
    private long user_id;
    private long word_id;
    private boolean visible_flag_id;
    private boolean edition_flag_id;

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

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getWord_id() {
        return word_id;
    }

    public void setWord_id(long word_id) {
        this.word_id = word_id;
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
}
