package io.github.wojtekmarcin.memobox.controller;

import io.github.wojtekmarcin.memobox.repository.MemoBoxRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/memobox")
public class MemoboxViewController {
    private final MemoBoxRepository repository;

    public MemoboxViewController(MemoBoxRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/view")
    String showMemoboxView(Model model) {
        model.addAttribute("memoboxes", repository.findAll());
        return "memobox/view";
    }
}
