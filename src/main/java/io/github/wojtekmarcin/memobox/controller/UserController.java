package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.User;
import io.github.wojtekmarcin.memobox.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/user/getAll")
    ResponseEntity<List<User>> readAllUsers() {
        if(repository.findAll().isEmpty()){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/user/getUserById/{id}")
    ResponseEntity<User> readUserById(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.findUserByUserId(id));
    }

    @PostMapping("/user/add")
    ResponseEntity<?> createUser(@RequestBody User toUpdate) {
        repository.save(toUpdate);
//        return ResponseEntity.noContent().build();
    return ResponseEntity.created(URI.create("/user/add")).build();
    }
//
//    @PutMapping("/edit/users/{id}")
//    ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User toUpdate) {
//        if (!repository.existsById(id)) {
//            ResponseEntity.notFound().build();
//        }
//        toUpdate.setUser_id(id);
//        repository.save(toUpdate);
//        return ResponseEntity.noContent().build();
//    }
}
