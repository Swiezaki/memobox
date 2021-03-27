package io.github.wojtekmarcin.memobox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/", "/home")
public class HomeController {
    @GetMapping
    String showHome(){
        return "home";
    }
}
