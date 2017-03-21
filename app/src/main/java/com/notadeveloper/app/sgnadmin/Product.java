package com.notadeveloper.app.sgnadmin;

/**
 * Created by Chirag on 20-Feb-17.
 */
public class Product {
    int pid;
    String name;
    String category;
    double price;
    int stock;
    String picture;
    boolean prescription;


    public Product() {
    }


    public Product(int pid, String name, String category, double price, int stock, String picture) {
        this.pid = pid;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.picture = picture;
    }

    public Product(int pid, String name, String category, double price, int stock, String picture, boolean prescription) {
        this.pid = pid;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.picture = picture;
        this.prescription = prescription;
    }

    public boolean isPrescription() {
        return prescription;
    }

    public void setPrescription(boolean prescription) {
        this.prescription = prescription;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
