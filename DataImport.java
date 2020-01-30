package stockcontrolsystemgui;

import java.io.*;

/**
 * This class reads from file found in /data/
 *
 * @author Farhaan Beeharry - M00681483
 */
public class DataImport {

    /**
     * this method counts the number of item in the inventory file
     *
     * @return the number of items in the file
     */
    public static int count() {

        int count = 0;

        try {
            FileReader inputFile = new FileReader("data/inventory.txt");

            try (BufferedReader inputBuffer = new BufferedReader(inputFile)) {
                String readItem = inputBuffer.readLine();

                while (readItem != null) {
                    count++;
                    readItem = inputBuffer.readLine();
                }
            }
        } catch (IOException e) {
        }

        return count;

    }

    /**
     * this methods reads from the file and creates an array with the file data
     *
     * @return the array of data to the method needed
     * @throws IOException prevent crashes in case file not found !
     */
    public static Items[] importInventory() throws IOException {

        Items[] items = new Items[count()];

        FileReader inputFile = new FileReader("data/inventory.txt");

        try (BufferedReader inputBuffer = new BufferedReader(inputFile)) {
            String data;
            data = inputBuffer.readLine();

            while (data != null) {
                for (int i = 0; i < count(); i++) {
                    String[] inventory = data.split("--");
                    items[i] = new Items();
                    items[i].setItemCode(inventory[0]);
                    items[i].setItemName(inventory[1]);
                    items[i].setItemPrice(Double.parseDouble(inventory[2]));
                    items[i].setQuantity(Integer.parseInt(inventory[3]));
                    data = inputBuffer.readLine();
                }

            }
        }

        return items;

    }

    /**
     * this methods reads from the file and creates an array with the default
     * file data
     *
     * @return the array of default data to the method needed
     * @throws IOException prevent crashes in case file not found !
     */
    public static Items[] importDefaultInventory() throws IOException {

        Items[] items = new Items[countDefault()];

        FileReader inputFile = new FileReader("data/default.txt");

        try (BufferedReader inputBuffer = new BufferedReader(inputFile)) {
            String data;
            data = inputBuffer.readLine();

            while (data != null) {
                for (int i = 0; i < countDefault(); i++) {
                    String[] inventory = data.split("--");
                    items[i] = new Items();
                    items[i].setItemCode(inventory[0]);
                    items[i].setItemName(inventory[1]);
                    items[i].setItemPrice(Double.parseDouble(inventory[2]));
                    items[i].setQuantity(Integer.parseInt(inventory[3]));
                    data = inputBuffer.readLine();
                }

            }
        }

        return items;

    }

    /**
     * this method counts the number of item in the default inventory file
     *
     * @return the number of items in the default file
     */
    public static int countDefault() {

        int count = 0;

        try {
            FileReader inputFile = new FileReader("data/default.txt");

            try (BufferedReader inputBuffer = new BufferedReader(inputFile)) {
                String readItem = inputBuffer.readLine();

                while (readItem != null) {
                    count++;
                    readItem = inputBuffer.readLine();
                }
            }
        } catch (IOException e) {
        }

        return count;

    }

}
