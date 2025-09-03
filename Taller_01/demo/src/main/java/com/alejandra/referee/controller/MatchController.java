package com.alejandra.referee.controller;

import com.alejandra.referee.entity.Match;
import com.alejandra.referee.repository.MatchRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/matches")
public class MatchController {

    private final MatchRepository repository;

    public MatchController(MatchRepository repository) {
        this.repository = repository;
    }

    // Activity 2: form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("match", new Match());
        return "match/form";
    }

    // Activity 3: create
    @PostMapping
    public String create(@Valid @ModelAttribute("match") Match match,
                        BindingResult result,
                        RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "match/form";
        }
        repository.save(match);
        ra.addFlashAttribute("success", "Match created successfully");
        return "redirect:/matches"; // go to list
    }

    // Activity 4: list
    @GetMapping
    public String list(Model model) {
        model.addAttribute("matches", repository.findAll());
        return "match/list";
    }

    // Activity 5: detail
    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        Match m = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("match", m);
        return "match/detail";
    }

    // Activity 6: delete
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/matches";
    }
}
