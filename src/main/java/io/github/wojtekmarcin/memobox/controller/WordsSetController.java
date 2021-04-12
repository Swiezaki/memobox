package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.WordsSet;
import io.github.wojtekmarcin.memobox.repository.WordsSetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wordSet")
public class WordsSetController {
    private final WordsSetRepository wordsSetRepository;

    public WordsSetController(WordsSetRepository wordsSetRepository) {
        this.wordsSetRepository = wordsSetRepository;
    }

    @PostMapping("/add")
    ResponseEntity<?> createWordsSet(@RequestBody WordsSet toUpdate) {
        wordsSetRepository.save(toUpdate);
        return ResponseEntity.noContent().build();
    }
}
