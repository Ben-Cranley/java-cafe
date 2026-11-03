import java.util.ArrayList;

public class MenuPrinter {
    // Method to print the menu
    public static void printMenu(ArrayList<String> menuItems, ArrayList<String> menuPrices) {
        System.out.println("=========MENU=========");

        for (int i = 0; i < menuItems.size(); i++) {
            // Print menu item number, name, and price
            System.out.println(String.format("%d) %-14s %s", i + 1, menuItems.get(i), menuPrices.get(i)));
        }
        // Print the exit option
        System.out.println("0) Exit");
        System.out.println("======================");
        System.out.print("Enter Choice: ");
    }
}
