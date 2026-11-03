import java.io.*;
import java.util.ArrayList;

public class MenuReader {

    // Method to read menu items, prices and stock from a file
    public static void readFile(String filename, ArrayList<String> menuItems, ArrayList<String> menuPrices, ArrayList<String> stockCheck) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                // Split each line into name, price and stock
                String[] sections = line.split(",");
                if (sections.length >= 3) {
                    menuItems.add(sections[0].trim());  // Add menu item
                    menuPrices.add(sections[1].trim()); // Add menu price
                    stockCheck.add(sections[2].trim()); // Add stock amount
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: Cannot read from " + filename);
        }
    }
}
