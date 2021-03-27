package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userView")
public class UserViewController {
    private final UserRepository repository;

    public UserViewController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String showUserView(Model model) {
        model.addAttribute("users", repository.findAll());
        return "userView";
    }
}
