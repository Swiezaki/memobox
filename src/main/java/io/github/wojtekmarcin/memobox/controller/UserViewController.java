package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.User;
import io.github.wojtekmarcin.memobox.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/userView")
public class UserViewController {
    private final UserRepository repository;

    public UserViewController(UserRepository repository) {
        this.repository = repository;
    }

//    @GetMapping
//    String showUserView(Model model) {
//        User userToEdit = new User();
//        userToEdit.setLogin("test");
//        model.addAttribute("user", userToEdit);
//        return "userView";
//    }

    @GetMapping
    List<User> readAllUsers() {
        return repository.findAll();
    }
}
