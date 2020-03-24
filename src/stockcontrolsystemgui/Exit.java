package stockcontrolsystemgui;

import java.io.FileInputStream;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import javafx.stage.*;

/**
 * Contains a simple GUI to ask user if he/she is sure about exiting the
 * application
 *
 * @author Farhaan Beeharry - M00681483
 */
public class Exit {

    static Stage exitWindow;

    /**
     * the GUI which contains a 'YES' and a 'NO' button
     *
     * @throws Exception
     */
    public static void exit() throws Exception {

        exitWindow = new Stage();
        exitWindow.getIcons().add(new Image("file:images/logo.png"));
        exitWindow.setTitle("Exit");
        exitWindow.setResizable(false);
        exitWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane exitPane = new GridPane();
        exitPane.setAlignment(Pos.CENTER);
        exitPane.setVgap(30);

        Label exitMessage = new Label();
        exitMessage.setText("Are you sure you want to exit ?");
        exitMessage.setAlignment(Pos.CENTER);

        ImageView yesIcon = new ImageView(new Image(new FileInputStream("images/yesIcon.png")));
        yesIcon.setFitWidth(25);
        yesIcon.setFitHeight(25);
        Button btnYes = new Button("Yes", yesIcon);
        btnYes.setMinWidth(250);
        btnYes.setFocusTraversable(false);
        btnYes.setOnAction(e -> System.exit(0));

        ImageView noIcon = new ImageView(new Image(new FileInputStream("images/noIcon.png")));
        noIcon.setFitWidth(25);
        noIcon.setFitHeight(25);
        Button btnNo = new Button("No", noIcon);
        btnNo.setMinWidth(250);
        btnNo.setFocusTraversable(false);
        btnNo.setOnAction(e -> exitWindow.close());

        exitPane.add(exitMessage, 0, 0);
        exitPane.add(btnYes, 0, 1);
        exitPane.add(btnNo, 1, 1);

        Scene scene = new Scene(exitPane, 600, 150);
        scene.getStylesheets().add("file:stylesheet/stylesheetMessageBox.css");

        exitWindow.setScene(scene);
        exitWindow.showAndWait();

    }

}
