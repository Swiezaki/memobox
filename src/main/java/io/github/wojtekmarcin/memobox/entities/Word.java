package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.*;

@Entity
@Table(name = "Words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wordId;
    private String word;
    private String wordTranslation;
    private Integer wordTranslationId;
    private Integer wordLanguageId;
    private Integer wordTypeId;

    @OneToOne(mappedBy = "wordId")
    private WordsSet wordsSetWordId;

    public Word() {
    }

    public long getWordId() {
        return wordId;
    }

    public void setWordId(long word_id) {
        this.wordId = word_id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordTranslation() {
        return wordTranslation;
    }

    public void setWordTranslation(String word_translation) {
        this.wordTranslation = word_translation;
    }


    public Integer getWordTranslationId() {
        return wordTranslationId;
    }

    public void setWordTranslationId(Integer word_translation_id) {
        this.wordTranslationId = word_translation_id;
    }

    public Integer getWordLanguageId() {
        return wordLanguageId;
    }

    public void setWordLanguageId(Integer word_language_id) {
        this.wordLanguageId = word_language_id;
    }

    public Integer getWordTypeId() {
        return wordTypeId;
    }

    public void setWordTypeId(Integer word_type_id) {
        this.wordTypeId = word_type_id;
    }

    public WordsSet getWordsSetWordId() {
        return wordsSetWordId;
    }

    public void setWordsSetWordId(WordsSet wordsSetWordId) {
        this.wordsSetWordId = wordsSetWordId;
    }
}
