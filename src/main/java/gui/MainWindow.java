package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import panda.ui.Ui;

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

    private PrintWriter pandaCommandWriter;

    /**
     * Binds the scroll position to the height of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Connects this window to Panda's command and response streams.
     *
     * @param commandOutputStream Stream that sends commands to Panda.
     * @param responseInputStream Stream that receives responses from Panda.
     */
    public void connectToPandaStreams(
            OutputStream commandOutputStream, InputStream responseInputStream) {
        pandaCommandWriter = new PrintWriter(
                Objects.requireNonNull(commandOutputStream), true, StandardCharsets.UTF_8);
        BufferedReader pandaResponseReader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(responseInputStream), StandardCharsets.UTF_8));
        startListeningForPandaMessages(pandaResponseReader);
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
     * Reads Panda's response stream without blocking the JavaFX application thread.
     *
     * @param pandaResponseReader Reader connected to Panda's response stream.
     */
    private void startListeningForPandaMessages(BufferedReader pandaResponseReader) {
        Thread.ofVirtual().name("panda-response-listener").start(() -> {
            try {
                String message;
                while ((message = Ui.readNextMessage(pandaResponseReader)) != null) {
                    String messageToDisplay = message;
                    Platform.runLater(() -> displayPandaMessage(messageToDisplay));
                }
            } catch (IOException exception) {
                Platform.runLater(() -> displayPandaMessage(
                        "Panda's response stream closed unexpectedly: " + exception.getMessage()));
            }
        });
    }

    /**
     * Sends the text field's contents to Panda and displays the user's message.
     */
    @FXML
    private void sendUserCommand() {
        String command = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(command, userImage));
        pandaCommandWriter.println(command);
        userInput.clear();
    }

    /**
     * Adds one response from Panda to the dialog container.
     *
     * @param message Response to display.
     */
    private void displayPandaMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getPandaDialog(message, pandaImage));
    }
}
