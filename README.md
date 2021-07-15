# Charlene's Coffee Corner - Details

Recently, Charlene decided to open her very own little coffee shop on a busy street corner.
Being the careful entrepreneur, she decided to start off with a limited offering, with the option to expand her
choice of products, as business goes.
Her Offering
• Coffee (small, medium, large) 2.50 CHF, 3.00 CHF, 3.50 CHF
• Bacon Roll 4.50 CHF
• Freshly squeezed orange juice (0.25l) 3.95 CHF
Extras:
• Extra milk 0.30 CHF
• Foamed milk 0.50 CHF
• Special roast coffee 0.90 CHF
Bonus Program
• Charlene's idea is to attract as many regular‘s as possible to have a steady turnaround.
turnaround.
• She decides to offer a customer stamp card, where every 5th beverage is for free.
• If a customer orders a beverage and a snack, one of the extra's is free.

## 1. Prerequisites to run the program
• Java 11 and Maven 3.8.1 version should be installed in the system

## 2. Input the element in the test

        list of products the shopper wants to purchase (large coffee with extra milk, small
            coffee with special roast, bacon roll, orange juice)

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

#### NOTE: Please pass false as the second parameter if Customer Stamp Card is not applicable for discount else pass true to avail discount on fifth Beverage.

## 3. Run the test
```
 mvn test
```
## 4. Expected result
![ScreenShot](receipt.png?raw=true "Optional Title")






