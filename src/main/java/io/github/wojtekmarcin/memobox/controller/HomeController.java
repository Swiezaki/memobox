package io.github.wojtekmarcin.memobox.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    private String showHome(Model model) {
        return "home";
    }

    @GetMapping("/userPage")
    private String showUserPage(){
        return "/userPage";
    }

    @GetMapping("/adminPage")
    private String showAdminPage(){
        return "/adminPage";
    }

    @GetMapping("/logout")
    private void logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
