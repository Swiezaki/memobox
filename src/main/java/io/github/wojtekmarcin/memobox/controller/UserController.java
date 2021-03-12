package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.User;
import io.github.wojtekmarcin.memobox.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getAll")
    ResponseEntity<List<User>> readAllUsers() {
        if(repository.findAll().isEmpty()){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/getUserById/{id}")
    ResponseEntity<User> readUserById(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.findUserByUserId(id));
    }

    @PostMapping("/add")
    ResponseEntity<?> createUser(@RequestBody User toUpdate) {
        repository.save(toUpdate);
    return ResponseEntity.created(URI.create("/user/add/" + toUpdate.getUserId())).build();
    }

    @PutMapping("/edit/{id}")
    ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User toUpdate) {
        if (!repository.existsById(id)) {
            ResponseEntity.notFound().build();
        }
        toUpdate.setUserId(id);
        repository.save(toUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteUser (@PathVariable Long id){
        if(!repository.existsById(id)){
            ResponseEntity.notFound().build();
        }
        repository.deleteUserByUserId(id);
        return ResponseEntity.noContent().build();
    }
}
