import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class JavaCafe {

    private static final String INVENTORY_FILE = "data/inventory.txt";
    private static final String TRANSACTIONS_FILE = "data/transactions.txt";

    public static void main(String[] args) {

        // Initialise ArrayLists to store menu items, prices, stock and complete order details
        ArrayList<String> menuItems = new ArrayList<String>();
        ArrayList<String> menuPrices = new ArrayList<String>();
        ArrayList<String> completeOrder = new ArrayList<String>();
        ArrayList<String> stockCheck = new ArrayList<String>();

        // Read menu items, prices and stock from file
        MenuReader.readFile(INVENTORY_FILE, menuItems, menuPrices, stockCheck);

        // Check stock amount and remove anything that is out of stock
        StockChecker.stock(menuItems, menuPrices, stockCheck);

        // Print the menu
        MenuPrinter.printMenu(menuItems, menuPrices);

        Scanner in = new Scanner(System.in);
        int order = 0;

        try {
            // Get user's order
            order = in.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid Selection");
            System.exit(0);
        }

        // Check if user wants to exit
        if (order == 0) {
            System.exit(0);
        }
        // Check if user selects an invalid option
        else if (order > menuItems.size() || order < 0) {
            System.out.println("Invalid Selection");
            System.exit(0);
        }

        order = order - 1; // Adjust index to match ArrayList

        // Get current date and time for the transaction
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String purchaseTime = dateFormat.format(now);

        // Add purchase time to completeOrder
        completeOrder.add(purchaseTime);

        double price = Double.parseDouble(menuPrices.get(order));
        String menuOrder = menuItems.get(order);
        System.out.println("You have ordered the " + menuOrder + ". This costs " + String.format("%.2f", price));

        // Add menu item and price to completeOrder
        completeOrder.add(menuOrder);
        completeOrder.add(Double.toString(price));

        // Get user's payment method
        System.out.print("Would you like to pay by (1) Cash or (2) Card? ");
        int paymentMethod = 0;

        // Validate user input
        try {
            paymentMethod = in.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid Selection");
            System.exit(0);
        }

        // Process payment based on user's choice
        try {
            // Cash Process
            if (paymentMethod == 1) {
                CashPayment.cash(price, completeOrder, in);
            }
            // Card Process
            else if (paymentMethod == 2) {
                CardPayment.card(completeOrder, in);
            }
            else {
                System.out.println("Invalid Selection");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Invalid Selection");
            System.exit(0);
        }

        // Close scanner
        in.close();
        System.out.println("Thank You For Shopping In The Java Cafe");

        // Write transaction details to file
        TransactionWriter.transactionWriter(completeOrder, TRANSACTIONS_FILE);

        // Reduce stock of the ordered item by 1 (matched by name, so hidden items can't shift it)
        StockAdjuster.decreaseStock(menuOrder, INVENTORY_FILE);
    }
}
