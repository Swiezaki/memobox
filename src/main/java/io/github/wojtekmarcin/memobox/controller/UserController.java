package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.controller.REST.UserRESTController;
import io.github.wojtekmarcin.memobox.entities.User;
import io.github.wojtekmarcin.memobox.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    public static final String PAGE_USER_VIEW = "user/view";
    public static final String PAGE_USER_EDIT = "user/edit";
    public static final String REDIRECT_PAGE_USER_VIEW = "redirect:/user/view";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/view")
    String getAllUserViewPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return PAGE_USER_VIEW;
    }

/*    TODO
        - dodanie możliwości rozszerzania listy parametrów wyszukiwania
        - filtrowanie wyników
        - refaktor switch case
    */

    @GetMapping("/search")
    String initSearchingEntityByKey(Model model,
                                    @Param("keyword") String keyword,
                                    @Param("filterType") Integer filterType) {
        model.addAttribute("keyword", keyword);
        model.addAttribute("filterType", filterType);

        LOGGER.info("keyword ={}, filterType={}", keyword, filterType);

        /*FIXME*/
        switch (filterType) {
            case 1: {
                if (userRepository.findAllUsersByUsername(keyword).isEmpty()) {
                    model.addAttribute("notFoundMessage1", String.format("Login not found"));
                } else {
                    model.addAttribute("users", userRepository.findAllUsersByUsername(keyword));
                }
                break;
            }
            case 2: {
                if (userRepository.findAllUsersByPassword(keyword).isEmpty()) {
                    model.addAttribute("notFoundMessage2", String.format("Password not found"));
                } else {
                    model.addAttribute("users", userRepository.findAllUsersByPassword(keyword));
                }
                break;
            }
        }

        LOGGER.info("users ={} filtered by filterType ={}", model.getAttribute("users"), filterType);
        return PAGE_USER_VIEW;
    }

    @GetMapping("/editUser/{id}")
    String initEditUserForm(@PathVariable long id, Model model) {
        model.addAttribute("userFormSource", userRepository.findUserByUserId(id));
        return PAGE_USER_EDIT;
    }

    /*TODO
     *   - walidację autentyczności na loginie
     *   - po błędnej walidacji numer klienta edytowanego zmienia się na 0
     * */

    @PostMapping("/editUser/{id}")
    String processEditUserEntityForm(@PathVariable("id") long id,
                                     @ModelAttribute("userFormSource")
                                     @Valid User user,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return PAGE_USER_EDIT;
        } else {
            LOGGER.info("user input ={} ", user);

            user.setUserId(id);
            userRepository.save(user);

            LOGGER.info("user output ={} ", user);
            redirectAttributes.addFlashAttribute("message", String.format("User %s edited.", user.getUserId()));
            return REDIRECT_PAGE_USER_VIEW;
        }
    }

    @GetMapping("/deleteUser/{id}")
    String initDeleteUserEntity(@PathVariable("id") long id,
                                RedirectAttributes redirectAttributes) {
        userRepository.deleteUserByUserId(id);
        redirectAttributes.addFlashAttribute("message", String.format("User %s deleted", id));
        return REDIRECT_PAGE_USER_VIEW;
    }
}
