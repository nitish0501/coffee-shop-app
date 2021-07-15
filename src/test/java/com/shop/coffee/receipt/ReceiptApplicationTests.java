package com.shop.coffee.receipt;

import com.shop.coffee.receipt.model.Order;
import com.shop.coffee.receipt.model.Receipt;
import com.shop.coffee.receipt.service.ReceiptGenerationImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReceiptApplicationTests {

    ReceiptGenerationImpl receiptGeneration = new ReceiptGenerationImpl();


    @Test
    void orderReceiptForOneBeverageWithExtraAndWithoutStampCard() {
        List<String> orderList = new ArrayList<String>();
        Order o1 = new Order("large coffee with extra milk");
        orderList.add(o1.getName());
        Receipt receipt = receiptGeneration.generateReceipt(orderList, false);
        System.out.println(receipt);
        assertEquals(3.80,receipt.getTotal());
    }

    @Test
    void orderReceiptForOneBeverageAndWithFifthBeverageFreeStampCard() {
        List<String> orderList = new ArrayList<String>();
        Order o1 = new Order("large coffee");
        orderList.add(o1.getName());
        Receipt receipt = receiptGeneration.generateReceipt(orderList, true);
        System.out.println(receipt);
        assertNotEquals(3.50,receipt.getTotal());
    }

    @Test
    void orderReceiptForOneBeverageWithoutExtra() {
        List<String> orderList = new ArrayList<String>();
        Order o1 = new Order("large coffee");
        orderList.add(o1.getName());
        Receipt receipt = receiptGeneration.generateReceipt(orderList, false);
        System.out.println(receipt);
        assertNotNull(receipt);
    }

    @Test
    void orderReceiptForOneBeverageWithExtraAndOneSnack() {
        List<String> orderList = new ArrayList<String>();

        Order o1 = new Order("large coffee with extra milk");
        Order o2 = new Order("bacon roll");

        orderList.add(o1.getName());
        orderList.add(o2.getName());

        Receipt receipt = receiptGeneration.generateReceipt(orderList, false);

        System.out.println(receipt);
        assertEquals(8.00,receipt.getTotal());

    }

    @Test
    void orderReceiptForOneBeverageWithoutExtraAndOneSnack() {
        List<String> orderList = new ArrayList<String>();

        Order o1 = new Order("large coffee");
        Order o2 = new Order("bacon roll");

        orderList.add(o1.getName());
        orderList.add(o2.getName());

        Receipt receipt = receiptGeneration.generateReceipt(orderList, false);

        System.out.println(receipt);
        assertEquals(8.00,receipt.getTotal());

    }

    @Test
    void mixedOrderReceiptWithFifthBeverageFreeStampCard() {
        List<String> orderList = new ArrayList<String>();

        Order o1 = new Order("large coffee with extra milk");
        Order o2 = new Order("small coffee with special roast");
        Order o3 = new Order("small coffee");
        Order o4 = new Order("bacon roll");
        Order o5 = new Order("orange juice");
        Order o6 = new Order("medium coffee");
        Order o7 = new Order("small coffee");
        orderList.add(o1.getName());
        orderList.add(o2.getName());
        orderList.add(o3.getName());
        orderList.add(o4.getName());
        orderList.add(o5.getName());
        orderList.add(o6.getName());
        orderList.add(o7.getName());
        Receipt receipt = receiptGeneration.generateReceipt(orderList, true);
        System.out.println(receipt);
        assertNotEquals(24.45,receipt.getTotal());

    }

    @Test
    void mixedOrderReceiptWithoutStampCardAndMoreThanFiveBeverages() {
        List<String> orderList = new ArrayList<String>();

        Order o1 = new Order("large coffee with extra milk");
        Order o2 = new Order("small coffee with special roast");
        Order o3 = new Order("small coffee");
        Order o4 = new Order("bacon roll");
        Order o5 = new Order("orange juice");
        Order o6 = new Order("medium coffee");
        Order o7 = new Order("small coffee");
        Order o8 = new Order("bacon roll");
        orderList.add(o1.getName());
        orderList.add(o2.getName());
        orderList.add(o3.getName());
        orderList.add(o4.getName());
        orderList.add(o5.getName());
        orderList.add(o6.getName());
        orderList.add(o7.getName());
        orderList.add(o8.getName());
        Receipt receipt = receiptGeneration.generateReceipt(orderList, false);
        System.out.println(receipt);
        assertEquals(24.45,receipt.getTotal());

    }

    @Test
    void mixedOrderReceiptWithDoubleSnacksWithoutStampCard() {
        List<String> orderList = new ArrayList<String>();

        Order o1 = new Order("large coffee with extra milk");
        Order o2 = new Order("small coffee with special roast");
        Order o3 = new Order("small coffee");
        Order o4 = new Order("bacon roll");
        Order o5 = new Order("orange juice");
        Order o6 = new Order("bacon roll");

        orderList.add(o1.getName());
        orderList.add(o2.getName());
        orderList.add(o3.getName());
        orderList.add(o4.getName());
        orderList.add(o5.getName());
        orderList.add(o6.getName());

        Receipt receipt = receiptGeneration.generateReceipt(orderList, false);
        System.out.println(receipt);
        assertEquals(21.45, receipt.getTotal());
    }


}
