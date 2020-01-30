package stockcontrolsystemgui;

import java.io.FileInputStream;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import javafx.stage.*;

/**
 * this class has a method which overwrites the inventory file with a default
 * file
 *
 * @author Farhaan Beeharry - M00681483
 */
public class RestoreDefault {

    static Stage restoreWindow;

    /**
     * GUI to confirm restore of data
     *
     * @throws Exception
     */
    public static void restore() throws Exception {

        restoreWindow = new Stage();
        restoreWindow.getIcons().add(new Image("file:images/logo.png"));
        restoreWindow.setTitle("Restore default inventory");
        restoreWindow.setResizable(false);
        restoreWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane restorePane = new GridPane();
        restorePane.setAlignment(Pos.CENTER);
        restorePane.setVgap(30);

        Label restoreMessage = new Label();
        restoreMessage.setText("Are you sure you want to\nrestore default inventory ?");
        restoreMessage.setAlignment(Pos.CENTER);

        ImageView yesIcon = new ImageView(new Image(new FileInputStream("images/yesIcon.png")));
        yesIcon.setFitWidth(25);
        yesIcon.setFitHeight(25);
        Button btnYes = new Button("Yes", yesIcon);
        btnYes.setMinWidth(250);
        btnYes.setFocusTraversable(false);
        btnYes.setOnAction(e -> {
            try {
                restoreDefault();
                restoreWindow.close();
                MessageBox.box("Restore default successful !");
            } catch (Exception ex) {
            }
        });

        ImageView noIcon = new ImageView(new Image(new FileInputStream("images/noIcon.png")));
        noIcon.setFitWidth(25);
        noIcon.setFitHeight(25);
        Button btnNo = new Button("No", noIcon);
        btnNo.setMinWidth(250);
        btnNo.setFocusTraversable(false);
        btnNo.setOnAction(e -> restoreWindow.close());

        restorePane.add(restoreMessage, 0, 0);
        restorePane.add(btnYes, 0, 1);
        restorePane.add(btnNo, 1, 1);

        Scene scene = new Scene(restorePane, 600, 150);
        scene.getStylesheets().add("file:stylesheet/stylesheetMessageBox.css");

        restoreWindow.setScene(scene);
        restoreWindow.showAndWait();

    }

    /**
     * reads the default file into an array overwrites the inventory file with
     * the default array
     *
     * @throws Exception
     */
    public static void restoreDefault() throws Exception {

        Items[] inventory = DataImport.importDefaultInventory();

        DataExport.updateFile(inventory);
    }

}
