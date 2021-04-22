package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.MemoBox;
import io.github.wojtekmarcin.memobox.entities.User;
import io.github.wojtekmarcin.memobox.repository.MemoBoxRepository;
import io.github.wojtekmarcin.memobox.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Controller
@RequestMapping("/memobox")
public class MemoboxController {
    private final MemoBoxRepository memoBoxRepository;
    private final UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoboxController.class);

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
    private String initAddWordForm(Model model) {
        model.addAttribute("memoboxToAdd", new MemoBox());
        return "memobox/add";
    }

    @PostMapping("/addMemobox")
    private String processAddingWordEntityForm(@ModelAttribute("memoboxToAdd")
                                               @Valid MemoBox memoBox,
                                               @ModelAttribute("authUser") User user,
                                               BindingResult bindingResult,
                                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "memobox/add";
        } else {

            LOGGER.info("Before add: user = {}, memoBox ={}", user.getUserId(), memoBox.toString());

            user.addMemobox(memoBox);

            LOGGER.info(" memoBox in user ={}", user.getUserId());

            userRepository.save(user);

            redirectAttributes.addFlashAttribute("message", String.format("Wordset %s created.", memoBox.getMemoBoxId()));
            return "redirect:/userPage";
        }
    }
}
