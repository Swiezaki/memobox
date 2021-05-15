package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.controller.REST.UserRESTController;
import io.github.wojtekmarcin.memobox.entities.User;
import io.github.wojtekmarcin.memobox.entities.WordsSet;
import io.github.wojtekmarcin.memobox.repository.MemoBoxRepository;
import io.github.wojtekmarcin.memobox.repository.UserRepository;
import io.github.wojtekmarcin.memobox.repository.WordsSetRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/wordset")
public class WordSetController {
    public static final String PAGE_WORDSET_ADD = "wordset/add";
    public static final String PAGE_WORDSET_EDIT = "wordset/edit";
    public static final String REDIRECT_PAGE_WORDSET_VIEW = "redirect:/wordset/view";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRESTController.class);

    private final WordsSetRepository wordSetRepository;
    private final UserRepository userRepository;
    private final MemoBoxRepository memoBoxRepository;

    @ModelAttribute("authUser")
    private User getAuthUser(Principal principal) {
        return userRepository.findUserByUsername(principal.getName());
    }

    @GetMapping("/view")
    private String showWordView(@ModelAttribute("authUser") User user,
                                Model model) {
        model.addAttribute("wordsets", wordSetRepository.findAllByUser(user));
        return "wordset/view";
    }

    @GetMapping("/addWordSet")
    private String initAddWordForm(Model model) {
        model.addAttribute("wordsetToAdd", new WordsSet());
        return PAGE_WORDSET_ADD;
    }

    @PostMapping("/addWordSet")
    private String processAddingWordEntityForm(@ModelAttribute("wordsetToAdd")
                                               @Valid WordsSet wordsSet,
                                               @ModelAttribute("authUser") User user,
                                               BindingResult bindingResult,
                                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return PAGE_WORDSET_ADD;
        } else {
            user.addWordSet(wordsSet);
            userRepository.save(user);
            redirectAttributes.addFlashAttribute("message", String.format("Wordset %s created.", wordsSet.getWordSetName().toLowerCase()));
            return REDIRECT_PAGE_WORDSET_VIEW;
        }
    }

    @GetMapping("/deleteWordSet/{id}")
    private String initDeleteUserEntityForm(@PathVariable("id") long id) {
        wordSetRepository.deleteByWordSetId(id);
        return REDIRECT_PAGE_WORDSET_VIEW;
    }

    @GetMapping("/editWordSet/{id}")
    private String initEditWordEntitieForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("wordSetFromSource", wordSetRepository.findMemoBoxByWordSetId(id));
        return PAGE_WORDSET_EDIT;
    }

    @PostMapping("/editWordSet/{id}")
    private String processEditWordEntitieForm(@PathVariable("id") long id,
                                              @ModelAttribute("wordSetFromSource")
                                              @Valid WordsSet wordsSet,
                                              BindingResult bindingResult,
                                              Model model) {
        if (bindingResult.hasErrors()) {
            wordsSet.setWordSetId(id);
            model.addAttribute("wordSetFromSource", wordsSet);
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

