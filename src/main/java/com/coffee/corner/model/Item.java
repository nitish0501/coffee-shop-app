package com.coffee.corner.model;


public class Item {
    String name;
    String currency;
    Double price;
    String typeProduct;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(String typeProduct) {
        this.typeProduct = typeProduct;
    }

    public Item(String name, String currency, Double price, String typeProduct) {
        this.name = name;
        this.currency = currency;
        this.price = price;
        this.typeProduct = typeProduct;
    }

    public Item() {
    }

    public String toString() { return "" + String.format("%-13s",name) + "                  " + price+ " "  + currency + ""  +"\n"; }

}
