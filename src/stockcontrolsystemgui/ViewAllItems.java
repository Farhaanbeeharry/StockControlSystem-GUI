package stockcontrolsystemgui;

import java.io.FileInputStream;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.layout.VBox;
import javafx.stage.*;

/**
 * This class contains methods which will display all the data in the .txt files
 * in a table It has a method which allows the user to choose how he/she wants
 * the data to be sorted
 * 
 * @author Farhaan Beeharry - M00681483
 */
public class ViewAllItems {

    static Stage choiceWindow, tableWindow;
    static TableView tableItems;

    /**
     * This method takes the user's choice to sort the data according to the
     * user's wish Then it adds the data to the table
     *
     * @param choice user's choice of sorting
     * @throws java.lang.Exception in case of errors, it catches the error and
     * prevents crashes
     */
    public static void setToTable(int choice) throws Exception {

        Items[] inventory = DataImport.importInventory();

        switch (choice) {
            case 1:
                inventory = Sort.itemCode();
                break;
            case 2:
                inventory = Sort.itemName();
                break;
            case 3:
                inventory = Sort.itemPrice();
                break;
            default:
                break;
        }

        for (int i = 0; i < inventory.length; i++) {
            tableItems.getItems().add(new Items(inventory[i].getItemCode(),
                    inventory[i].getItemName(),
                    inventory[i].getQuantity(),
                    inventory[i].getItemPrice()));
        }

    }

    /**
     * This method contains 3 buttons @itemName, @itemCode, @price which the
     * user can choose Then it will send the data to the method setToTable When
     * sorted by price, if prices are similar, it will then sort by item name
     *
     * @throws java.lang.Exception in case of errors, it catches the error and
     * prevents crashes
     */
    public static void choice() throws Exception {

        choiceWindow = new Stage();
        choiceWindow.getIcons().add(new Image("file:images/logo.png"));
        choiceWindow.setTitle("Viewing all items - sort by");
        choiceWindow.setResizable(false);
        choiceWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane viewAll = new GridPane();
        viewAll.setAlignment(Pos.CENTER);
        viewAll.setVgap(30);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        Label programTitle = new Label();
        programTitle.setText("Viewing all items ...");
        vbox.getChildren().add(programTitle);

        Label sortBy = new Label();
        sortBy.setText("\n\nChoose sorting method :");
        vbox.getChildren().add(sortBy);

        ImageView codeIcon = new ImageView(new Image(new FileInputStream("images/codeIcon.png")));
        codeIcon.setFitWidth(25);
        codeIcon.setFitHeight(25);
        Button btnItemCode = new Button("Item code", codeIcon);
        btnItemCode.setAlignment(Pos.BASELINE_LEFT);
        btnItemCode.setMaxWidth(250);
        btnItemCode.setFocusTraversable(false);
        btnItemCode.setOnAction(e -> {
            try {
                ViewAllItems.table(1);
            } catch (Exception ex) {
            }
        });

        ImageView nameIcon = new ImageView(new Image(new FileInputStream("images/nameIcon.png")));
        nameIcon.setFitWidth(25);
        nameIcon.setFitHeight(25);
        Button btnItemName = new Button("Item name", nameIcon);
        btnItemName.setAlignment(Pos.BASELINE_LEFT);
        btnItemName.setMaxWidth(250);
        btnItemName.setFocusTraversable(false);
        btnItemName.setOnAction(e -> {
            try {
                ViewAllItems.table(2);
            } catch (Exception ex) {
            }
        });

        ImageView priceIcon = new ImageView(new Image(new FileInputStream("images/priceIcon.png")));
        priceIcon.setFitWidth(25);
        priceIcon.setFitHeight(25);
        Button btnItemPrice = new Button("Item price", priceIcon);
        btnItemPrice.setAlignment(Pos.BASELINE_LEFT);
        btnItemPrice.setMaxWidth(250);
        btnItemPrice.setFocusTraversable(false);
        btnItemPrice.setOnAction(e -> {
            try {
                ViewAllItems.table(3);
            } catch (Exception ex) {
            }
        });

        ImageView backIcon = new ImageView(new Image(new FileInputStream("images/backIcon.png")));
        backIcon.setFitWidth(25);
        backIcon.setFitHeight(25);
        Button btnBack = new Button("Back", backIcon);
        btnBack.setText("Back");
        btnBack.setAlignment(Pos.BASELINE_LEFT);
        btnBack.setMaxWidth(250);
        btnBack.setFocusTraversable(false);
        btnBack.setOnAction(e -> choiceWindow.close());

        viewAll.getChildren().add(vbox);

        viewAll.add(btnItemCode, 0, 1);
        viewAll.add(btnItemName, 0, 2);
        viewAll.add(btnItemPrice, 0, 3);
        viewAll.add(btnBack, 0, 5);

        Scene scene = new Scene(viewAll);
        scene.getStylesheets().add("file:stylesheet/stylesheet.css");

        choiceWindow.setScene(scene);
        choiceWindow.setMaximized(true);
        choiceWindow.showAndWait();

    }

    /**
     * Creates the table with the columns names and sizes
     *
     * @param choice the user's choice of sorting
     * @throws java.lang.Exception in case of errors, it catches the error and
     * prevents crashes
     */
    public static void table(int choice) throws Exception {
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

            setToTable(choice);

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

}
