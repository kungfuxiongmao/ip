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
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import panda.ui.Ui;

/**
 * Controls Panda's main graphical user interface.
 */
public class MainWindow extends AnchorPane {
    private static final String BACKGROUND_STYLE_BAMBOO = "background-bamboo";
    private static final String BACKGROUND_STYLE_MOUNTAIN = "background-mountain";
    private static final double SCROLL_SPEED_FACTOR = 1.4;

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
        dialogContainer.heightProperty().addListener((_, _, _) ->
                Platform.runLater(() -> scrollPane.setVvalue(scrollPane.getVmax())));
        scrollPane.addEventFilter(ScrollEvent.SCROLL, this::scrollConversation);
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
     * Scrolls the conversation using a slightly amplified mouse-wheel or trackpad delta.
     *
     * @param event Scroll input received over the conversation.
     */
    private void scrollConversation(ScrollEvent event) {
        double scrollableHeight = dialogContainer.getHeight()
                - scrollPane.getViewportBounds().getHeight();
        if (scrollableHeight <= 0 || event.getDeltaY() == 0) {
            return;
        }

        double valueRange = scrollPane.getVmax() - scrollPane.getVmin();
        double valueDelta = event.getDeltaY() * SCROLL_SPEED_FACTOR * valueRange
                / scrollableHeight;
        double newValue = Math.clamp(
                scrollPane.getVvalue() - valueDelta,
                scrollPane.getVmin(),
                scrollPane.getVmax());
        scrollPane.setVvalue(newValue);
        event.consume();
    }

    /**
     * Reads Panda's response stream without blocking the JavaFX application thread.
     *
     * @param pandaResponseReader Reader connected to Panda's response stream.
     */
    private void startListeningForPandaMessages(BufferedReader pandaResponseReader) {
        Thread.ofVirtual().name("panda-response-listener").start(() -> {
            try {
                Ui.UiMessage message;
                while ((message = Ui.readNextUiMessage(pandaResponseReader)) != null) {
                    Ui.UiMessage messageToDisplay = message;
                    Platform.runLater(() -> displayPandaMessage(messageToDisplay));
                }
            } catch (IOException exception) {
                Ui.UiMessage errorMessage = new Ui.UiMessage(
                        Ui.MessageType.APPLICATION_ERROR,
                        "Master Shifu is offline: " + exception.getMessage());
                Platform.runLater(() -> displayPandaMessage(errorMessage));
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
     * @param message Typed response to display.
     */
    private void displayPandaMessage(Ui.UiMessage message) {
        DialogBox dialogBox = DialogBox.createPandaDialog(message, botImage);
        dialogContainer.getChildren().add(dialogBox);
    }
}
