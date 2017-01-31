package com.ironyard.data;

/**
 * Created by rohanayub on 1/31/17.
 */
public class GroceryItem {
    private long id;
    private double price;
    private String name;
    private String category;
    private int aisle;

    public GroceryItem(){

    }

    public GroceryItem(long id, double price, String name, String category, int aisle) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.category = category;
        this.aisle = aisle;
    }

    public GroceryItem(double price, String name, String category, int aisle) {
        this.price = price;
        this.name = name;
        this.category = category;
        this.aisle = aisle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public int getAisle() {
        return aisle;
    }

    public void setAisle(int aisle) {
        this.aisle = aisle;
    }


}
