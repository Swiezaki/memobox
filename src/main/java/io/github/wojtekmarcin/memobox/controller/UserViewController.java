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
    public static final String USER_ADD = "user/add";
    public static final String USER_VIEW = "user/view";
    public static final String USER_EDIT = "user/edit";

    private final UserRepository repository;

    public UserViewController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/view")
    String getAllUserViewPage(Model model) {
        model.addAttribute("users", repository.findAll());
        return USER_VIEW;
    }

    @GetMapping("/view/addUser")
    String getUserViewPage(Model model) {
        model.addAttribute("userToAdd", new User());
        return USER_ADD;
    }

    @PostMapping("/view/addUser")
    String addUserEntity(@Valid @ModelAttribute("userToAdd") User user, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("message", "User has not been added");
            return USER_ADD;
        }
        repository.save(user);
        model.addAttribute("message", "User has been added");
        return USER_ADD;
    }

    @GetMapping("/editUser/{id}")
    String getEditUserPage(@PathVariable long id, Model model) {
        User userByUserId = repository.findUserByUserId(id);
        User userFormSource = new User();
        userFormSource.setLogin(userByUserId.getLogin());
        userFormSource.setPassword(userByUserId.getPassword());
        model.addAttribute("userFormSource", userFormSource);
        return USER_EDIT;
    }

    //TODO
    @PostMapping("/editUser/{id}")
    String editUserEntity(@PathVariable("id") long id, @ModelAttribute("userToAdd") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", "User has not been edited");
            return USER_EDIT;
        }
        //repository.update
        model.addAttribute("message", "User has been edited");
        return USER_EDIT;
    }

    @GetMapping("/delete/{id}")
    String deleteUserEntity(@PathVariable("id") long id, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("message", "User has not been deleted");
            return USER_VIEW;
        }
        repository.deleteUserByUserId(id);
        model.addAttribute("message", "User has been deleted");
        return USER_VIEW;
    }
}
