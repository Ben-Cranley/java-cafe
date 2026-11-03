import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StockAdjuster {

    // Decrease the stock of the ordered item by one.
    // The item is found by name in the inventory file, so items hidden
    // from the menu (out of stock) can't shift which line gets changed.
    public static void decreaseStock(String itemName, String inventoryFilePath) {

        // Read the existing contents of the inventory file
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inventoryFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
            return;
        }

        // Find the line for the ordered item
        int itemIndex = -1;
        for (int i = 0; i < lines.size(); i++) {
            String name = lines.get(i).split(",")[0].trim();
            if (name.equalsIgnoreCase(itemName.trim())) {
                itemIndex = i;
                break;
            }
        }

        if (itemIndex == -1) {
            System.out.println("Item not found in inventory: " + itemName);
            return;
        }

        // Split the line into name, price and stock
        String[] parts = lines.get(itemIndex).split(",");
        if (parts.length != 3) {
            System.out.println("Invalid inventory file format.");
            return;
        }

        try {
            int stock = Integer.parseInt(parts[2].trim());
            if (stock > 0) {
                // Decrease the stock count by one and rebuild the line
                stock--;
                lines.set(itemIndex, parts[0].trim() + ", " + parts[1].trim() + ", " + stock);

                // Write the updated contents back to the inventory file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(inventoryFilePath))) {
                    for (String updatedLine : lines) {
                        writer.write(updatedLine);
                        writer.newLine();
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to inventory file: " + e.getMessage());
                }
            } else {
                System.out.println("Item is out of stock.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid stock count format.");
        }
    }
}
