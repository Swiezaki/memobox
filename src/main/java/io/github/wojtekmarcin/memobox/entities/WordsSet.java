package io.github.wojtekmarcin.memobox.entities;

<<<<<<< HEAD
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "wordsSets")
=======
import javax.persistence.*;

@Table(name = "wordSets")
@Entity
>>>>>>> d66ba66... memoBox and wordsSet entity filed
public class WordsSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wordSet_id;
    private String set_name;
    private long user_id;
    private long word_id;
<<<<<<< HEAD
    private boolean visible_flag_id;
    private boolean edition_flag_id;
=======
    private Integer visible_flag_id;
    private Integer edition_flag_id;
>>>>>>> d66ba66... memoBox and wordsSet entity filed

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

<<<<<<< HEAD
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
=======
    public Integer getVisible_flag_id() {
        return visible_flag_id;
    }

    public void setVisible_flag_id(Integer visible_flag_id) {
        this.visible_flag_id = visible_flag_id;
    }

    public Integer getEdition_flag_id() {
        return edition_flag_id;
    }

    public void setEdition_flag_id(Integer edition_flag_id) {
>>>>>>> d66ba66... memoBox and wordsSet entity filed
        this.edition_flag_id = edition_flag_id;
    }
}
