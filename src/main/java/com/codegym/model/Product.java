package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, max = 30)
    private String name;
    @ManyToOne
    @JoinColumn(name = "productType_id")
    private ProductType productType;
    private String image;

    @Size(min = 1)
    private Double price;
    private Double quantity;
    private LocalDate createDate;
    private String description;

    public Product() {
    }

    public Product(String name, ProductType productType,String image, Double price, Double quantity, LocalDate createDate, String description) {
        this.name = name;
        this.productType = productType;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.createDate = createDate;
        this.description = description;
    }

    public Product(Long id,String name, ProductType productType,String image, Double price, Double quantity, LocalDate createDate, String description) {
        this.id = id;
        this.name = name;
        this.productType = productType;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.createDate = createDate;
        this.description = description;
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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
