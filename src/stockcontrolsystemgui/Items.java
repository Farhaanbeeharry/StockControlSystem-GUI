package stockcontrolsystemgui;

/**
 * this class contains constructors and is a class of objects
 *
 * @author Farhaan Beeharry - M00681483
 */
public class Items {

    /**
     * @itemCode the item code
     */
    private String itemCode;
    /**
     * @itemName the item name
     */
    private String itemName;
    /**
     * @quantity the quantity of this item in stock
     */
    private int quantity;
    /**
     * @itemPrice the price of the item per unit
     */
    private double itemPrice;

    /**
     * default constructor
     */
    public Items() {
        this.itemCode = " ";
        this.itemName = " ";
        this.quantity = 0;
        this.itemPrice = 0.0;
    }

    /**
     * Explicit constructor
     *
     * @param itemCode item code to add to the object
     * @param itemName item name of the object
     * @param quantity quantity value of the object
     * @param itemPrice price of the object
     */
    public Items(String itemCode, String itemName, int quantity, double itemPrice) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * method to display data via console (not used in the FINAL PROJECT)
     */
    public void display() {

        System.out.format("|  %30s %17s %21s %20s", itemName + "  |", itemCode + "  |", itemPrice + "  |", quantity + "  |");
        System.out.println(" ");
    }

}
