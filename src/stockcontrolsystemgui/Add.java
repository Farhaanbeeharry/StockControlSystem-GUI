package stockcontrolsystemgui;

import java.io.FileInputStream;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.*;

/**
 * this class contains a GUI and algorithms to add a new item to the inventory
 *
 * @author Farhaan Beeharry - M00681483
 */
public class Add {

    static Stage addWindow;
    static TextField inputItemName, inputItemCode, inputItemPrice, inputQuantity;

    /**
     * a GUI to allow user to input data about the item to be added
     *
     * @throws Exception
     */
    public static void addItem() throws Exception {

        addWindow = new Stage();
        addWindow.getIcons().add(new Image("file:images/logo.png"));
        addWindow.setTitle("Add new item");
        addWindow.setResizable(false);
        addWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane addItem = new GridPane();
        addItem.setAlignment(Pos.CENTER);
        addItem.setVgap(30);

        Label addItemTitle = new Label();
        addItemTitle.setText("Add a new item to inventory");
        addItemTitle.setAlignment(Pos.CENTER);
        addItemTitle.setPrefWidth(Double.MAX_VALUE);

        Text itemName = new Text("Enter item name :");
        inputItemName = new TextField();
        inputItemName.setPromptText("item name here");
        inputItemName.setFocusTraversable(false);

        Text itemCode = new Text("Enter item code :");
        inputItemCode = new TextField();
        inputItemCode.setPromptText("item code here");
        inputItemCode.setFocusTraversable(false);

        Text itemPrice = new Text("Enter item price : Rs");
        inputItemPrice = new TextField();
        inputItemPrice.setPromptText("item price here (Rs)");
        inputItemPrice.setFocusTraversable(false);

        Text itemQuantity = new Text("Enter item quantity :");
        inputQuantity = new TextField();
        inputQuantity.setPromptText("item quantity here");
        inputQuantity.setFocusTraversable(false);

        ImageView addIcon = new ImageView(new Image(new FileInputStream("images/addIcon.png")));
        addIcon.setFitWidth(25);
        addIcon.setFitHeight(25);
        Button btnAdd = new Button("Add", addIcon);
        btnAdd.setMinWidth(250);
        btnAdd.setFocusTraversable(false);
        btnAdd.setOnAction(e -> {
            try {
                processAdd();
            } catch (Exception ex) {
            }
        });

        ImageView cancelIcon = new ImageView(new Image(new FileInputStream("images/cancelIcon.png")));
        cancelIcon.setFitWidth(25);
        cancelIcon.setFitHeight(25);
        Button btnCancel = new Button("Cancel", cancelIcon);
        btnCancel.setMinWidth(250);
        btnCancel.setFocusTraversable(false);
        btnCancel.setOnAction(e -> addWindow.close());

        HBox Hbtns = new HBox(40);
        Hbtns.getChildren().addAll(btnAdd, btnCancel);
        Hbtns.setAlignment(Pos.CENTER);

        addItem.add(addItemTitle, 0, 0, 2, 1);
        addItem.add(itemName, 0, 2);
        addItem.add(inputItemName, 1, 2);
        addItem.add(itemCode, 0, 3);
        addItem.add(inputItemCode, 1, 3);
        addItem.add(itemPrice, 0, 4);
        addItem.add(inputItemPrice, 1, 4);
        addItem.add(itemQuantity, 0, 5);
        addItem.add(inputQuantity, 1, 5);
        addItem.add(Hbtns, 0, 7, 2, 1);
        addItem.setPadding(new Insets(10, 250, 10, 250));
        addItem.setHgap(50);

        Scene scene = new Scene(addItem);
        scene.getStylesheets().add("file:stylesheet/stylesheet.css");

        addWindow.setScene(scene);
        addWindow.setMaximized(true);
        addWindow.showAndWait();

    }

    /**
     * a method to verify the data input by the user to check for similarity to
     * check for invalid inputs such as alphabets in integer textFields
     *
     * @throws Exception
     */
    public static void processAdd() throws Exception {

        String itemName = inputItemName.getText();
        String itemCode = inputItemCode.getText();
        String itemPrice = inputItemPrice.getText();
        String itemQuantity = inputQuantity.getText();

        if (itemName.equalsIgnoreCase("")) {
            MessageBox.box("Item name field cannot be empty !");
            return;
        } else if (SimilarityCheck.itemName(itemName) == 1) {
            MessageBox.box("Error ! Item name already exists !\nCheck and try again !");
            return;
        } else if (!itemName.matches("^[a-zA-Z]+$")) {
            MessageBox.box("Error ! Check item name and try again !");
            return;
        } else if (itemCode.equalsIgnoreCase("")) {
            MessageBox.box("Item code field cannot be empty !");
            return;
        } else if (SimilarityCheck.itemCode(itemCode) == 1) {
            MessageBox.box("Error ! Item code already exists !\nCheck and try again !");
            return;
        } else if (!itemCode.matches("^[a-zA-Z0-9]+$")) {
            MessageBox.box("Error ! Check item code and try again !");
            return;
        } else if (itemPrice.equalsIgnoreCase("")) {
            MessageBox.box("Item price field cannot be empty !");
            return;
        } else if (!itemPrice.matches("^[0-9.]+$") || itemPrice.equalsIgnoreCase("0")) {
            MessageBox.box("Error ! Check item price and try again !");
            return;
        } else if (itemQuantity.equalsIgnoreCase("")) {
            MessageBox.box("Item quantity field cannot be empty !");
            return;
        } else if (!itemQuantity.matches("^[0-9]+$") || itemQuantity.equalsIgnoreCase("0")) {
            MessageBox.box("Error ! Check item quantity and try again !");
            return;
        }

        double price = Double.parseDouble(itemPrice);
        int quantity = Integer.parseInt(itemQuantity);

        DataExport.addItemToFile(itemName, itemCode, price, quantity);

        MessageBox.box("Successfully added !");

        addWindow.close();
    }
}
