package gui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import panda.Panda;

/**
 * Controls Panda's main graphical user interface.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = loadImage("/images/DaUser.png");
    private final Image pandaImage = loadImage("/images/DaPanda.png");

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Panda panda;

    /**
     * Binds the scroll position to the height of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Supplies the Panda instance that handles user input.
     *
     * @param panda Panda instance used by this window.
     */
    public void setPanda(Panda panda) {
        this.panda = Objects.requireNonNull(panda);
    }

    /**
     * Loads an image resource from the application classpath.
     *
     * @param resourcePath Absolute classpath location of the image.
     * @return Loaded image.
     */
    private static Image loadImage(String resourcePath) {
        return new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream(resourcePath)));
    }

    /**
     * Adds the user's message and Panda's response to the dialog container.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = panda.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPandaDialog(response, pandaImage));
        userInput.clear();
    }
}
