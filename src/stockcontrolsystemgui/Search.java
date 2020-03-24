package stockcontrolsystemgui;

import java.io.FileInputStream;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.*;

/**
 * This class contains a search GUI and the searching methods
 *
 * @author Farhaan Beeharry - M00681483
 */
public class Search {

    static TableView tableItems;
    static Stage searchWindow, resultWindow, tableWindow;
    static TextField inputCriteria, itemNameField, itemCodeField, itemPriceField, itemQuantityField;

    /**
     * this method is a GUI which allows user to choose searching options
     *
     * @throws Exception in case of errors, it prevents crashes
     */
    public static void choice() throws Exception {
        searchWindow = new Stage();
        searchWindow.getIcons().add(new Image("file:images/logo.png"));
        searchWindow.setTitle("Search for an item");
        searchWindow.setResizable(false);
        searchWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane searchItem = new GridPane();
        searchItem.setAlignment(Pos.CENTER);
        searchItem.setVgap(30);

        Label searchLabel = new Label();
        searchLabel.setText("Search for an item");
        searchLabel.setAlignment(Pos.CENTER);
        searchLabel.setPrefWidth(Double.MAX_VALUE);

        Text searchText = new Text("Search by :");
        final ToggleGroup searchBy = new ToggleGroup();
        RadioButton choiceCode = new RadioButton("Code");
        choiceCode.setToggleGroup(searchBy);
        choiceCode.setSelected(true);
        RadioButton choiceName = new RadioButton("Name");
        choiceName.setToggleGroup(searchBy);
        RadioButton choicePrice = new RadioButton("Price");
        choicePrice.setToggleGroup(searchBy);

        Text inputCriteriaText = new Text("Input criteria here :");
        inputCriteria = new TextField();
        inputCriteria.setPromptText("input criteria here");
        inputCriteria.setFocusTraversable(false);

        ImageView searchIcon = new ImageView(new Image(new FileInputStream("images/searchIcon.png")));
        searchIcon.setFitWidth(25);
        searchIcon.setFitHeight(25);
        Button btnSearch = new Button("Search", searchIcon);
        btnSearch.setMinWidth(250);
        btnSearch.setFocusTraversable(false);
        btnSearch.setOnAction(e -> {
            try {
                if (inputCriteria.getText().equalsIgnoreCase("")) {
                    MessageBox.box("Criteria field cannot be empty !");
                } else if (choiceCode.isSelected()) {
                    itemCode();
                } else if (choiceName.isSelected()) {
                    itemName();
                } else if (choicePrice.isSelected()) {
                    itemPrice();
                }

            } catch (Exception ex) {
            }
        });

        ImageView cancelIcon = new ImageView(new Image(new FileInputStream("images/cancelIcon.png")));
        cancelIcon.setFitWidth(25);
        cancelIcon.setFitHeight(25);
        Button btnCancel = new Button("Cancel", cancelIcon);
        btnCancel.setMinWidth(250);
        btnCancel.setFocusTraversable(false);
        btnCancel.setOnAction(e -> searchWindow.close());

        HBox Hbtns = new HBox(40);
        Hbtns.getChildren().addAll(btnSearch, btnCancel);
        Hbtns.setAlignment(Pos.CENTER);

        searchItem.add(searchLabel, 0, 0, 5, 1);
        searchItem.add(searchText, 0, 2);
        searchItem.add(choiceCode, 2, 2);
        searchItem.add(choiceName, 3, 2);
        searchItem.add(choicePrice, 4, 2);
        searchItem.add(inputCriteriaText, 0, 5);
        searchItem.add(inputCriteria, 1, 5, 4, 1);
        searchItem.add(Hbtns, 0, 7, 5, 1);
        searchItem.setPadding(new Insets(10, 250, 10, 250));
        searchItem.setHgap(50);
        Scene scene = new Scene(searchItem);
        scene.getStylesheets().add("file:stylesheet/stylesheet.css");

        searchWindow.setScene(scene);
        searchWindow.setMaximized(true);
        searchWindow.showAndWait();

    }

    /**
     * contains searching algorithms for itemCode
     *
     * @throws Exception prevents crashes in case of errors
     */
    public static void itemCode() throws Exception {
        Items[] inventory = DataImport.importInventory();

        int searchResult = -1;
        for (int x = 0; x < DataImport.count(); x++) {
            if (inventory[x].getItemCode().equalsIgnoreCase(inputCriteria.getText())) {
                searchResult = x;
            }
        }

        if (searchResult == -1) {
            MessageBox.box("No result found !");
        } else {
            result(searchResult);
        }

    }

    /**
     * contains searching algorithms for itemName
     *
     * @throws Exception prevents crashes in case of errors
     */
    public static void itemName() throws Exception {

        Items[] inventory = DataImport.importInventory();

        int searchResult = -1;
        for (int x = 0; x < DataImport.count(); x++) {
            if (inventory[x].getItemName().equalsIgnoreCase(inputCriteria.getText())) {
                searchResult = x;
            }
        }

        if (searchResult == -1) {
            MessageBox.box("No result found !");
        } else {
            result(searchResult);
        }

    }

    /**
     * contains searching algorithms for itemPrice
     *
     * @throws Exception prevents crashes in case of errors
     */
    public static void itemPrice() throws Exception {

        Items[] inventory = DataImport.importInventory();

        if (!inputCriteria.getText().matches("^[0-9.]+$") || inputCriteria.getText().equalsIgnoreCase("0")) {
            MessageBox.box("Invalid criteria input ! Try again !");
            return;
        }

        tableWindow = new Stage();
        tableWindow.getIcons().add(new Image("file:images/logo.png"));
        tableWindow.setResizable(false);
        tableWindow.setTitle("Table");

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double columnWidth = (screenWidth - 4) / 4;

        try {
            TableColumn<Items, String> codeColumns = new TableColumn<>("Item Code");
            codeColumns.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            codeColumns.setMinWidth(columnWidth);
            codeColumns.setResizable(false);
            codeColumns.setSortable(false);

            TableColumn<Items, String> nameColumns = new TableColumn<>("Name");
            nameColumns.setCellValueFactory(new PropertyValueFactory<>("itemName"));
            nameColumns.setMinWidth(columnWidth);
            nameColumns.setSortable(false);
            nameColumns.setResizable(false);

            TableColumn<Items, Integer> quantityColumns = new TableColumn<>("Quantity");
            quantityColumns.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            quantityColumns.setMinWidth(columnWidth);
            quantityColumns.setSortable(false);
            quantityColumns.setResizable(false);

            TableColumn<Items, Double> priceColumns = new TableColumn<>("Price (Rs)");
            priceColumns.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
            priceColumns.setMinWidth(columnWidth);
            priceColumns.setSortable(false);
            priceColumns.setResizable(false);

            tableItems = new TableView<>();
            tableItems.setFocusTraversable(false);
            tableItems.setEditable(false);
            tableItems.prefHeightProperty().bind(tableWindow.heightProperty());
            tableItems.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            for (int i = 0; i < DataImport.count(); i++) {
                if (inventory[i].getItemPrice() == Double.parseDouble(inputCriteria.getText())) {

                    tableItems.getItems().add(new Items(inventory[i].getItemCode(),
                            inventory[i].getItemName(),
                            inventory[i].getQuantity(),
                            inventory[i].getItemPrice()));

                }
            }

            tableItems.getColumns().addAll(codeColumns, nameColumns, quantityColumns, priceColumns);
        } catch (Exception e) {
        }

        ImageView backIcon = new ImageView(new Image(new FileInputStream("images/backIcon.png")));
        backIcon.setFitWidth(25);
        backIcon.setFitHeight(25);
        Button btnBack = new Button("Back", backIcon);
        btnBack.setPadding(new Insets(10, 10, 10, 10));
        btnBack.setMinWidth(250);
        btnBack.setFocusTraversable(false);
        btnBack.setOnAction(e -> tableWindow.close());

        VBox vbox = new VBox();
        vbox.getChildren().addAll(tableItems, btnBack);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        BorderPane bp = new BorderPane();
        bp.setCenter(vbox);

        Scene scene = new Scene(bp);
        scene.getStylesheets().add("file:stylesheet/stylesheet.css");

        tableWindow.setScene(scene);
        tableWindow.setMaximized(true);
        tableWindow.show();
    }

    /**
     * this method displays the search result if any
     *
     * @param searchResult it is the index at which the search result is present
     * in the array
     * @throws Exception
     */
    public static void result(int searchResult) throws Exception {

        Items[] inventory = DataImport.importInventory();

        resultWindow = new Stage();
        resultWindow.getIcons().add(new Image("file:images/logo.png"));
        resultWindow.setTitle("Search result");
        resultWindow.setResizable(false);
        resultWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane confirmPane = new GridPane();
        confirmPane.setAlignment(Pos.CENTER);
        confirmPane.setVgap(30);

        Label searchResultLabel = new Label();
        searchResultLabel.setText("Search Result");
        searchResultLabel.setAlignment(Pos.CENTER);
        searchResultLabel.setPrefWidth(Double.MAX_VALUE);

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

        ImageView mainMenuIcon = new ImageView(new Image(new FileInputStream("images/mainMenuIcon.png")));
        mainMenuIcon.setFitWidth(25);
        mainMenuIcon.setFitHeight(25);
        Button btnMainMenu = new Button("Go to main menu", mainMenuIcon);
        btnMainMenu.setMinWidth(250);
        btnMainMenu.setFocusTraversable(false);
        btnMainMenu.setOnAction(e -> {
            try {
                searchWindow.close();
                resultWindow.close();
            } catch (Exception ex) {
            }
        });

        ImageView searchAgainIcon = new ImageView(new Image(new FileInputStream("images/searchAgainIcon.png")));
        searchAgainIcon.setFitWidth(25);
        searchAgainIcon.setFitHeight(25);
        Button btnAnotherSearch = new Button("Search again", searchAgainIcon);
        btnAnotherSearch.setMinWidth(250);
        btnAnotherSearch.setFocusTraversable(false);
        btnAnotherSearch.setOnAction(e -> {
            try {
                resultWindow.close();
            } catch (Exception ex) {
            }
        });

        HBox Hbtns = new HBox(40);
        Hbtns.getChildren().addAll(btnMainMenu, btnAnotherSearch);
        Hbtns.setAlignment(Pos.CENTER);

        confirmPane.add(searchResultLabel, 0, 0, 2, 1);
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

        resultWindow.setScene(scene);
        resultWindow.setMaximized(true);
        resultWindow.showAndWait();
    }
}
