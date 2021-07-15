package com.shop.coffee.receipt.service;

import com.shop.coffee.receipt.model.Receipt;

import java.util.List;

public interface ReceiptGeneration {
 public Receipt generateReceipt(List<String> orderList, boolean stampCardDiscount);
}
