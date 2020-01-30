package stockcontrolsystemgui;

import java.io.*;

/**
 * this class contains methods which writes data(array) to file
 *
 * @author Farhaan Beeharry - M00681483
 */
public class DataExport {

    /**
     * overwrites the existing file with the data of the array
     *
     * @param inventory the array of data to be written to the file
     * @throws Exception
     */
    public static void updateFile(Items[] inventory) throws Exception {

        try {
            File outputFile = new File("data/inventory.txt");
            try (PrintStream writer = new PrintStream(outputFile)) {
                for (int i = 0; i < inventory.length; i++) {
                    if (!(inventory[i].getItemCode() == null)) {
                        writer.print(inventory[i].getItemCode());
                        writer.print("--");
                        writer.print(inventory[i].getItemName());
                        writer.print("--");
                        writer.print(inventory[i].getItemPrice());
                        writer.print("--");
                        if (i == inventory.length - 1) {
                            writer.print(inventory[i].getQuantity());
                        } else {
                            writer.println(inventory[i].getQuantity());
                        }
                    }

                }
            }
        } catch (FileNotFoundException e) {
            MessageBox.box("File not found!");
        }
    }

    /**
     * adds another line of data in the file
     *
     * @param itemName the item name to be added
     * @param itemCode the item code to be added
     * @param itemPrice the price of the item
     * @param quantity the quantity of the item
     * @throws Exception
     */
    public static void addItemToFile(String itemName, String itemCode, double itemPrice, int quantity) throws Exception {

        try {
            File inputFile = new File("data/inventory.txt");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile, true))) {
                writer.newLine();
                writer.write(itemCode + "--" + itemName + "--" + itemPrice + "--" + quantity);
            }

        } catch (IOException e) {
            MessageBox.box("File not found!");
        }
    }
}
