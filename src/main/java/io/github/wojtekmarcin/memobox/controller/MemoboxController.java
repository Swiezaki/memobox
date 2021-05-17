package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.MemoBox;
import io.github.wojtekmarcin.memobox.entities.User;
import io.github.wojtekmarcin.memobox.repository.MemoBoxRepository;
import io.github.wojtekmarcin.memobox.repository.UserRepository;
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
@RequestMapping("/memobox")
public class MemoboxController {
    public static final String REDIRECT_PAGE_MEMOBOX_VIEW = "redirect:/memobox/view";
    public static final String PAGE_MEMOBOX_ADD = "memobox/add";
    public static final String PAGE_MEMOBOX_EDIT = "memobox/edit";

    private final MemoBoxRepository memoBoxRepository;
    private final UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoboxController.class);

    @ModelAttribute("authUser")
    private User getAuthUser(Principal principal) {
        return userRepository.findUserByUsername(principal.getName());
    }

    @GetMapping("/view")
    String showMemoboxView(@ModelAttribute("authUser") User user,
                           Model model) {
        model.addAttribute("memoboxes", memoBoxRepository.findAllByUser(user));
        return "memobox/view";
    }

    @GetMapping("/addMemobox")
    private String initAddWordForm(Model model) {
        model.addAttribute("memoboxToAdd", new MemoBox());
        return PAGE_MEMOBOX_ADD;
    }

    @PostMapping("/addMemobox")
    private String processAddingWordEntityForm(@ModelAttribute("memoboxToAdd")
                                               @Valid MemoBox memoBox,
                                               @ModelAttribute("authUser") User user,
                                               BindingResult bindingResult,
                                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return PAGE_MEMOBOX_ADD;
        } else {
            user.addMemobox(memoBox);
            userRepository.save(user);
            redirectAttributes.addFlashAttribute("message", String.format("Memobox %s created.", memoBox.getMemoboxName()));
            return REDIRECT_PAGE_MEMOBOX_VIEW;
        }
    }

    @GetMapping("/editMemobox/{id}")
    String initEditUserForm(@PathVariable long id, Model model) {
        model.addAttribute("memoboxFormSource", memoBoxRepository
                .findMemoBoxByMemoBoxId(id)
                .orElseThrow(() -> new RuntimeException("asd")));
        return PAGE_MEMOBOX_EDIT;
    }

    /*TODO
     *   - przy wywołaniu akcji Edit nadpisuje wszystkie dane encji i czyści numer klienta i w związku z tym nie widać później memoboxa na liście memoboxów użytkownika
     * */
    @PostMapping("/editMemobox/{id}")
    String processEditUserEntityForm(@PathVariable("id") long id,
                                     @ModelAttribute("memoboxFormSource")
                                     @Valid MemoBox memoBox,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return PAGE_MEMOBOX_EDIT;
        } else {
            LOGGER.info("memobox input ={} ", memoBox);

            memoBox.setMemoBoxId(id);
            memoBoxRepository.save(memoBox);

            LOGGER.info("user output ={} ", memoBox);
            redirectAttributes.addFlashAttribute("message", String.format("Memobox %s edited.", memoBox.getMemoBoxId()));
            return REDIRECT_PAGE_MEMOBOX_VIEW;
        }
    }

    @GetMapping("/deleteMemobox/{id}")
    String initDeleteUserEntity(@PathVariable("id") long id,
                                RedirectAttributes redirectAttributes) {
        memoBoxRepository.deleteByMemoBoxId(id);
        redirectAttributes.addFlashAttribute("message", String.format("Memobox %s deleted", id));
        return REDIRECT_PAGE_MEMOBOX_VIEW;
    }
}
