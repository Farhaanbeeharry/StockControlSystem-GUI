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
 * this class contains GUI and algorithms for changing the stock of an item
 *
 * @author Farhaan Beeharry - M00681483
 */
public class ChangeStock {

    static Stage changeStockWindow, confirmWindow;
    static TextField inputItemCode, itemNameField, itemCodeField, itemPriceField, itemQuantityField, inputNewQuantity;

    /**
     * GUI to choose which item to change stock
     *
     * @throws Exception
     */
    public static void changeStock() throws Exception {

        changeStockWindow = new Stage();
        changeStockWindow.getIcons().add(new Image("file:images/logo.png"));
        changeStockWindow.setTitle("Change stock");
        changeStockWindow.setResizable(false);
        changeStockWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane changeStock = new GridPane();
        changeStock.setAlignment(Pos.CENTER);
        changeStock.setVgap(30);

        Label changeStockTitle = new Label();
        changeStockTitle.setText("Changing stock of an item");
        changeStockTitle.setAlignment(Pos.CENTER);
        changeStockTitle.setPrefWidth(Double.MAX_VALUE);
        //changeStockTitle.setAlignment(Pos.BASELINE_CENTER);

        Label chooseItemCode = new Label();
        chooseItemCode.setText("Enter the item code that you want to change stock");
        chooseItemCode.setAlignment(Pos.CENTER);
        chooseItemCode.setPrefWidth(Double.MAX_VALUE);

        Text itemCode = new Text("Enter item code :");
        inputItemCode = new TextField();
        inputItemCode.setFocusTraversable(false);
        inputItemCode.setPromptText("item code here");

        ImageView changeIcon = new ImageView(new Image(new FileInputStream("images/changeIcon.png")));
        changeIcon.setFitWidth(25);
        changeIcon.setFitHeight(25);
        Button btnChange = new Button("Change", changeIcon);
        btnChange.setMinWidth(250);
        btnChange.setFocusTraversable(false);
        btnChange.setOnAction(e -> {
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
                    changeStockConfirm();
                } catch (Exception ex) {
                }
            }
        });

        ImageView cancelIcon = new ImageView(new Image(new FileInputStream("images/cancelIcon.png")));
        cancelIcon.setFitWidth(25);
        cancelIcon.setFitHeight(25);
        Button btnCancel = new Button("Cancel", cancelIcon);
        btnCancel.setText("Cancel");
        btnCancel.setMinWidth(250);
        btnCancel.setFocusTraversable(false);
        btnCancel.setOnAction(e -> changeStockWindow.close());

        HBox hBtns = new HBox(40);
        hBtns.getChildren().addAll(btnChange, btnCancel);
        hBtns.setAlignment(Pos.CENTER);

        changeStock.add(changeStockTitle, 0, 0, 2, 1);
        changeStock.add(chooseItemCode, 0, 2, 2, 1);
        changeStock.add(itemCode, 0, 4);
        changeStock.add(inputItemCode, 1, 4);
        changeStock.add(hBtns, 0, 5, 2, 1);
        changeStock.setPadding(new Insets(10, 250, 10, 250));
        changeStock.setHgap(50);

        Scene scene = new Scene(changeStock);
        scene.getStylesheets().add("file:stylesheet/stylesheet.css");

        changeStockWindow.setScene(scene);
        changeStockWindow.setMaximized(true);
        changeStockWindow.showAndWait();

    }

    /**
     * input the new stock value this GUI also shows the data of the item to be
     * updated
     *
     * @throws Exception
     */
    public static void changeStockConfirm() throws Exception {

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

            confirmWindow = new Stage();
            confirmWindow.getIcons().add(new Image("file:images/logo.png"));
            confirmWindow.setTitle("Confirm change stock of item");
            confirmWindow.setResizable(false);
            confirmWindow.initModality(Modality.APPLICATION_MODAL);

            GridPane confirmPane = new GridPane();
            confirmPane.setAlignment(Pos.CENTER);
            confirmPane.setVgap(30);

            Label removeConfirm = new Label();
            removeConfirm.setText("Confirm change stock of this item");
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

            Text newQuantity = new Text("Input new quantity : ");
            inputNewQuantity = new TextField();
            inputItemCode.setFocusTraversable(false);
            inputItemCode.setPromptText("new quantity here");

            ImageView confirmIcon = new ImageView(new Image(new FileInputStream("images/confirmIcon.png")));
            confirmIcon.setFitWidth(25);
            confirmIcon.setFitHeight(25);
            Button btnConfirm = new Button("Confirm", confirmIcon);
            btnConfirm.setMinWidth(250);
            btnConfirm.setFocusTraversable(false);
            btnConfirm.setOnAction(e -> {
                try {
                    process();
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
                changeStockWindow.close();
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
            confirmPane.add(newQuantity, 0, 7);
            confirmPane.add(inputNewQuantity, 1, 7);
            confirmPane.add(Hbtns, 0, 9, 2, 1);
            confirmPane.setPadding(new Insets(10, 250, 10, 250));
            confirmPane.setHgap(50);

            Scene scene = new Scene(confirmPane);
            scene.getStylesheets().add("file:stylesheet/stylesheet.css");

            confirmWindow.setScene(scene);
            confirmWindow.setMaximized(true);
            confirmWindow.showAndWait();
        }
    }

    /**
     * the process of updating the stock of the item
     *
     * @throws Exception
     */
    public static void process() throws Exception {

        Items[] inventory = DataImport.importInventory();

        int searchResult = -1;
        for (int x = 0; x < DataImport.count(); x++) {
            if (inventory[x].getItemCode().equalsIgnoreCase(inputItemCode.getText())) {
                searchResult = x;
            }
        }

        inventory[searchResult].setQuantity(Integer.parseInt(inputNewQuantity.getText()));

        DataExport.updateFile(inventory);

        MessageBox.box("Stock changed successfully !");

        changeStockWindow.close();
        confirmWindow.close();

    }
}
