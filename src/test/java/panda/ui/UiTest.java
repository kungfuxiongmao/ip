package panda.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class UiTest {

    @AfterEach
    void resetOutput() {
        Ui.directOutputTo(System.out);
    }

    @Test
    void typedMessages_allMessageTypes_preservesTypesAndText() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Ui.directTypedOutputTo(outputStream);

        Ui.printMessage("Task added.");
        Ui.printParsingError("Unknown command.");
        Ui.printApplicationError("Unable to save tasks.");

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(outputStream.toByteArray()), StandardCharsets.UTF_8));
        assertEquals(new Ui.UiMessage(Ui.MessageType.NORMAL, "Task added."),
                Ui.readNextUiMessage(inputReader));
        assertEquals(new Ui.UiMessage(Ui.MessageType.PARSING_ERROR, "Unknown command."),
                Ui.readNextUiMessage(inputReader));
        assertEquals(new Ui.UiMessage(Ui.MessageType.APPLICATION_ERROR, "Unable to save tasks."),
                Ui.readNextUiMessage(inputReader));
        assertNull(Ui.readNextUiMessage(inputReader));
    }
}
