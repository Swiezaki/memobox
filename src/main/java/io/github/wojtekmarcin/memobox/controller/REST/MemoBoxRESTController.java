package io.github.wojtekmarcin.memobox.controller.REST;

import io.github.wojtekmarcin.memobox.entities.MemoBox;
import io.github.wojtekmarcin.memobox.repository.MemoBoxRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/memobox")
public class MemoBoxRESTController {
    MemoBoxRepository repository;

    public MemoBoxRESTController(MemoBoxRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getAll")
    ResponseEntity<List<MemoBox>> readAllMemobox() {
        if (repository.findAll().isEmpty()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/getMemoboxById/{id}")
    ResponseEntity<Optional<MemoBox>> readMemoboxById(@RequestBody Long id) {
        if (!repository.existsById(id)) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.findMemoBoxByMemoBoxId(id));
    }

    @PostMapping("/add")
    ResponseEntity<?> createMemobox(@RequestBody MemoBox toUpdate) {
        repository.save(toUpdate);
        return ResponseEntity.created(URI.create("/memobox/add/" + toUpdate.getMemoBoxId())).build();
    }

    @PutMapping("/edit/{id}")
    ResponseEntity<MemoBox> editMemoboxById(@PathVariable Long id, @RequestBody MemoBox toUpdate) {
        if (!repository.existsById(id)) {
            ResponseEntity.notFound().build();
        }
        toUpdate.setMemoBoxId(id);
        repository.save(toUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteMemobox(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            ResponseEntity.notFound().build();
        }
        repository.deleteByMemoBoxId(id);
        return ResponseEntity.noContent().build();
    }
}
