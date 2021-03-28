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

@Controller
@RequestMapping("/user")
public class UserViewController {
    public static final String USER_VIEW = "user/view";
    public static final String REDIRECT_HOME = "redirect:/home";

    private final UserRepository repository;

    public UserViewController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/view")
    String showUserView(Model model) {
        model.addAttribute("users", repository.findAll());
        return USER_VIEW;
    }

/*TODO
    @GetMapping("/addUser")
    String addUser(Model model) {
        model.addAttribute("userToUpdate", new User());
        return USER_VIEW;
    }*/
/*TODO
    @PostMapping("/addUser")
    String addUserFromModel(@ModelAttribute("userToUpdate") User userToUpdate, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return REDIRECT_HOME;
        }
        repository.save(userToUpdate);
        model.addAttribute("message", "User has been added");
        return USER_VIEW;
    }*/
}
