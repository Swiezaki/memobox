package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.MemoBox;
import io.github.wojtekmarcin.memobox.entities.User;
import io.github.wojtekmarcin.memobox.entities.WordsSet;
import io.github.wojtekmarcin.memobox.repository.MemoBoxRepository;
import io.github.wojtekmarcin.memobox.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

import static io.github.wojtekmarcin.memobox.controller.WordSetController.PAGE_WORDSET_ADD;
import static io.github.wojtekmarcin.memobox.controller.WordSetController.REDIRECT_PAGE_WORDSET_VIEW;

@Controller
@RequestMapping("/memobox")
public class MemoboxController {
    private final MemoBoxRepository memoBoxRepository;
    private final UserRepository userRepository;

    public MemoboxController(MemoBoxRepository repository, UserRepository userRepository) {
        this.memoBoxRepository = repository;
        this.userRepository = userRepository;
    }

    @ModelAttribute("authUser")
    private User getAuthUser(Principal principal) {
        return userRepository.findUserByUsername(principal.getName());
    }

    @GetMapping("/view")
    String showMemoboxView(Model model) {
        model.addAttribute("memoboxes", memoBoxRepository.findAll());
        return "memobox/view";
    }

    @GetMapping("/addMemobox")
    private String initAddWordForm(Model model,
                                   @ModelAttribute("authUser") User user) {
        MemoBox memoBox = new MemoBox();
        user.addMemobox(memoBox);
        model.addAttribute("memoboxToAdd", memoBox);
        return PAGE_WORDSET_ADD;
    }

    @PostMapping("/addMemobox")
    private String processAddingWordEntityForm(@ModelAttribute("memoboxToAdd")
                                               @Valid MemoBox memoBox,
                                               BindingResult bindingResult,
                                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return PAGE_WORDSET_ADD;
        } else {
            memoBoxRepository.save(memoBox);
            redirectAttributes.addFlashAttribute("message", String.format("Wordset %s created.", memoBox.getMemoBoxId()));
            return REDIRECT_PAGE_WORDSET_VIEW;
        }
    }
}
