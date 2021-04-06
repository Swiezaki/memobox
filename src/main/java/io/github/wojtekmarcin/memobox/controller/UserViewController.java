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
    public static final String USER_ADD_PAGE = "user/add";
    public static final String USER_VIEW_PAGE = "user/view";
    public static final String USER_EDIT_PAGE = "user/edit";
    public static final String REDIRECT_USER_VIEW_PAGE = "redirect:/user/view";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserRepository repository;

    public UserViewController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/view")
    String getAllUserViewPage(Model model) {
        model.addAttribute("users", repository.findAll());
        return USER_VIEW_PAGE;
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
        return USER_VIEW_PAGE;
    }

    @GetMapping("/addUser")
    String initAddUserForm(Model model) {
        model.addAttribute("userToAdd", new User());
        return USER_ADD_PAGE;
    }

    @PostMapping("/addUser")
    String processAddUserEntityForm(@ModelAttribute("userToAdd") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return USER_ADD_PAGE;
        }
        repository.save(user);
        return REDIRECT_USER_VIEW_PAGE;
    }


    @GetMapping("/editUser/{id}")
    String initEditUserForm(@PathVariable long id, Model model) {
        model.addAttribute("userFormSource", repository.findUserByUserId(id));
        return USER_EDIT_PAGE;
    }

    //TODO
    @PostMapping("/editUser/{id}")
    String processEditUserEntityForm(@PathVariable("id") long id,
                                     @ModelAttribute("userFormSource")
                                     @Valid User toUpdate,
                                     BindingResult bindingResult) {
        User userByUserId = repository.findUserByUserId(id);

        if (userByUserId.equals(toUpdate)) {
            userByUserId.setLogin(toUpdate.getLogin());
            userByUserId.setPassword(toUpdate.getPassword());
            userByUserId.setMemoBoxId(toUpdate.getMemoBoxId());
            userByUserId.setWordsSetId(toUpdate.getWordsSetId());
            repository.save(userByUserId);
            return REDIRECT_USER_VIEW_PAGE;
        } else {
            return USER_EDIT_PAGE;
        }
    }

    @GetMapping("/deleteUser/{id}")
    String initDeleteUserEntity(@PathVariable("id") long id) {
        repository.deleteUserByUserId(id);
        return REDIRECT_USER_VIEW_PAGE;
    }
}
