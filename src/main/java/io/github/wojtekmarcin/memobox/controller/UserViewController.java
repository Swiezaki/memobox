package io.github.wojtekmarcin.memobox.controller;

import com.sun.xml.bind.v2.TODO;
import io.github.wojtekmarcin.memobox.entities.User;
import io.github.wojtekmarcin.memobox.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserViewController {
    public static final String PAGE_USER_ADD = "user/add";
    public static final String PAGE_USER_VIEW = "user/view";
    public static final String PAGE_USER_EDIT = "user/edit";
    public static final String REDIRECT_PAGE_USER_VIEW = "redirect:/user/view";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    public UserViewController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/view")
    String getAllUserViewPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return PAGE_USER_VIEW;
    }

/*    TODO
        - komunikat o braku odnalezionych rekordów
        - dodanie możliwości rozszerzania listy parametrów wyszukiwania
        - filtrowanie
    */

    @GetMapping("/search")
    String initSearchingEntityByKey(Model model,
                                    @Param("keyword") String keyword,
                                    @Param("filterType") Integer filterType) {
        model.addAttribute("keyword", keyword);
        model.addAttribute("filterType", filterType);

        LOGGER.info("keyword ={}, filterType={}", keyword, filterType);

        if (filterType == 1) {
            model.addAttribute("users", userRepository.findUserByLogin(keyword));
        } else {
            model.addAttribute("users", userRepository.findUserByPassword(keyword));
        }

        LOGGER.info("users ={}", model.getAttribute("users"));
        return PAGE_USER_VIEW;
    }

    @GetMapping("/addUser")
    String initAddUserForm(Model model) {
        model.addAttribute("userToAdd", new User());
        return PAGE_USER_ADD;
    }

    @PostMapping("/addUser")
    String processAddUserEntityForm(@ModelAttribute("userToAdd") @Valid User user,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return PAGE_USER_ADD;
        }
        if (!userRepository.findUserByLogin(user.getLogin()).isEmpty()){
            bindingResult.rejectValue("login","login.userToAdd","Login already exist.");
            return PAGE_USER_ADD;
        }

        userRepository.save(user);
        redirectAttributes.addFlashAttribute("message", String.format("User %s created.", user.getUserId()));
        return REDIRECT_PAGE_USER_VIEW;
    }

    @GetMapping("/editUser/{id}")
    String initEditUserForm(@PathVariable long id, Model model) {
        model.addAttribute("userFormSource", userRepository.findUserByUserId(id));
        return PAGE_USER_EDIT;
    }

    /*TODO
        - Brak walidacji (np. pola memoboxID oraz WordsetId to tablice, trzeba stworzyć implementację createUser która będzie tworzyć dwie listy)
        */

    @PostMapping("/editUser/{id}")
    String processEditUserEntityForm(@PathVariable("id") long id,
                                     @ModelAttribute("userFormSource")
                                     @Valid User userToUpdate,
                                     BindingResult bindingResult,
                                     ModelMap model,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.put("userFromSource", userRepository.findUserByUserId(id));
            return PAGE_USER_EDIT;
        } else {
            User userFromRepository = userRepository.findUserByUserId(id);
            LOGGER.info("user from repo input ={}, user to update={}", userFromRepository, userToUpdate);

            userFromRepository.setLogin(userToUpdate.getLogin());
            userFromRepository.setPassword(userToUpdate.getPassword());
            userFromRepository.setMemoBoxId(userToUpdate.getMemoBoxId());
            userFromRepository.setWordsSetId(userToUpdate.getWordsSetId());

            userRepository.save(userFromRepository);
            LOGGER.info("users output={}", userFromRepository);
            redirectAttributes.addFlashAttribute("message", String.format("User %s edited.", userFromRepository.getUserId()));
            return REDIRECT_PAGE_USER_VIEW;
        }
    }

    @GetMapping("/deleteUser/{id}")
    String initDeleteUserEntity(@PathVariable("id") long id) {
        userRepository.deleteUserByUserId(id);
        return REDIRECT_PAGE_USER_VIEW;
    }
}
