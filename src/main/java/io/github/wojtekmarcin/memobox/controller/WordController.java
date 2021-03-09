package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.Word;
import io.github.wojtekmarcin.memobox.repository.WordRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WordController {
    private final WordRepository repository;

    public WordController(WordRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/get/wordsAll")
    ResponseEntity<List<Word>> findAll() {
        if (repository.findAll().isEmpty()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/put/word")
    ResponseEntity<?> createWord(@RequestBody Word toUpdate) {
        repository.save(toUpdate);
        return ResponseEntity.noContent().build();
    }
}
