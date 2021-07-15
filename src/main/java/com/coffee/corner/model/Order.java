package com.coffee.corner.model;



public class Order {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                '}';
    }

    public Order(String name) {
        this.name = name;
    }
}
