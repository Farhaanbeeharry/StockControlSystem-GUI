package stockcontrolsystemgui;

/**
 * this class contains similarity check methods
 *
 * @author Farhaan Beeharry - M00681483
 */
public class SimilarityCheck {

    /**
     * compares the user input item name with the existing data to prevent
     * insertion of similar data
     *
     * @param itemName user input item name when adding a new item
     * @return returns the result of the similarity check
     * @throws Exception in cause of errors, it catches the error and prevents
     * crashes
     */
    public static int itemName(String itemName) throws Exception {

        Items[] inventory = DataImport.importInventory();

        int result = -1;

        for (int i = 0; i < DataImport.count(); i++) {
            if (itemName.equalsIgnoreCase(inventory[i].getItemName())) {
                result = 1;
            }
        }

        return result;
    }

    /**
     * compares the user input item code with the existing data to prevent
     * insertion of similar data
     *
     * @param itemCode user input item code when adding a new item
     * @return returns the result of the similarity check
     * @throws Exception in cause of errors, it catches the error and prevents
     * crashes
     */
    public static int itemCode(String itemCode) throws Exception {

        Items[] inventory = DataImport.importInventory();

        int result = -1;

        for (int i = 0; i < DataImport.count(); i++) {
            if (itemCode.equalsIgnoreCase(inventory[i].getItemCode())) {
                result = 1;
            }
        }

        return result;
    }

}
