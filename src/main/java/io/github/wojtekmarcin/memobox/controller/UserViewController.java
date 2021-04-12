package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.User;
import io.github.wojtekmarcin.memobox.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserViewController {
    public static final String PAGE_USER_ADD = "user/add";
    public static final String PAGE_USER_VIEW = "user/view";
    public static final String PAGE_USER_EDIT = "user/edit";
    public static final String REDIRECT_PAGE_USER_VIEW = "redirect:/user/view";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserRepository repository;

    public UserViewController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/view")
    String getAllUserViewPage(Model model) {
        model.addAttribute("users", repository.findAll());
        return PAGE_USER_VIEW;
    }

    @GetMapping("/search")
    String initSearchingEntityByKey(Model model,
                                    @Param("keyword") String keyword,
                                    @Param("filterType") Integer filterType) {
        model.addAttribute("keyword", keyword);
        model.addAttribute("filterType", filterType);

        LOGGER.info("keyword ={}, filterType={}", keyword, filterType);

        if (filterType == 1) {
            model.addAttribute("users", repository.findUserByLogin(keyword));
        } else {
            model.addAttribute("users", repository.findUserByPassword(keyword));
        }

        LOGGER.info("users ={}", model.getAttribute("users"));
        return PAGE_USER_VIEW;
    }

    @GetMapping("/addUser")
    String initAddUserForm(Model model) {
        model.addAttribute("userToAdd", new User());
        return PAGE_USER_ADD;
    }

    /*TODO
     *  1. Brak walidacji na duplikujące się loginy
     * 2. Dodać komunikat o poprawnym dodaniu użytkownika*/

    @PostMapping("/addUser")
    String processAddUserEntityForm(@ModelAttribute("userToAdd") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return PAGE_USER_ADD;
        }
        repository.save(user);
        return REDIRECT_PAGE_USER_VIEW;
    }

    @GetMapping("/editUser/{id}")
    String initEditUserForm(@PathVariable long id, Model model) {
        model.addAttribute("userFormSource", repository.findUserByUserId(id));
        return PAGE_USER_EDIT;
    }

    /*-TODO
     *    1. Brak walidacji pola memoboxID oraz WordsetId to tablice, trzeba stworzyć implementację createUser która będzie tworzyć dwie listy
     *  2. Dodać komunikat o poprawnej edycji użytkownika*/

    @PostMapping("/editUser/{id}")
    String processEditUserEntityForm(@PathVariable("id") long id,
                                     @ModelAttribute("userFormSource")
                                     @Valid User userToUpdate,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return PAGE_USER_EDIT;
        } else {
            User userFromRepository = repository.findUserByUserId(id);
            LOGGER.info("user from repo input ={}, user to update={}", userFromRepository, userToUpdate);

            userFromRepository.setLogin(userToUpdate.getLogin());
            userFromRepository.setPassword(userToUpdate.getPassword());
            userFromRepository.setMemoBoxId(userToUpdate.getMemoBoxId());
            userFromRepository.setWordsSetId(userToUpdate.getWordsSetId());
            repository.save(userFromRepository);
            LOGGER.info("users output={}", userFromRepository);

            return REDIRECT_PAGE_USER_VIEW;
        }
    }

    @GetMapping("/deleteUser/{id}")
    String initDeleteUserEntity(@PathVariable("id") long id) {
        repository.deleteUserByUserId(id);
        return REDIRECT_PAGE_USER_VIEW;
    }
}
