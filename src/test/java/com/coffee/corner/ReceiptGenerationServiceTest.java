package com.coffee.corner;

import com.coffee.corner.model.Order;
import com.coffee.corner.model.Receipt;
import com.coffee.corner.service.ReceiptGenerationImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;



public class ReceiptGenerationServiceTest {

    ReceiptGenerationImpl receiptGeneration = new ReceiptGenerationImpl();


    @Test
    public void orderReceiptForOneBeverageWithExtraAndWithoutStampCard() {
        List<String> orderList = new ArrayList<String>();
        Order o1 = new Order("large coffee with extra milk");
        orderList.add(o1.getName());
        Receipt receipt = receiptGeneration.generateReceipt(orderList, false);
        System.out.println(receipt);
        assertEquals(3.80,receipt.getTotal());
    }

    @Test
    public void orderReceiptForOneBeverageAndWithFifthBeverageFreeStampCard() {
        List<String> orderList = new ArrayList<String>();
        Order o1 = new Order("large coffee");
        orderList.add(o1.getName());
        Receipt receipt = receiptGeneration.generateReceipt(orderList, true);
        System.out.println(receipt);
        assertNotEquals(3.50,receipt.getTotal());
    }

    @Test
    public void orderReceiptForOneBeverageWithoutExtra() {
        List<String> orderList = new ArrayList<String>();
        Order o1 = new Order("large coffee");
        orderList.add(o1.getName());
        Receipt receipt = receiptGeneration.generateReceipt(orderList, false);
        System.out.println(receipt);
        assertNotNull(receipt);
    }

    @Test
    public void orderReceiptForOneBeverageWithExtraAndOneSnack() {
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
    public void orderReceiptForOneBeverageWithoutExtraAndOneSnack() {
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
    public void mixedOrderReceiptWithFifthBeverageFreeStampCard() {
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
    public void mixedOrderReceiptWithoutStampCardAndMoreThanFiveBeverages() {
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
    public void mixedOrderReceiptWithDoubleSnacksWithoutStampCard() {
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
