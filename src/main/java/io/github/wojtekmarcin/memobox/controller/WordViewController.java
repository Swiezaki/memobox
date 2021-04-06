package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.Word;
import io.github.wojtekmarcin.memobox.repository.WordRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/word")
public class WordViewController {
    public static final String PAGE_WORD_ADD = "word/add";
    public static final String PAGE_WORD_VIEW = "word/view";
    public static final String REDIRECT_PAGE_WORD_VIEW = "redirect:/word/view";

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
        return PAGE_WORD_ADD;
    }

    @PostMapping("/addWord")
    private String processAddingWordEntity(@ModelAttribute("wordToAdd") @Valid Word word, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return PAGE_WORD_ADD;
        } else {
            repository.save(word);
            return REDIRECT_PAGE_WORD_VIEW;
        }
    }

    @GetMapping("/deleteWord/{id}")
    String initDeleteUserEntity(@PathVariable("id") long id) {
        repository.deleteWordByWordId(id);
        return REDIRECT_PAGE_WORD_VIEW;
    }
}
