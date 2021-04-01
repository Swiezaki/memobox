package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.User;
import io.github.wojtekmarcin.memobox.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserViewController {
    public static final String USER_ADD_PAGE = "user/add";
    public static final String USER_VIEW_PAGE = "user/view";
    public static final String USER_EDIT_PAGE = "user/edit";
    public static final String REDIRECT_USER_VIEW_PAGE = "redirect:/user/view";

    private final UserRepository repository;

    public UserViewController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/view")
    String getAllUserViewPage(Model model) {
        model.addAttribute("users", repository.findAll());
        return USER_VIEW_PAGE;
    }

    @GetMapping("/addUser")
    String initAddUserForm(Model model) {
        model.addAttribute("userToAdd", new User());
        return USER_ADD_PAGE;
    }

    @PostMapping("/addUser")
    String proccessAddUserEntityForm(@ModelAttribute("userToAdd") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return USER_ADD_PAGE;
        }
        repository.save(user);
        return USER_ADD_PAGE;
    }


    @GetMapping("/editUser/{id}")
    String initEditUserForm(@PathVariable long id, Model model) {
        model.addAttribute("userFormSource", repository.findUserByUserId(id));
        return USER_EDIT_PAGE;
    }

    //TODO
    @PostMapping("/editUser/{id}")
    String proccessEditUserEntityForm(@PathVariable("id") long id,
                                      @ModelAttribute("userFormSource") @Valid User toUpdate,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return USER_EDIT_PAGE;
        } else {
            User userFromRepository = repository.findUserByUserId(id);
            userFromRepository.setLogin(toUpdate.getLogin());
            userFromRepository.setLogin(toUpdate.getPassword());
            repository.save(userFromRepository);
            return REDIRECT_USER_VIEW_PAGE;
        }
    }

    @GetMapping("/deleteUser/{id}")
    String initDeleteUserEntity(@PathVariable("id") long id) {
        repository.deleteUserByUserId(id);
        return REDIRECT_USER_VIEW_PAGE;
    }
}
