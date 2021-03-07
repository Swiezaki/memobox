package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.User;
import io.github.wojtekmarcin.memobox.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @GetMapping("/get/usersAll")
    ResponseEntity<List<User>> readAllUsers() {
        return (ResponseEntity<List<User>>) repository.findAllUsers();
    }

    @GetMapping("/get/userId/{id}")
    ResponseEntity<User> readUserById(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.findUserByUser_id(id));
    }

    @PutMapping("/add/users")
    ResponseEntity<?> createUser(@RequestBody User toUpdate) {
        repository.save(toUpdate);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/edit/users/{id}")
    ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User toUpdate) {
        if (!repository.existsById(id)) {
            ResponseEntity.notFound().build();
        }
        toUpdate.setUser_id(id);
        repository.save(toUpdate);
        return ResponseEntity.noContent().build();
    }
}
