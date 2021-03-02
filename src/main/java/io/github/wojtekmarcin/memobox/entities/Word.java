package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.*;

@Entity
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long word_id;
    private String word;
    private String word_translation;
    private Integer word_translation_id;
    private Integer word_language_id;
    private Integer word_type_id;

    public Word() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord_translation() {
        return word_translation;
    }

    public void setWord_translation(String word_translation) {
        this.word_translation = word_translation;
    }

    public Integer getWord_translation_id() {
        return word_translation_id;
    }

    public void setWord_translation_id(Integer word_translation_id) {
        this.word_translation_id = word_translation_id;
    }

    public Integer getWord_language_id() {
        return word_language_id;
    }

    public void setWord_language_id(Integer word_language_id) {
        this.word_language_id = word_language_id;
    }

    public Integer getWord_type_id() {
        return word_type_id;
    }

    public void setWord_type_id(Integer word_type_id) {
        this.word_type_id = word_type_id;
    }
}
