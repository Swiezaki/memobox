package io.github.wojtekmarcin.memobox.entities;

import io.github.wojtekmarcin.memobox.dictionary.LanguageEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

import static io.github.wojtekmarcin.memobox.dictionary.ValidationMessage.*;

@Entity
@Data
@Table(name = "Words")
public class Word {
    public static final String PATTERN_ONLY_LETTERS = "[a-zA-Z]+";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wordId;

    @Length(min = 2, message = LENGHT)
    @Pattern(regexp = PATTERN_ONLY_LETTERS, message = LETTERS)
    @NotEmpty(message = EMPTY)
    private String word;

    @NotNull(message = EMPTY)
    private LanguageEnum wordLanguage;

    @Length(min = 2, message = LENGHT)
    @Pattern(regexp = PATTERN_ONLY_LETTERS, message = LETTERS)
    @NotEmpty(message = EMPTY)
    private String wordTranslation;

    @NotNull(message = EMPTY)
    private LanguageEnum translationLanguage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wordSetId")
    private WordsSet wordSet;

    @Embedded
    private Audit audit = new Audit();
}
