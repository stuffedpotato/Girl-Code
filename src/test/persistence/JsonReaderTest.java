package persistence;

import org.junit.jupiter.api.Test;

import model.PeriodEntry;
import model.PeriodLog;

import java.time.LocalDate;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Referenced from JSONSerializationDemo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */
class JsonReaderTest extends JsonTest {
    private JsonReader reader;
    private PeriodLog log;

    @Test
    void testReaderNonExistentFile() {
        reader = new JsonReader("./data/noSuchFile.json");

        try {
            log = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyLog() {
        reader = new JsonReader("./data/testReaderEmptyLog.json");

        try {
            log = reader.read();

            assertEquals(LocalDate.parse("2024-10-11"), log.getDate());
            assertEquals(0, log.getNumEntries());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLog() {
        reader = new JsonReader("./data/testReaderGeneralLog.json");

        try {
            log = reader.read();

            assertEquals(LocalDate.parse("2024-10-11"), log.getDate());
            List<PeriodEntry> entries = log.getLog();
            assertEquals(2, entries.size());

            PeriodEntry entry1 = entries.get(0);
            PeriodEntry entry2 = entries.get(1);

            checkEntry(entry1, entry2);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
