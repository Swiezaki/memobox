package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.repository.WordRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wordView")
public class WordViewController {
    private final WordRepository repository;

    public WordViewController(WordRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    String showWordView(Model model) {
        model.addAttribute("words", repository.findAll());
        return "wordView";
    }
}
