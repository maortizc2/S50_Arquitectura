package com.alejandra.referee.controller;

import com.alejandra.referee.entity.Specialty;
import com.alejandra.referee.repository.SpecialtyRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/specialties")
public class SpecialtyController {

    private final SpecialtyRepository repository;

    public SpecialtyController(SpecialtyRepository repository) {
        this.repository = repository;
    }

// Activity 2: Forms
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("specialty", new Specialty());
        return "specialty/form";
    }


// Activity 3: Validation 
    @PostMapping
    public String create(@Valid @ModelAttribute("specialty") Specialty specialty,BindingResult result,RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "specialty/form";
        }
        repository.save(specialty);
        ra.addFlashAttribute("success", "Created successfully");
        return "redirect:/specialties"; // redirige a listar (A4)
    }

// Activity 4: List (Id + Name)
    @GetMapping
    public String list(Model model) {
        model.addAttribute("specialties", repository.findAll());
        return "specialty/list";
    }

// Activity 5: Show details
    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        Specialty s = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("specialty", s);
        return "specialty/detail";
    }


// Activity 6: Delete and redirect to list
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
    repository.deleteById(id);
    return "redirect:/specialties"; 
    }
}
