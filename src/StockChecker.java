import java.util.ArrayList;

public class StockChecker {
    public static void stock(ArrayList<String> items, ArrayList<String> price, ArrayList<String> stock) {

        for (int i = 0; i < stock.size(); i++) {
            // Change stock quantity from string to int
            int stockQuantity = Integer.parseInt(stock.get(i).trim());
            // If stock is 0, remove the item from all ArrayLists
            if (stockQuantity == 0) {
                items.remove(i);
                price.remove(i);
                stock.remove(i);

                // Decrease i to stay in line with lists
                i--;
            }
        }
    }
}
