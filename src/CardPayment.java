import java.util.ArrayList;
import java.util.Scanner;

public class CardPayment {
    public static void card(ArrayList<String> completeOrder, Scanner in) {
        // Prompt user to enter card type
        System.out.print("Please enter your card type (1) MasterCard or (2) Visa: ");
        int inputCardType = in.nextInt();

        // Add card transaction to completeOrder list
        completeOrder.add("Card");

        if (inputCardType == 1) {
            // Add card type to completeOrder
            completeOrder.add("MasterCard");
        }
        else if (inputCardType == 2) {
            // Add card type to completeOrder
            completeOrder.add("Visa");
        }
        else {
            System.out.println("Invalid Selection");
            System.exit(0);
        }
    }
}
