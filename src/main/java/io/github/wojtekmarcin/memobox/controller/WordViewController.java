package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.Word;
import io.github.wojtekmarcin.memobox.repository.WordRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/word")
public class WordViewController {
    public static final String WORD_ADD_PAGE = "word/add";
    public static final String WORD_VIEW_PAGE = "word/view";
    public static final String REDIRECT_WORD_VIEW_PAGE = "redirect:/word/view";

    private final WordRepository repository;

    public WordViewController(WordRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/view")
    String showWordView(Model model) {
        model.addAttribute("words", repository.findAll());
        return "word/view";
    }

    @GetMapping("/addWord")
    private String initAddWordForm(Model model) {
        model.addAttribute("wordToAdd", new Word());
        return WORD_ADD_PAGE;
    }

    @PostMapping("/addWord")
    private String processAddingWordEntity(@ModelAttribute("wordToAdd") @Valid Word word, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return WORD_ADD_PAGE;
        } else {
            repository.save(word);
            return REDIRECT_WORD_VIEW_PAGE;
        }
    }
}
