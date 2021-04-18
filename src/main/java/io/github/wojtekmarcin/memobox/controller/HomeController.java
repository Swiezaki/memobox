package io.github.wojtekmarcin.memobox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    String showHome(Model model) {
        return "home";
    }

    @GetMapping("/userPage")
    String showUserPage(){
        return "/userPage";
    }

    @GetMapping("/adminPage")
    String showAdminPage(){
        return "/adminPage";
    }
}
