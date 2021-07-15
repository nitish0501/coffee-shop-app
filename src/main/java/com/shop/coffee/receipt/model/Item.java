package com.shop.coffee.receipt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    String name;
    String currency;
    Double price;
    String typeProduct;

    public String toString() { return "" + name + " : " + price+ " "  + currency + ""  +"\n"; }

}
