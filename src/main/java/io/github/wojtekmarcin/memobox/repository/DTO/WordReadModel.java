package io.github.wojtekmarcin.memobox.repository.DTO;

import io.github.wojtekmarcin.memobox.entities.Word;

public class WordReadModel {
    private String word;
    private String wordTranslation;

    public WordReadModel(Word source){
        this.word = source.getWord();
        this.wordTranslation = source.getWordTranslation();
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

    public void setWordTranslation(String wordTranslation) {
        this.wordTranslation = wordTranslation;
    }
}
