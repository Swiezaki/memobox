package io.github.wojtekmarcin.memobox.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Table(name = "Words")
public class Word {
    public static final String PATTERN_ONLY_LETTERS = "[a-zA-Z]+";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wordId;

    @Length(min = 2, message = "Word should be longer")
    @Pattern(regexp = PATTERN_ONLY_LETTERS, message = "Only letters")
    private String word;

    @Length(min = 2, message = "Word should be longer")
    @Pattern(regexp = PATTERN_ONLY_LETTERS, message = "Only letters")
    private String wordTranslation;

    private Integer wordTypeId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return wordId == word1.wordId && Objects.equals(word, word1.word) && Objects.equals(wordTranslation, word1.wordTranslation) && Objects.equals(wordTypeId, word1.wordTypeId) && Objects.equals(wordTranslationId, word1.wordTranslationId) && Objects.equals(wordLanguageId, word1.wordLanguageId) && Objects.equals(wordsSetWordId, word1.wordsSetWordId) && Objects.equals(audit, word1.audit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordId, word, wordTranslation, wordTypeId, wordTranslationId, wordLanguageId, wordsSetWordId, audit);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Word{");
        sb.append("wordId=").append(wordId);
        sb.append(", word='").append(word).append('\'');
        sb.append(", wordTranslation='").append(wordTranslation).append('\'');
        sb.append(", wordTypeId=").append(wordTypeId);
        sb.append(", wordTranslationId=").append(wordTranslationId);
        sb.append(", wordLanguageId=").append(wordLanguageId);
        sb.append(", wordsSetWordId=").append(wordsSetWordId);
        sb.append(", audit=").append(audit);
        sb.append('}');
        return sb.toString();
    }
}
