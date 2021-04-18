package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.Word;
import io.github.wojtekmarcin.memobox.entities.WordsSet;
import io.github.wojtekmarcin.memobox.repository.WordsSetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

public class WordSetViewController {
    public static final String PAGE_WORDSET_ADD = "wordset/add";
    public static final String PAGE_WORDSET_EDIT = "wordset/edit";
    public static final String REDIRECT_PAGE_WORDSET_VIEW = "redirect:/wordset/view";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private WordsSetRepository wordSetRepository;

    public WordSetViewController(WordsSetRepository wordsSetRepository) {
        this.wordSetRepository = wordsSetRepository;
    }

    @GetMapping("/view")
    String showWordView(Model model) {
        model.addAttribute("wordsets", wordSetRepository.findAll());
        return "word/view";
    }

    @GetMapping("/addWordSet")
    private String initAddWordForm(Model model) {
        model.addAttribute("wordsetToAdd", new WordsSet());
        return PAGE_WORDSET_ADD;
    }

    @PostMapping("/addWordSet")
    private String processAddingWordEntityForm(@ModelAttribute("wordsetToAdd") @Valid WordsSet wordsSet,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return PAGE_WORDSET_ADD;
        } else {
            wordSetRepository.save(wordsSet);
            return REDIRECT_PAGE_WORDSET_VIEW;
        }
    }

    @GetMapping("/deleteWordSet/{id}")
    String initDeleteUserEntityForm(@PathVariable("id") long id) {
        wordSetRepository.deleteByWordSetId(id);
        return REDIRECT_PAGE_WORDSET_VIEW;
    }

    @GetMapping("/editWordSet/{id}")
    String initEditWordEntitieForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("wordSetFromSource", wordSetRepository.findMemoBoxByWordSetId(id));
        return PAGE_WORDSET_EDIT;
    }

    @PostMapping("/editWord/{id}")
    String processEditWordEntitieForm(@PathVariable("id") long id,
                                      @ModelAttribute("wordSetFromSource")
                                      @Valid WordsSet wordsSet,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return PAGE_WORDSET_EDIT;
        } else {
            LOGGER.info("word input ={}", wordsSet);

            wordsSet.setWordSetId(id);
            wordSetRepository.save(wordsSet);

            LOGGER.info("users output={}", wordsSet);
            return REDIRECT_PAGE_WORDSET_VIEW;
        }
    }
}

