package com.shop.coffee.receipt.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Receipt {
    private static final String CURRENCY = "CHF";
    String id;
    Date date = new Date();
    List<Item> items = new ArrayList<Item>();
    List<Item> discountedItems = new ArrayList<Item>();
    Double total;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------------").append("\n");
        sb.append("Charlene's Coffee Corner").append("\n");
        sb.append("--------------------------------------------------").append("\n");
        sb.append("Id Receipt: ").append(id).append("\n");
        sb.append("Date      : ").append(date).append("\n");
        sb.append("Item           Price").append("\n");
        sb.append("--------------------------------------------------").append("\n");
        for (Item i : items) {
            sb.append(i.toString());
        }

        sb.append("--------------------------------------------------").append("\n");
        sb.append("Total      : ").append(String.format("%.2f", total)).append(" ")
                .append(CURRENCY).append("\n\n");
        sb.append("--------------------------------------------------").append("\n");
        sb.append("Total Discount").append("\n");
        for (Item i : discountedItems) {
            sb.append(i.toString());
        }
        sb.append("--------------------------------------------------").append("\n");
        sb.append("Thank you. Come Again!").append("\n");
        return sb.toString();
    }
}
