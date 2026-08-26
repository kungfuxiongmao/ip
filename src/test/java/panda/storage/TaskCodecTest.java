package panda.storage;

import org.junit.jupiter.api.Test;

import panda.exception.storage.FileCorruptedException;
import panda.task.Deadline;
import panda.task.Event;
import panda.task.Task;
import panda.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link TaskCodec} using round-trip serialization and corrupted line handling.
 */
public class TaskCodecTest {

    @Test
    public void roundTrip_unmarkedTodoTask_encodesBackToSameString() throws Exception {
        String originalRecord = "T | 0 | read book";
        Task decodedTask = TaskCodec.decode(originalRecord);
        assertInstanceOf(Todo.class, decodedTask);
        String reEncodedRecord = TaskCodec.encode(decodedTask);
        assertEquals(originalRecord, reEncodedRecord);
    }

    @Test
    public void roundTrip_markedTodoTask_encodesBackToSameString() throws Exception {
        String originalRecord = "T | 1 | read book";
        Task decodedTask = TaskCodec.decode(originalRecord);
        assertInstanceOf(Todo.class, decodedTask);
        String reEncodedRecord = TaskCodec.encode(decodedTask);
        assertEquals(originalRecord, reEncodedRecord);
    }

    @Test
    public void roundTrip_unmarkedDeadlineTask_encodesBackToSameString() throws Exception {
        String originalRecord = "D | 0 | return book | 2019-06-06 18:00";
        Task decodedTask = TaskCodec.decode(originalRecord);
        assertInstanceOf(Deadline.class, decodedTask);
        String reEncodedRecord = TaskCodec.encode(decodedTask);
        assertEquals(originalRecord, reEncodedRecord);
    }

    @Test
    public void roundTrip_markedDeadlineTask_encodesBackToSameString() throws Exception {
        String originalRecord = "D | 1 | return book | 2019-06-06";
        Task decodedTask = TaskCodec.decode(originalRecord);
        assertInstanceOf(Deadline.class, decodedTask);
        String reEncodedRecord = TaskCodec.encode(decodedTask);
        assertEquals(originalRecord, reEncodedRecord);
    }

    @Test
    public void roundTrip_markedEventTask_encodesBackToSameString() throws Exception {
        String originalRecord = "E | 1 | project meeting | 2019-08-06 14:00 | 2019-08-06 16:00";
        Task decodedTask = TaskCodec.decode(originalRecord);
        assertInstanceOf(Event.class, decodedTask);
        String reEncodedRecord = TaskCodec.encode(decodedTask);
        assertEquals(originalRecord, reEncodedRecord);
    }

    @Test
    public void roundTrip_unmarkedEventTask_encodesBackToSameString() throws Exception {
        String originalRecord = "E | 0 | orientation camp | 2019-08-06 | 2019-08-08";
        Task decodedTask = TaskCodec.decode(originalRecord);
        assertInstanceOf(Event.class, decodedTask);
        String reEncodedRecord = TaskCodec.encode(decodedTask);
        assertEquals(originalRecord, reEncodedRecord);
    }

    @Test
    public void decode_corruptedRecord_throwsFileCorruptedException() {
        assertThrows(FileCorruptedException.class, () -> TaskCodec.decode("corrupted task record"));
    }
}
