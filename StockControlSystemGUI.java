package stockcontrolsystemgui;

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 * This application is a stock control system it allows user to input items,
 * change stock, delete items, view inventory and more
 *
 * @author Farhaan Beeharry - M00681483
 */
public class StockControlSystemGUI extends Application {

    /**
     * Launches the application
     *
     * @param args
     */
    public static void main(String[] args) {

        launch(args);
    }

    /**
     * Contains GUI of the main menu
     *
     * @param primaryStage which is the stage of the main menu
     * @throws Exception in case of errors, it catches the error and prevents
     * crashes
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane mainMenu = new GridPane();
        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.setVgap(30);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        Label programTitle = new Label();
        programTitle.setText("Stock Control System");
        vbox.getChildren().add(programTitle);

        ImageView addIcon = new ImageView(new Image(new FileInputStream("images/addIcon.png")));
        addIcon.setFitWidth(25);
        addIcon.setFitHeight(25);
        Button btnAdd = new Button("Add new item", addIcon);
        btnAdd.setAlignment(Pos.BASELINE_LEFT);
        btnAdd.setMaxWidth(300);
        btnAdd.setFocusTraversable(false);
        btnAdd.setOnAction(e -> {
            try {
                AddItem.addItem();
            } catch (Exception ex) {
            }
        });

        ImageView changeIcon = new ImageView(new Image(new FileInputStream("images/changeIcon.png")));
        changeIcon.setFitWidth(25);
        changeIcon.setFitHeight(25);
        Button btnChange = new Button("Change stock quantity", changeIcon);
        btnChange.setAlignment(Pos.BASELINE_LEFT);
        btnChange.setMaxWidth(300);
        btnChange.setFocusTraversable(false);
        btnChange.setOnAction(e -> {
            try {
                ChangeStock.changeStock();
            } catch (Exception ex) {
            }
        });

        ImageView removeIcon = new ImageView(new Image(new FileInputStream("images/removeIcon.png")));
        removeIcon.setFitWidth(25);
        removeIcon.setFitHeight(25);
        Button btnRemove = new Button("Remove item", removeIcon);
        btnRemove.setAlignment(Pos.BASELINE_LEFT);
        btnRemove.setMaxWidth(300);
        btnRemove.setFocusTraversable(false);
        btnRemove.setOnAction(e -> {
            try {
                RemoveItem.removeItem();
            } catch (Exception ex) {
            }
        });

        ImageView showIcon = new ImageView(new Image(new FileInputStream("images/showIcon.png")));
        showIcon.setFitWidth(25);
        showIcon.setFitHeight(25);
        Button btnShowAll = new Button("Show all items", showIcon);
        btnShowAll.setAlignment(Pos.BASELINE_LEFT);
        btnShowAll.setMaxWidth(300);
        btnShowAll.setFocusTraversable(false);
        btnShowAll.setOnAction(e -> {
            try {
                ViewAllItems.choice();
            } catch (Exception ex) {
            }
        });

        ImageView searchIcon = new ImageView(new Image(new FileInputStream("images/searchIcon.png")));
        searchIcon.setFitWidth(25);
        searchIcon.setFitHeight(25);
        Button btnSearch = new Button("Search for an item", searchIcon);
        btnSearch.setAlignment(Pos.BASELINE_LEFT);
        btnSearch.setMaxWidth(300);
        btnSearch.setFocusTraversable(false);
        btnSearch.setOnAction(e -> {
            try {
                Search.choice();
            } catch (Exception ex) {
            }
        });

        ImageView restoreIcon = new ImageView(new Image(new FileInputStream("images/restoreIcon.png")));
        restoreIcon.setFitWidth(25);
        restoreIcon.setFitHeight(25);
        Button restoreDefault = new Button("Restore default inventory", restoreIcon);
        restoreDefault.setAlignment(Pos.BASELINE_LEFT);
        restoreDefault.setMinWidth(300);
        restoreDefault.setFocusTraversable(false);
        restoreDefault.setOnAction(e -> {
            try {
                RestoreDefault.restore();
            } catch (Exception ex) {
            }
        });

        ImageView exitIcon = new ImageView(new Image(new FileInputStream("images/exitIcon.png")));
        exitIcon.setFitWidth(25);
        exitIcon.setFitHeight(25);
        Button btnExit = new Button("Exit", exitIcon);
        btnExit.setAlignment(Pos.BASELINE_LEFT);
        btnExit.setMaxWidth(300);
        btnExit.setFocusTraversable(false);
        btnExit.setOnAction(e -> {
            try {
                Exit.exit();
            } catch (Exception ex) {
            }
        });

        Text about = new Text("    ~ Farhaan Beeharry ~ M00681483 ~");
        about.setFont(Font.font("Courier New", 12));

        mainMenu.getChildren().add(vbox);
        mainMenu.add(btnAdd, 0, 1);
        mainMenu.add(btnChange, 0, 2);
        mainMenu.add(btnRemove, 0, 3);
        mainMenu.add(btnShowAll, 0, 4);
        mainMenu.add(btnSearch, 0, 5);
        mainMenu.add(restoreDefault, 0, 6);
        mainMenu.add(btnExit, 0, 8);
        mainMenu.add(about, 0, 10);

        Scene scene = new Scene(mainMenu);
        scene.getStylesheets().add("file:stylesheet/stylesheet.css");

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Stock Control System");
        primaryStage.setOnCloseRequest(e -> {
            try {
                e.consume();
                Exit.exit();

            } catch (Exception ex) {
            }
        });
        primaryStage.getIcons().add(new Image("file:images/logo.png"));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
