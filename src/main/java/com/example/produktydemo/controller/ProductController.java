package com.example.produktydemo.controller;

import com.example.produktydemo.model.Product;
import com.example.produktydemo.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/list")
public class ProductController {

    ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @ResponseBody
    @GetMapping
    public String getAll() {
        List<Product> products = repository.getAll();
        return convertToString(products);
    }

    @ResponseBody
    @GetMapping("/category")
    public String getByCategory(String category) {
        List<Product> productsByCategory = repository.getByCategory(category);
        return convertToString(productsByCategory);
    }

    @PostMapping("/add")
    public void add(@RequestParam(value = "name") String name,
                    @RequestParam("price") BigDecimal price,
                    @RequestParam(value = "category") String category) {

        repository.add(name, price, category);
    }

    private String convertToString(List<Product> products) {
        String result = "";

        for (Product product : products) {
            result += product.toString() + "<br>";
        }
        return result;
    }

}
