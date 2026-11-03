# Java Café

A command-line point-of-sale system for a small café, written in Java.

First-year project, Fundamentals of Programming 2, TU Dublin (2024).

## Features

- Loads the menu, prices and stock levels from a text file
- Hides anything that is out of stock
- Takes cash (and works out the change) or card payments
- Logs every sale, with a timestamp, to a CSV file
- Takes the sold item off the stock count

## Example run

```
=========MENU=========
1) Americano      3.00
2) Cappuccino     3.00
3) Flat White     2.50
4) Latte          3.00
5) House Special  5.00
0) Exit
======================
Enter Choice: 2
You have ordered the Cappuccino. This costs 3.00
Would you like to pay by (1) Cash or (2) Card? 1
Please enter your cash amount (##.##): 5.00
Here is your change 2.00
Thank You For Shopping In The Java Cafe
```

Espresso is in the inventory file but doesn't show on the menu because its stock is 0.

## Running it

You need a Java JDK (version 8 or later). From the project folder:

```
javac -d out src/*.java
java -cp out JavaCafe
```

Run it from the project folder so it can find the `data` folder.

## How it's built

| File | What it does |
|---|---|
| `JavaCafe.java` | Main flow: menu, order, payment |
| `MenuReader.java` | Loads `data/inventory.txt` |
| `StockChecker.java` | Removes out-of-stock items from the menu |
| `MenuPrinter.java` | Prints the menu |
| `CashPayment.java`, `CardPayment.java` | Handle each payment type |
| `TransactionWriter.java` | Adds each sale to `data/transactions.txt` |
| `StockAdjuster.java` | Updates the stock after a sale |

## Changes since submission

I went back over the project in 2026 before publishing it:

- **Fixed a stock bug.** Once an item sold out and was hidden, the menu numbers shifted but the stock update still used the old position, so the wrong item's stock went down. Stock is now updated by item name.
- Used one `Scanner` for the whole program instead of opening a new one on `System.in` for each payment.
- Renamed classes to standard Java naming (`JavaCafe` rather than `javaCafe`) and split the files into `src` and `data` folders.
