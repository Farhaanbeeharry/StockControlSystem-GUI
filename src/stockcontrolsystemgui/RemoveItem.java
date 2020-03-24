package stockcontrolsystemgui;

import java.io.FileInputStream;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.*;

/**
 * this class contains the remove GUI and algorithms
 *
 * @author Farhaan Beeharry - M00681483
 */
public class RemoveItem {

    static Stage removeWindow, confirmWindow;
    static TextField inputItemCode, itemNameField, itemCodeField, itemPriceField, itemQuantityField;

    /**
     * GUI to prompt user which item does he/she wants to remove
     *
     * @throws Exception
     */
    public static void removeItem() throws Exception {

        removeWindow = new Stage();
        removeWindow.getIcons().add(new Image("file:images/logo.png"));
        removeWindow.setTitle("Remove an item");
        removeWindow.setResizable(false);
        removeWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane removeItem = new GridPane();
        removeItem.setAlignment(Pos.CENTER);
        removeItem.setVgap(30);

        Label removeItemTitle = new Label();
        removeItemTitle.setText("Removing an item from inventory");
        removeItemTitle.setAlignment(Pos.CENTER);
        removeItemTitle.setPrefWidth(Double.MAX_VALUE);

        Label chooseItemCode = new Label();
        chooseItemCode.setText("Enter the item code that you want to remove");
        chooseItemCode.setAlignment(Pos.CENTER);
        chooseItemCode.setPrefWidth(Double.MAX_VALUE);

        Text itemCode = new Text("Enter item code :");
        inputItemCode = new TextField();
        inputItemCode.setFocusTraversable(false);
        inputItemCode.setPromptText("item code here");

        ImageView removeIcon = new ImageView(new Image(new FileInputStream("images/removeIcon.png")));
        removeIcon.setFitWidth(25);
        removeIcon.setFitHeight(25);
        Button btnRemove = new Button("Remove", removeIcon);
        btnRemove.setMinWidth(250);
        btnRemove.setFocusTraversable(false);
        btnRemove.setOnAction(e -> {
            if (inputItemCode.getText().equalsIgnoreCase("")) {
                try {
                    MessageBox.box("Field cannot be empty. Try again !");
                } catch (Exception ex) {
                }
            } else if (!inputItemCode.getText().matches("^[a-zA-Z0-9]+$")) {
                try {
                    MessageBox.box("Error ! Check item code and try again !");
                } catch (Exception ex) {
                }
            } else {
                try {
                    removeConfirmation();
                } catch (Exception ex) {
                }
            }
        });

        ImageView cancelIcon = new ImageView(new Image(new FileInputStream("images/cancelIcon.png")));
        cancelIcon.setFitWidth(25);
        cancelIcon.setFitHeight(25);
        Button btnCancel = new Button("Cancel", cancelIcon);
        btnCancel.setMinWidth(250);
        btnCancel.setFocusTraversable(false);
        btnCancel.setOnAction(e -> removeWindow.close());

        HBox hBtns = new HBox(40);
        hBtns.getChildren().addAll(btnRemove, btnCancel);
        hBtns.setAlignment(Pos.CENTER);

        removeItem.add(removeItemTitle, 0, 0, 2, 1);
        removeItem.add(chooseItemCode, 0, 2, 2, 1);
        removeItem.add(itemCode, 0, 4);
        removeItem.add(inputItemCode, 1, 4);
        removeItem.add(hBtns, 0, 6, 2, 1);
        removeItem.setPadding(new Insets(10, 250, 10, 250));
        removeItem.setHgap(50);

        Scene scene = new Scene(removeItem);
        scene.getStylesheets().add("file:stylesheet/stylesheet.css");

        removeWindow.setScene(scene);
        removeWindow.setMaximized(true);
        removeWindow.showAndWait();
    }

    /**
     * passes the remove item data to other method and overwrites the inventory
     * file with the new array
     *
     * @param searchResult the index of the item to be removed
     * @throws Exception
     */
    private static void remove(int searchResult) throws Exception {

        Items[] inventory = DataImport.importInventory();

        inventory = removeTheElement(inventory, searchResult);

        removeWindow.close();
        confirmWindow.close();

        MessageBox.box("Item removed successfully !");

        DataExport.updateFile(inventory);

    }

    /**
     * deletes the object from the array and returns the array
     *
     * @param items the array of items
     * @param index the index of the item to be removed
     * @return returns the updated array
     */
    public static Items[] removeTheElement(Items[] items, int index) {

        Items[] anotherItems = new Items[items.length - 1];

        for (int x = 0, k = 0; x < items.length; x++) {

            if (x == index) {
                continue;
            }

            anotherItems[k++] = items[x];
        }

        return anotherItems;
    }

    /**
     * GUI to confirm removal of item it also shows the different data of the
     * item to be removed
     *
     * @throws Exception
     */
    private static void removeConfirmation() throws Exception {

        Items[] inventory = DataImport.importInventory();

        int searchResult = -1;
        for (int x = 0; x < DataImport.count(); x++) {
            if (inventory[x].getItemCode().equalsIgnoreCase(inputItemCode.getText())) {
                searchResult = x;
            }
        }

        if (searchResult == -1) {
            MessageBox.box("No match found ! Try another item code");
        } else {

            final int finalSearch = searchResult;

            confirmWindow = new Stage();
            confirmWindow.getIcons().add(new Image("file:images/logo.png"));
            confirmWindow.setTitle("Confirm remove of item");
            confirmWindow.setResizable(false);
            confirmWindow.initModality(Modality.APPLICATION_MODAL);

            GridPane confirmPane = new GridPane();
            confirmPane.setAlignment(Pos.CENTER);
            confirmPane.setVgap(30);

            Label removeConfirm = new Label();
            removeConfirm.setText("Confirm removal of this item");
            removeConfirm.setAlignment(Pos.CENTER);
            removeConfirm.setPrefWidth(Double.MAX_VALUE);

            Text itemName = new Text("Item name :");
            itemNameField = new TextField();
            itemNameField.setText(inventory[searchResult].getItemName());
            itemNameField.setEditable(false);
            itemNameField.setFocusTraversable(false);

            Text itemCode = new Text("Item code :");
            itemCodeField = new TextField();
            itemCodeField.setText(inventory[searchResult].getItemCode());
            itemCodeField.setEditable(false);
            itemCodeField.setFocusTraversable(false);

            Text itemPrice = new Text("Item price : Rs");
            itemPriceField = new TextField();
            itemPriceField.setText(String.valueOf(inventory[searchResult].getItemPrice()));
            itemPriceField.setEditable(false);
            itemPriceField.setFocusTraversable(false);

            Text itemQuantity = new Text("Item quantity :");
            itemQuantityField = new TextField();
            itemQuantityField.setText(String.valueOf(inventory[searchResult].getQuantity()));
            itemQuantityField.setEditable(false);
            itemQuantityField.setFocusTraversable(false);

            ImageView confirmIcon = new ImageView(new Image(new FileInputStream("images/confirmIcon.png")));
            confirmIcon.setFitWidth(25);
            confirmIcon.setFitHeight(25);
            Button btnConfirm = new Button("Confirm", confirmIcon);
            btnConfirm.setMinWidth(250);
            btnConfirm.setFocusTraversable(false);
            btnConfirm.setOnAction(e -> {
                try {
                    remove(finalSearch);
                } catch (Exception ex) {
                }
            });

            ImageView cancelIcon = new ImageView(new Image(new FileInputStream("images/cancelIcon.png")));
            cancelIcon.setFitWidth(25);
            cancelIcon.setFitHeight(25);
            Button btnCancel = new Button("Cancel", cancelIcon);
            btnCancel.setMinWidth(250);
            btnCancel.setFocusTraversable(false);
            btnCancel.setOnAction(e -> {
                removeWindow.close();
                confirmWindow.close();
            });

            HBox Hbtns = new HBox(40);
            Hbtns.getChildren().addAll(btnConfirm, btnCancel);
            Hbtns.setAlignment(Pos.CENTER);

            confirmPane.add(removeConfirm, 0, 0, 2, 1);
            confirmPane.add(itemName, 0, 2);
            confirmPane.add(itemNameField, 1, 2);
            confirmPane.add(itemCode, 0, 3);
            confirmPane.add(itemCodeField, 1, 3);
            confirmPane.add(itemPrice, 0, 4);
            confirmPane.add(itemPriceField, 1, 4);
            confirmPane.add(itemQuantity, 0, 5);
            confirmPane.add(itemQuantityField, 1, 5);
            confirmPane.add(Hbtns, 0, 7, 2, 1);
            confirmPane.setPadding(new Insets(10, 250, 10, 250));
            confirmPane.setHgap(50);

            Scene scene = new Scene(confirmPane);
            scene.getStylesheets().add("file:stylesheet/stylesheet.css");

            confirmWindow.setScene(scene);
            confirmWindow.setMaximized(true);
            confirmWindow.showAndWait();
        }

    }

}
