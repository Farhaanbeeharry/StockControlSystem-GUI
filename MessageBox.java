package stockcontrolsystemgui;

import java.io.FileInputStream;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import javafx.stage.*;

/**
 * this class contains a simple GUI to inform user about actions
 *
 * @author Farhaan Beeharry - M00681483
 */
public class MessageBox {

    static Stage messageWindow;

    /**
     * A message box to inform user about actions taken
     *
     * @param message the message to be displayed is sent from the methods which
     * needs the box to appear
     * @throws Exception
     */
    public static void box(String message) throws Exception {

        messageWindow = new Stage();
        messageWindow.getIcons().add(new Image("file:images/logo.png"));
        messageWindow.setTitle("Message");
        messageWindow.setResizable(false);
        messageWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane messagePane = new GridPane();
        messagePane.setAlignment(Pos.CENTER);
        messagePane.setVgap(30);

        Label displayMessage = new Label();
        displayMessage.setText(message);
        displayMessage.setAlignment(Pos.CENTER);

        ImageView OKIcon = new ImageView(new Image(new FileInputStream("images/OKIcon.png")));
        OKIcon.setFitWidth(25);
        OKIcon.setFitHeight(25);
        Button btnOK = new Button("OK", OKIcon);
        btnOK.setMinWidth(250);
        btnOK.setFocusTraversable(false);
        btnOK.setOnAction(e -> messageWindow.close());

        messagePane.add(displayMessage, 0, 0);
        messagePane.add(btnOK, 0, 1);

        Scene scene = new Scene(messagePane, 600, 150);
        scene.getStylesheets().add("file:stylesheet/stylesheetMessageBox.css");

        messageWindow.setScene(scene);
        messageWindow.showAndWait();

    }
}
