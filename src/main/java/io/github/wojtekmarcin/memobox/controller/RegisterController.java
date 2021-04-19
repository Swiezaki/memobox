package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.User;
import io.github.wojtekmarcin.memobox.repository.UserRepository;
import io.github.wojtekmarcin.memobox.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
    public static final String PAGE_REGISTER = "/register";

    private final UserService userService;
    private final UserRepository userRepository;

    public RegisterController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/createUser")
    String initAddUserForm(Model model) {
        model.addAttribute("userToAdd", new User());
        return PAGE_REGISTER;
    }

    @PostMapping("/createUser")
    String processAddUserEntityForm(@ModelAttribute("userToAdd") @Valid User user,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return PAGE_REGISTER;
        }
        if (!userRepository.findUserByLogin(user.getUsername()).isEmpty()) {
            bindingResult.rejectValue("login", "login.userToAdd", "Login already exist.");
            return PAGE_REGISTER;
        }

        userService.addUser(user);
        redirectAttributes.addFlashAttribute("message", String.format("User %s created.", user.getUserId()));
        return "redirect:/home";
    }
}
