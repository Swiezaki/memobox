package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.User;
import io.github.wojtekmarcin.memobox.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserViewController {
    public static final String USER_ADD = "user/add";
    public static final String USER_VIEW = "user/view";

    private final UserRepository repository;

    public UserViewController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/view")
    String showUserView(Model model) {
        model.addAttribute("users", repository.findAll());
        return USER_VIEW;
    }

    //TODO
    @GetMapping("/view/addUser")
    String addUser(Model model) {
        model.addAttribute("userToAdd", new User());
        return USER_ADD;
    }

    //TODO
    @PostMapping("/view/addUser")
    String addUserFromModel(@Valid @ModelAttribute("userToAdd") User user, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("message", "User has not been added");
            return USER_ADD;
        }
        repository.save(user);
        model.addAttribute("message", "User has been added");
        return USER_ADD;
    }
}
