import java.io.*;
import java.util.ArrayList;

public class TransactionWriter {
    // Method to write transaction details to a file
    public static void transactionWriter(ArrayList<String> completeOrder, String filename) {
        try {
            FileWriter writer = new FileWriter(filename, true);

            // Combine elements of completeOrder with commas and end the line
            String line = String.join(",", completeOrder) + "\n";

            // Write the line to the file
            writer.write(line);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: Cannot write to " + filename);
        }
    }
}
