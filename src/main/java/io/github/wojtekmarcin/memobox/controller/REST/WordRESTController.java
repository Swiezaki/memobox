package io.github.wojtekmarcin.memobox.controller.REST;

import io.github.wojtekmarcin.memobox.entities.Word;
import io.github.wojtekmarcin.memobox.repository.WordRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/word")
public class WordRESTController {
    private final WordRepository repository;

    public WordRESTController(WordRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getAll")
    ResponseEntity<List<Word>> findAll() {
        if (repository.findAll().isEmpty()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/add")
    ResponseEntity<?> createWord(@RequestBody Word toUpdate) {
        repository.save(toUpdate);
        return ResponseEntity.noContent().build();
    }
}
