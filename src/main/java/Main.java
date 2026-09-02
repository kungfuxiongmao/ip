import java.io.IOException;

import gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import panda.Panda;

/**
 * Starts Panda's JavaFX graphical user interface.
 */
public class Main extends Application {
    private final Panda panda = new Panda();

    /**
     * Loads and displays Panda's main window.
     *
     * @param stage Primary JavaFX stage.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPanda(panda);
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
