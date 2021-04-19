package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.User;
import io.github.wojtekmarcin.memobox.repository.UserRepository;
import io.github.wojtekmarcin.memobox.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegisterController {
    public static final String PAGE_REGISTER = "register";

    private final UserService userService;
    private final UserRepository userRepository;

    public RegisterController(@Lazy UserService userService,@Lazy UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping("/register")
    String initCreateUserForm(Model model) {
        model.addAttribute("userToAdd", new User());
        return PAGE_REGISTER;
    }

    @PostMapping("/register")
    String processCreateUserForm(@ModelAttribute("userToAdd") @Valid User user,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return PAGE_REGISTER;
        }
        if (!userRepository.findAllUsersByUsername(user.getUsername()).isEmpty()) {
            bindingResult.rejectValue("login", "username.userToAdd", "Login already exist.");
            return PAGE_REGISTER;
        }

        userService.addUser(user);
        redirectAttributes.addFlashAttribute("message", String.format("User %s created.", user.getUserId()));
        return "redirect:/home";

    }
}
