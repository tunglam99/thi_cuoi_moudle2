package com.codegym.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "productTypes")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(targetEntity = Product.class)
    private List<Product> products;

    public ProductType() {
    }

    public ProductType(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }
    public ProductType(Long id,String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
