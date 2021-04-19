package io.github.wojtekmarcin.memobox.controller.REST;

import io.github.wojtekmarcin.memobox.entities.WordsSet;
import io.github.wojtekmarcin.memobox.repository.WordsSetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wordSet")
public class WordsSetRESTController {
    private final WordsSetRepository wordsSetRepository;

    public WordsSetRESTController(WordsSetRepository wordsSetRepository) {
        this.wordsSetRepository = wordsSetRepository;
    }

    @PostMapping("/add")
    ResponseEntity<?> createWordsSet(@RequestBody WordsSet toUpdate) {
        wordsSetRepository.save(toUpdate);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    ResponseEntity<List<WordsSet>> findAll() {
        if (wordsSetRepository.findAll().isEmpty()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wordsSetRepository.findAll());
    }
}
