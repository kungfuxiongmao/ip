package gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

/**
 * Represents a dialog box containing a speaker image and message text.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load the dialog box layout.", exception);
        }

        dialog.setText(text);
        displayPicture.setImage(image);
    }

    /**
     * Creates a dialog box for a message from the user.
     *
     * @param text Message to display.
     * @param image User image to display.
     * @return Dialog box for the user's message.
     */
    public static DialogBox getUserDialog(String text, Image image) {
        DialogBox dialogBox = new DialogBox(text, image);
        dialogBox.alignMessageWithUserImage();
        return dialogBox;
    }

    /**
     * Creates a dialog box for a response from Panda.
     *
     * @param text Message to display.
     * @param image Panda image to display.
     * @return Dialog box for Panda's response.
     */
    public static DialogBox getPandaDialog(String text, Image image) {
        DialogBox dialogBox = new DialogBox(text, image);
        dialogBox.flip();
        dialogBox.alignMessageWithPandaImage();
        return dialogBox;
    }

    /**
     * Right-aligns a user message beside the user image.
     */
    private void alignMessageWithUserImage() {
        dialog.setAlignment(Pos.TOP_RIGHT);
        dialog.setTextAlignment(TextAlignment.RIGHT);
    }

    /**
     * Left-aligns a Panda message beside the Panda image.
     */
    private void alignMessageWithPandaImage() {
        dialog.setAlignment(Pos.TOP_LEFT);
        dialog.setTextAlignment(TextAlignment.LEFT);
    }

    /**
     * Places the speaker image on the left and the message on the right.
     */
    private void flip() {
        ObservableList<Node> children = FXCollections.observableArrayList(getChildren());
        Collections.reverse(children);
        getChildren().setAll(children);
        setAlignment(Pos.TOP_LEFT);
    }
}
