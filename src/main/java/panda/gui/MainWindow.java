package panda.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
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
    private static final String BACKGROUND_STYLE_BAMBOO = "background-bamboo";
    private static final String BACKGROUND_STYLE_MOUNTAIN = "background-mountain";

    private final Image userImage = loadImage("/images/DaUser.png");
    private final Image botImage = loadImage("/images/DaMaster.png");

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    private PrintWriter pandaCommandWriter;

    /**
     * Enables manual scrolling and scrolls to the latest message when the dialog grows.
     */
    @FXML
    public void initialize() {
        assert scrollPane != null : "FXML must inject scrollPane";
        assert dialogContainer != null : "FXML must inject dialogContainer";
        assert userInput != null : "FXML must inject userInput";
        dialogContainer.minHeightProperty().bind(Bindings.createDoubleBinding(() ->
                scrollPane.getViewportBounds().getHeight(),
                scrollPane.viewportBoundsProperty()));
        dialogContainer.heightProperty().addListener((observable, oldHeight, newHeight) ->
                Platform.runLater(() -> scrollPane.setVvalue(scrollPane.getVmax())));
        selectRandomBackground();
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
     * Selects either available background with equal probability.
     */
    private void selectRandomBackground() {
        String backgroundStyle = ThreadLocalRandom.current().nextBoolean()
                ? BACKGROUND_STYLE_BAMBOO
                : BACKGROUND_STYLE_MOUNTAIN;
        scrollPane.getStyleClass().add(backgroundStyle);
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
                        "Master Shifu is offline: " + exception.getMessage()));
            }
        });
    }

    /**
     * Sends the text field's contents to Panda and displays the user's message.
     */
    @FXML
    private void sendUserCommand() {
        String command = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.createUserDialog(command, userImage));
        pandaCommandWriter.println(command);
        userInput.clear();
    }

    /**
     * Adds one response from Panda to the dialog container.
     *
     * @param message Response to display.
     */
    private void displayPandaMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.createPandaDialog(message, botImage));
    }
}
