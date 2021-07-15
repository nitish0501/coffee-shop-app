package com.coffee.corner.service;

import com.coffee.corner.model.Item;
import com.coffee.corner.model.Receipt;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Generates the receipt of the orders and applies discount.
 *
 * @author 947213
 */
public class ReceiptGenerationImpl implements ReceiptGeneration {

  public static final String WITH = "with";
  private Item smallCoffee = new Item("small coffee", "CHF", 2.50, "B");
  private Item mediumCoffee = new Item("medium coffee", "CHF", 3.00, "B");
  private Item largeCoffee = new Item("large coffee", "CHF", 3.50, "B");
  private Item baconRoll = new Item("bacon roll", "CHF", 4.50, "S");
  private Item orangeJuice = new Item("orange juice", "CHF", 3.95, "B");
  private Item extraMilk = new Item("extra milk", "CHF", 0.30, "E");
  private Item foamedMilk = new Item("foamed milk", "CHF", 0.50, "E");
  private Item specialRoast = new Item("special roast", "CHF", 0.90, "E");

  List<Item> itemListArray = new ArrayList<>();

  /**
   * Generates the receipt of the orders and applies discount.
   */
  public Receipt generateReceipt(List<String> orderList, boolean stampCardDiscount) {
    itemListArray.add(largeCoffee);
    itemListArray.add(mediumCoffee);
    itemListArray.add(smallCoffee);
    itemListArray.add(baconRoll);
    itemListArray.add(orangeJuice);
    itemListArray.add(extraMilk);
    itemListArray.add(foamedMilk);
    itemListArray.add(specialRoast);

    List<Item> itemList = new ArrayList<>();
    itemList = convertOrderToItems(orderList);

    //If a customer orders a beverage(B) and a snack(S), one of the extra's(E) is free.

    //Count for the beverage(B)
    long beverageCount = itemList
            .stream()
            .filter(p -> "B".equals(p.getTypeProduct()))
            .count();

    //Count for the snack(S)
    long snackCount = itemList
            .stream()
            .filter(p -> "S".equals(p.getTypeProduct()))
            .count();
    //Get the minimum count for extra discount
    long minimalDiscount = getMinimumDiscount(beverageCount, snackCount);

    //has Extra(E) can be for discount
    List<Item> orderDiscountable = new ArrayList<Item>();
    for (Item item : itemList) {
      if ("E".equals(item.getTypeProduct())) {
        orderDiscountable.add(item);
      }
    }

    Receipt receipt = new Receipt();
    receipt.setDate(new Date());
    List<Item> items = getItemsFromOrder(itemList);
    receipt.setItems(items);

    //add the discountable item
    List<Item> productDiscountableTmp = new ArrayList<Item>();
    List<Item> itemsForDiscount = new ArrayList<Item>();
    if (!orderDiscountable.isEmpty() && minimalDiscount > 0) {
      if (minimalDiscount > orderDiscountable.size()) {
        //add all element for discount
        itemsForDiscount = getItemsFromProductsForDiscount(orderDiscountable);
      } else {
        //add the minimal element for discount
        for (int i = 0; i < minimalDiscount; i++) {
          productDiscountableTmp.add(orderDiscountable.get(i));
        }
        itemsForDiscount = getItemsFromProductsForDiscount(productDiscountableTmp);
      }
    }
    if (stampCardDiscount == true && beverageCount > 0 && beverageCount <= 5) {
      List<Item> beverageItem = itemList.stream()
                .filter(p -> "B".equals(p.getTypeProduct()))
                .collect(Collectors.toList());
      productDiscountableTmp.add(beverageItem.get(0));
      itemsForDiscount = getItemsFromProductsForDiscount(productDiscountableTmp);
    } else if (stampCardDiscount == true && beverageCount > 5) {
      List<Item> beverageItem = itemList.stream()
                .filter(p -> "B".equals(p.getTypeProduct()))
                .collect(Collectors.toList());
      for (int i = 0; i <= beverageItem.size(); i++) {
        if (i % 5 == 0) {
          productDiscountableTmp.add(beverageItem.get(i));
        }
      }
      itemsForDiscount = getItemsFromProductsForDiscount(productDiscountableTmp);

    } else if (stampCardDiscount == false && beverageCount > 5) {
      List<Item> beverageItem = itemList.stream()
                .filter(p -> "B".equals(p.getTypeProduct()))
                .collect(Collectors.toList());
      for (int i = 1; i < beverageItem.size(); i++) {
        if (i % 5 == 0) {
          productDiscountableTmp.add(beverageItem.get(i));
        }
      }
      itemsForDiscount = getItemsFromProductsForDiscount(productDiscountableTmp);

    }
    receipt.getItems().addAll(itemsForDiscount);
    receipt.setDiscountedItems(itemsForDiscount);
    //calculate the total  discounts
    Double discountedTotal = 0.00;
    for (Item i : itemsForDiscount) {
      discountedTotal = discountedTotal + i.getPrice();
    }
    receipt.setTotalDiscount(discountedTotal);

    UUID uuid = UUID.randomUUID();
    receipt.setId(uuid.toString());

    //calculate the total after all discounts
    Double total = 0.00;
    for (Item i : receipt.getItems()) {
      total = total + i.getPrice();
    }

    receipt.setTotal(total);
    return receipt;
  }

  //Get Items from Orders List
  private List<Item> getItemsFromOrder(List<Item> listProduct) {
    List<Item> items = new ArrayList<Item>();
    for (Item i : listProduct) {
      items.add(i);
    }
    return items;
  }

  //Get Item from Orders for Discount
  private List<Item> getItemsFromProductsForDiscount(List<Item> orderList) {
    List<Item> items = new ArrayList<Item>();

    for (Item i : orderList) {
      Item item = new Item();
      item.setPrice(i.getPrice() * (-1));
      item.setCurrency(i.getCurrency());
      item.setTypeProduct(i.getTypeProduct());
      item.setName(i.getName());
      items.add(item);
    }
    return items;
  }

  //get minimum discount
  private long getMinimumDiscount(long beverageCount, long snackCount) {
    long min;
    min = beverageCount;
    if (min > snackCount) {
      min = snackCount;
    }
    return min;
  }

  /**
   * To convert the orders to items for receipt generation.
   */
  public List<Item> convertOrderToItems(List<String> orderList) {
    List<Item> itemList = new ArrayList<>();
    for (String o : orderList) {
      if (o.contains("with")) {
        String mainOrder = o.substring(0, o.indexOf(WITH)).trim();
        String extraOrder = o.substring(o.indexOf(WITH) + 4, o.length()).trim();
        for (Item item : itemListArray) {
          if (mainOrder.equals(item.getName())) {
            itemList.add(item);
          }
          if (extraOrder.equals(item.getName())) {
            itemList.add(item);
          }
        }
      } else {
        for (Item item : itemListArray) {
          if (o.equals(item.getName())) {
            itemList.add(item);
          }
        }
      }
    }
    return itemList;
  }
}

