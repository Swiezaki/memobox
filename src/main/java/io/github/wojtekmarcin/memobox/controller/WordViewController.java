package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.Word;
import io.github.wojtekmarcin.memobox.entities.WordsSet;
import io.github.wojtekmarcin.memobox.repository.WordRepository;
import io.github.wojtekmarcin.memobox.repository.WordsSetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/word")
public class WordViewController {
    public static final String PAGE_WORD_ADD = "word/add";
    public static final String PAGE_WORD_EDIT = "word/edit";
    public static final String REDIRECT_PAGE_WORD_VIEW = "redirect:/word/view";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final WordRepository wordRepository;
    private final WordsSetRepository wordsSetRepository;

    public WordViewController(WordRepository repository, WordsSetRepository wordsSetRepository) {
        this.wordRepository = repository;
        this.wordsSetRepository = wordsSetRepository;
    }

    @GetMapping("/view")
    String showWordView(Model model) {
        model.addAttribute("words", wordRepository.findAll());
        return "word/view";
    }

    /*TODO
        -Nie dodaje się WordSet
        -Zrobić ModelAtributte
      */
    @GetMapping("/addWord")
    private String initAddWordForm(Model model) {
        model.addAttribute("wordToAdd", new Word());
        List<WordsSet> wordsSetsFromRepo = wordsSetRepository.findAll();
        model.addAttribute("wordSets", wordsSetsFromRepo);
        return PAGE_WORD_ADD;
    }

    @PostMapping("/addWord")
    private String processAddingWordEntityForm(@ModelAttribute("wordToAdd") @Valid Word word,
                                               BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.put("wordSets", wordsSetRepository.findAll());
            return PAGE_WORD_ADD;
        } else {
            wordRepository.save(word);
            return REDIRECT_PAGE_WORD_VIEW;
        }
    }

    @GetMapping("/deleteWord/{id}")
    String initDeleteUserEntityForm(@PathVariable("id") long id) {
        wordRepository.deleteWordByWordId(id);
        return REDIRECT_PAGE_WORD_VIEW;
    }

    /*TODO
       -Jeżeli pierwszy warunek if zostanie spełniony to numer ID słowa zmienia się na 0 czyli numer ID wordToUpdate i za drugim kliknięciem
        submit wywala błąd
        */
    @GetMapping("/editWord/{id}")
    String initEditWordEntitieForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("wordFromSource", wordRepository.findWordByWordId(id));
        return PAGE_WORD_EDIT;
    }

    @PostMapping("/editWord/{id}")
    String processEditWordEntitieForm(@PathVariable("id") long id,
                                      @ModelAttribute("wordFromSource")
                                      @Valid Word wordToUpdate,
                                      BindingResult bindingResult,
                                      ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.put("wordFromSource", wordRepository.findWordByWordId(id));
            return PAGE_WORD_EDIT;
        } else {
            Word wordFromRepository = wordRepository.findWordByWordId(id);
            LOGGER.info("word from repo input ={}, word to update={}", wordFromRepository, wordToUpdate);

            wordFromRepository.setWord(wordToUpdate.getWord());
            wordFromRepository.setWordTranslation(wordToUpdate.getWordTranslation());
            wordFromRepository.setWordLanguageId(wordToUpdate.getWordLanguageId());
            wordFromRepository.setWordTypeId(wordToUpdate.getWordTypeId());
            wordFromRepository.setWordsSetWordId(wordToUpdate.getWordsSetWordId());
            wordRepository.save(wordFromRepository);
            LOGGER.info("users output={}", wordFromRepository);

            return REDIRECT_PAGE_WORD_VIEW;
        }
    }
}
