package stockcontrolsystemgui;

/**
 * this class contains sorting methods
 *
 * @author Farhaan Beeharry - M00681483
 */
public class Sort {

    /**
     * it sorts the data in the file in ascending order of itemCode
     *
     * @return returns the array of sorted data to requested methods
     * @throws Exception
     */
    public static Items[] itemCode() throws Exception {

        Items[] inventory = DataImport.importInventory();

        String tempCode, tempName;
        int tempQuantity;
        double tempPrice;

        for (int x = 1; x < inventory.length; x++) {
            for (int j = x; j > 0; j--) {

                if (inventory[j].getItemCode().compareToIgnoreCase(inventory[j - 1].getItemCode()) < 0) {

                    tempCode = inventory[j].getItemCode();
                    tempName = inventory[j].getItemName();
                    tempQuantity = inventory[j].getQuantity();
                    tempPrice = inventory[j].getItemPrice();

                    inventory[j].setItemCode(inventory[j - 1].getItemCode());
                    inventory[j].setItemName(inventory[j - 1].getItemName());
                    inventory[j].setQuantity(inventory[j - 1].getQuantity());
                    inventory[j].setItemPrice(inventory[j - 1].getItemPrice());

                    inventory[j - 1].setItemCode(tempCode);
                    inventory[j - 1].setItemName(tempName);
                    inventory[j - 1].setQuantity(tempQuantity);
                    inventory[j - 1].setItemPrice(tempPrice);
                }
            }
        }

        return inventory;

    }

    /**
     * it sorts the data in the file in ascending order of itemName
     *
     * @return returns the array of sorted data to requested methods
     * @throws Exception
     */
    public static Items[] itemName() throws Exception {

        Items[] inventory = DataImport.importInventory();

        String tempCode, tempName;
        int tempQuantity;
        double tempPrice;

        for (int x = 1; x < inventory.length; x++) {
            for (int j = x; j > 0; j--) {

                if (inventory[j].getItemName().compareToIgnoreCase(inventory[j - 1].getItemName()) < 0) {

                    tempCode = inventory[j].getItemCode();
                    tempName = inventory[j].getItemName();
                    tempQuantity = inventory[j].getQuantity();
                    tempPrice = inventory[j].getItemPrice();

                    inventory[j].setItemCode(inventory[j - 1].getItemCode());
                    inventory[j].setItemName(inventory[j - 1].getItemName());
                    inventory[j].setQuantity(inventory[j - 1].getQuantity());
                    inventory[j].setItemPrice(inventory[j - 1].getItemPrice());

                    inventory[j - 1].setItemCode(tempCode);
                    inventory[j - 1].setItemName(tempName);
                    inventory[j - 1].setQuantity(tempQuantity);
                    inventory[j - 1].setItemPrice(tempPrice);
                }
            }
        }

        return inventory;

    }

    /**
     * it sorts the data in the file in ascending order of itemPrice in case of
     * similarities in item price, it sorts by itemName
     *
     * @return returns the array of sorted data to requested methods
     * @throws Exception
     */
    public static Items[] itemPrice() throws Exception {

        Items[] inventory = itemName();

        String tempCode, tempName;
        int tempQuantity;
        double tempPrice;

        for (int x = 1; x < inventory.length; x++) {
            for (int j = x; j > 0; j--) {

                if (inventory[j].getItemPrice() < inventory[j - 1].getItemPrice()) {

                    tempCode = inventory[j].getItemCode();
                    tempName = inventory[j].getItemName();
                    tempQuantity = inventory[j].getQuantity();
                    tempPrice = inventory[j].getItemPrice();

                    inventory[j].setItemCode(inventory[j - 1].getItemCode());
                    inventory[j].setItemName(inventory[j - 1].getItemName());
                    inventory[j].setQuantity(inventory[j - 1].getQuantity());
                    inventory[j].setItemPrice(inventory[j - 1].getItemPrice());

                    inventory[j - 1].setItemCode(tempCode);
                    inventory[j - 1].setItemName(tempName);
                    inventory[j - 1].setQuantity(tempQuantity);
                    inventory[j - 1].setItemPrice(tempPrice);
                }
            }
        }

        return inventory;

    }
}
