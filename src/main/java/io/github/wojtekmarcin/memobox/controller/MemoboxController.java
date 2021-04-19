package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.entities.MemoBox;
import io.github.wojtekmarcin.memobox.repository.MemoBoxRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@Controller
@RequestMapping("/memobox")
public class MemoboxController {
    private final MemoBoxRepository repository;

    public MemoboxController(MemoBoxRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/view")
    String showMemoboxView(Model model) {
        model.addAttribute("memoboxes", repository.findAll());
        return "memobox/view";
    }
}
