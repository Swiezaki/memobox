package io.github.wojtekmarcin.memobox.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wordId;
    @Min(value = 1, message = "Word should be longer")
    @Pattern(regexp = "[a-zA-Z]",message = "Only letters")
    private String word;
    private Integer wordTypeId;
    private String wordTranslation;
    private Integer wordTranslationId;
    private Integer wordLanguageId;

    @OneToOne(mappedBy = "wordId")
    private WordsSet wordsSetWordId;

    @Embedded
    private Audit audit = new Audit();

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

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }
}
