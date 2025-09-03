package com.alejandra.referee.controller;

import com.alejandra.referee.entity.CustomUser;
import com.alejandra.referee.repository.CustomUserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class CustomUserController {
    
    private final CustomUserRepository repository;

    public CustomUserController(CustomUserRepository repository) {
        this.repository = repository;
    }

    // Activity 2: form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new CustomUser());
        return "customUser/form";
    }

    // Activity 3: create
    @PostMapping
    public String create(@Valid @ModelAttribute("user") CustomUser user,
                        BindingResult result,
                        RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "customUser/form";
        }
        repository.save(user);
        ra.addFlashAttribute("success", "User created successfully");
        return "redirect:/users"; // go to list
    }

    // Activity 4: list
    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", repository.findAll());
        return "customUser/list";
    }

    // Activity 5: detail
    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        CustomUser u = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("user", u);
        return "customUser/detail";
    }

    // Activity 6: delete
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/users";
    }
}
