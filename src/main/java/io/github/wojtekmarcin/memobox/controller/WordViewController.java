package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.repository.SqlWordRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wordView")
public class WordViewController {
    private final SqlWordRepository repository;

    public WordViewController(SqlWordRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String showWordView(Model model) {
        model.addAttribute("words", repository.findAll());
        return "wordView";
    }
}
