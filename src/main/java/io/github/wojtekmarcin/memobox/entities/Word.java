package io.github.wojtekmarcin.memobox.entities;

import io.github.wojtekmarcin.memobox.dictionary.LanguageEnum;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Words")
public class Word {
    public static final String PATTERN_ONLY_LETTERS = "[a-zA-Z]+";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wordId;

    @Length(min = 2, message = "Word should be longer")
    @Pattern(regexp = PATTERN_ONLY_LETTERS, message = "Only letters")
    @NotEmpty
    private String word;

    @NotEmpty
    private LanguageEnum wordLanguage;

    @Length(min = 2, message = "Word should be longer")
    @Pattern(regexp = PATTERN_ONLY_LETTERS, message = "Only letters")
    @NotEmpty
    private String wordTranslation;
    @NotEmpty
    private LanguageEnum translationLanguage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wordSetId")
    private WordsSet wordSet;

    @Embedded
    private Audit audit = new Audit();

    public Word() {
    }

    public long getWordId() {
        return wordId;
    }

    public void setWordId(long wordId) {
        this.wordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public LanguageEnum getWordLanguage() {
        return wordLanguage;
    }

    public void setWordLanguage(LanguageEnum wordLanguage) {
        this.wordLanguage = wordLanguage;
    }

    public String getWordTranslation() {
        return wordTranslation;
    }

    public void setWordTranslation(String wordTranslation) {
        this.wordTranslation = wordTranslation;
    }

    public LanguageEnum getTranslationLanguage() {
        return translationLanguage;
    }

    public void setTranslationLanguage(LanguageEnum translationLanguage) {
        this.translationLanguage = translationLanguage;
    }

    public WordsSet getWordSet() {
        return wordSet;
    }

    public void setWordSet(WordsSet wordSet) {
        this.wordSet = wordSet;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Word{");
        sb.append("wordId=").append(wordId);
        sb.append(", word='").append(word).append('\'');
        sb.append(", wordLanguage=").append(wordLanguage);
        sb.append(", wordTranslation='").append(wordTranslation).append('\'');
        sb.append(", translationLanguage=").append(translationLanguage);
        sb.append(", wordSet=").append(wordSet);
        sb.append(", audit=").append(audit);
        sb.append('}');
        return sb.toString();
    }
}
