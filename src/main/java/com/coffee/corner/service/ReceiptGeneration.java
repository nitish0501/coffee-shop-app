package com.coffee.corner.service;

import com.coffee.corner.model.Receipt;

import java.util.List;

public interface ReceiptGeneration {
 public Receipt generateReceipt(List<String> orderList, boolean stampCardDiscount);
}
