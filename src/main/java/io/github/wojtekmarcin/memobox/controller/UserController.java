package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.User;
import io.github.wojtekmarcin.memobox.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getAll")
    ResponseEntity<List<User>> readAllUsers() {
        if (repository.findAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/getUserById/{id}")
    ResponseEntity<User> readUserById(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.findUserByUserId(id));
    }

    @PostMapping("/add")
    ResponseEntity<?> createUser(@RequestBody @Valid User toUpdate) {
        repository.save(toUpdate);
        return ResponseEntity.created(URI.create("/user/add/" + toUpdate.getUserId())).build();
    }

    @PutMapping("/edit/{id}")
    ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User toUpdate) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Optional<User> byId = repository.findById(id);
        if (byId.isPresent()) {
            if (!byId.get().equals(toUpdate)) {
                byId.get().updateFrom(toUpdate);
                repository.save(byId.get());
                return ResponseEntity.ok().build();
            } else {
                LOGGER.info("weszło do elsa");
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteUserByUserId(id);
        return ResponseEntity.noContent().build();
    }
}
