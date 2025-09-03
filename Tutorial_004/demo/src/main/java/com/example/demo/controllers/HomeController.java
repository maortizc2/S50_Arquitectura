package com.example.demo.controllers;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Welcome to Spring Boot");
        model.addAttribute("subtitle", "An Spring Boot Eafit App");
        return "home/index";
    }


    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About Us-Online Boot");
        model.addAttribute("subtitle", "About Us");
        model.addAttribute("description", "This is about page...");
        model.addAttribute("author", "Developed by:Alejandra Ortiz");
        return "home/about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", "Contact Us - Online Store");
        model.addAttribute("subtitle", "Contact Us");
        return "home/contact";
    }

    @GetMapping("/products")
    public String products(Model model) {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product 1", "Description 1", 19.99));
        products.add(new Product(2L, "Product 2", "Description 2", 29.99));
        products.add(new Product(3L, "Product 3", "Description 3", 39.99));

        model.addAttribute("title", "Products - Online Store");
        model.addAttribute("subtitle", "List of Products");
        model.addAttribute("products", products);
        return "product/index";
    }
}
class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;

    public Product(Long id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }
}

