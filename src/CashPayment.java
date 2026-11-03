import java.util.ArrayList;
import java.util.Scanner;

public class CashPayment {
    public static void cash(double price, ArrayList<String> completeOrder, Scanner in) {
        // Ask for cash payment
        System.out.print("Please enter your cash amount (##.##): ");
        double tendered = in.nextDouble();

        // Check if sufficient funds given
        if (tendered >= price) {
            // Calculate change
            double change = tendered - price;
            // Print change
            System.out.println("Here is your change " + String.format("%.2f", change));
            // Add transaction to array list to be written
            completeOrder.add("Cash");
            completeOrder.add(Double.toString(tendered));
            completeOrder.add(String.format("%.2f", change));
        }
        else {
            System.out.println("Insufficient Amount");
            System.exit(0);
        }
    }
}
