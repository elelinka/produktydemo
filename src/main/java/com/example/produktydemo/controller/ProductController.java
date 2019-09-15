package com.example.produktydemo.controller;

import com.example.produktydemo.model.Product;
import com.example.produktydemo.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductController {

    ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @ResponseBody
    @GetMapping("/list")
    public String getAll(@RequestParam(required = false) String category) {
        List<Product> products;

        if (StringUtils.isEmpty(category)) {
            products = repository.getAll();
        } else {
            products = repository.getByCategory(category);
        }
        return convertToString(products) + "<br> suma produktów: " + repository.sumPrice(products).toPlainString();
    }

    @PostMapping("/add")
    public String add(@RequestParam(value = "name") String name,
                      @RequestParam("price") BigDecimal price,
                      @RequestParam(value = "category") String category) {
        if (isParamsOk(name, price, category)) {
            return "redirect:/err";
        } else {

            repository.add(name, price, category);
            return "redirect:/success";
        }
    }

    private String convertToString(List<Product> products) {
        String result = "";

        for (Product product : products) {
            result += product.toString() + "<br>";
        }
        return result;
    }

    private boolean isParamsOk(String name, BigDecimal price, String category) {
        return StringUtils.isEmpty(name) || StringUtils.isEmpty(price) || StringUtils.isEmpty(category);
    }

    @GetMapping("/success")
    public String successPage() {
        return "success.html";
    }

    @GetMapping("/err")
    public String errPage() {
        return "err.html";
    }

}
