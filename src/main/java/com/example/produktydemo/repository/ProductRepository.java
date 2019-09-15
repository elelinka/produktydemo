package com.example.produktydemo.repository;

import com.example.produktydemo.model.Product;
import com.example.produktydemo.model.category.ProductCategory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private List<Product> products;

    public ProductRepository() {
        products = new LinkedList<>();
        products.add(new Product("czekolada", BigDecimal.valueOf(2.5), ProductCategory.GROCERIES.getName()));
        products.add(new Product("płyn do naczyń", BigDecimal.valueOf(7.49), ProductCategory.HOUSEHOLD_ITEMS.getName()));
        products.add(new Product("telewizor", BigDecimal.valueOf(1999), ProductCategory.OTHER.getName()));
    }

    public List<Product> getAll() {
        return products;
    }

    public List<Product> getByCategory(String category) {
        return products.stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public void add(String name, BigDecimal price, String category) {
        List<Product> products = getAll();
        products.add(new Product(name, price, category));
    }

}
