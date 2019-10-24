package com.codegym.model;

public class Items {
    private Product product;
    private int Quantity;

    public Items() {
    }

    public Items(Product product, int quantity) {
        this.product = product;
        Quantity = quantity;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
