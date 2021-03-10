package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.MemoBox;
import io.github.wojtekmarcin.memobox.repository.MemoboxRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class MemoBoxController {
    private final MemoboxRepository repository;

    public MemoBoxController(MemoboxRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/memobox/getAll")
    ResponseEntity<List<MemoBox>> readAllMemobox() {
        if (repository.findAll().isEmpty()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/memobox/getMemoboxById/{id}")
    ResponseEntity<MemoBox> readMemoboxById(@RequestBody Long id) {
        if (!repository.existsById(id)) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.findMemoBoxByMemoBoxId(id));
    }

    @PostMapping("/memobox/add")
    ResponseEntity<?> createMemobox(@RequestBody MemoBox toUpdate) {
        repository.save(toUpdate);
        return ResponseEntity.created(URI.create("/memobox/add/" + toUpdate.getUserId())).build();
    }

    @PutMapping("/memobox/edit/{id}")
    ResponseEntity<MemoBox> editMemoboxById(@PathVariable Long id, @RequestBody MemoBox toUpdate) {
        if (!repository.existsById(id)) {
            ResponseEntity.notFound().build();
        }
        toUpdate.setMemoBoxId(id);
        repository.save(toUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/membox/delete/{id}")
    ResponseEntity<?> deleteMemobox(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            ResponseEntity.notFound().build();
        }
        repository.deleteByMemoBoxId(id);
        return ResponseEntity.noContent().build();
    }

}
