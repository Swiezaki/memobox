package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.*;

@Entity
@Table(name = "WORDS_SETS")
public class WordsSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wordSet_id;
    private String set_name;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user_id;

    @OneToOne(mappedBy = "WORD_ID")
    private Word word_id;

    private boolean visible_flag_id;
    private boolean edition_flag_id;


    public WordsSet() {
    }

    public String getSet_name() {
        return set_name;
    }

    public void setSet_name(String set_name) {
        this.set_name = set_name;
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
