package com.example.demo.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Map;


@Controller
public class ProductController {

    private static final List<Map<String, String>> products = List.of(
        Map.of("id", "1", "name", "TV", "description", "Best TV"),
        Map.of("id", "2", "name", "iPhone", "description", "Best iPhone"),
        Map.of("id", "3", "name", "Chromecast", "description", "Best Chromecast"),
        Map.of("id", "4", "name", "Glasses", "description", "Best Glasses")
);

    @GetMapping("/products")
    public String index(Model model) {
        model.addAttribute("title", "Products - Online Store");
        model.addAttribute("subtitle", "List of products");
        model.addAttribute("products", products);
        return "product/index";
    }

    @GetMapping("/products/{id}")
    public String show(@PathVariable String id, Model model) {
        int productId = Integer.parseInt(id) - 1;
        if (productId < 0 || productId >= products.size()) {
            return "redirect:/products";
        }
        Map<String, String> product = products.get(productId);
        model.addAttribute("title", product.get("name") + " - Online Store");
        model.addAttribute("subtitle", product.get("name") + " - Product Information");
        model.addAttribute("product", product);
        return "product/show";
    }
}