package com.example.produktydemo.model;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;
    private String category;

    public Product(String name, BigDecimal price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "name: " + name + ", price: " + price + ", category: " + category;
    }
}
