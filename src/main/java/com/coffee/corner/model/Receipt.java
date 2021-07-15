package com.coffee.corner.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Receipt {
    private static final String CURRENCY = "CHF";
    String id;
    Date date = new Date();
    List<Item> items = new ArrayList<Item>();
    List<Item> discountedItems = new ArrayList<Item>();
    Double total;
    Double totalDiscount;

    public static String getCURRENCY() {
        return CURRENCY;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getDiscountedItems() {
        return discountedItems;
    }

    public void setDiscountedItems(List<Item> discountedItems) {
        this.discountedItems = discountedItems;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------------").append("\n");
        sb.append("            Charlene's Coffee Corner").append("\n");
        sb.append("--------------------------------------------------").append("\n");
        //sb.append("Id Receipt: ").append(id).append("\n");
        sb.append("Date      : ").append(date).append("\n");
        sb.append("Item                            Price").append("\n");
        sb.append("--------------------------------------------------").append("\n");
        for (Item i : items) {
            sb.append(i.toString());
        }

        sb.append("--------------------------------------------------").append("\n");
        sb.append("Total :                       ").append(String.format("%.2f", total)).append(" ")
                .append(CURRENCY).append("\n\n");
        sb.append("--------------------------------------------------").append("\n");
        sb.append("Total Discount :").append("               ").append((-1) * totalDiscount).append(" ").append(CURRENCY).append("\n");
        sb.append("--------------------------------------------------").append("\n");
        sb.append("Thank you. Come Again!").append("\n");
        return sb.toString();
    }
}
