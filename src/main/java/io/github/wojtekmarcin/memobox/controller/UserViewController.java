package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userView")
public class UserViewController {
    @GetMapping
    String showUserView(Model model){
        User userToEdit = new User();
        userToEdit.setLogin("test");
        model.addAttribute("users", userToEdit);
        return "userView";
    }
}
