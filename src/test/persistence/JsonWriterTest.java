package persistence;

import org.junit.jupiter.api.Test;

import model.PeriodEntry;
import model.PeriodLog;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

/*
 * Referenced from JSONSerializationDemo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */
public class JsonWriterTest extends JsonTest {
    private JsonReader reader;
    private JsonWriter writer;
    private PeriodLog log;

    @Test
    void testWriterInvalidFile() {
        try {
            log = new PeriodLog(LocalDate.now());
            writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyLog() {
        try {
            log = new PeriodLog(LocalDate.now());
            writer = new JsonWriter("./data/testWriterEmptyLog.json");
            writer.open();
            writer.write(log);
            writer.close();

            reader = new JsonReader("./data/testWriterEmptyLog.json");
            log = reader.read();
            assertEquals(LocalDate.now(), log.getDate());
            assertEquals(0, log.getNumEntries());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLog() {
        try {
            log = new PeriodLog(LocalDate.now());
            PeriodEntry entry1 = new PeriodEntry(LocalDate.of(2024, 10, 14));
            PeriodEntry entry2 = new PeriodEntry(LocalDate.of(2024, 10, 11));
            logEntry(entry1, entry2);

            log.addEntry(entry1);
            log.addEntry(entry2);

            writer = new JsonWriter("./data/testWriterGeneralLog.json");
            writer.open();
            writer.write(log);
            writer.close();

            reader = new JsonReader("./data/testWriterGeneralLog.json");
            log = reader.read();
            assertEquals(LocalDate.now(), log.getDate());
            List<PeriodEntry> entries = log.getLog();
            assertEquals(2, entries.size());

            checkEntry(entry1, entry2);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    /*
     * EFFECTS: helper method - logs data fields for entry1 and entry2
     */
    private void logEntry(PeriodEntry entry1, PeriodEntry entry2) {
        entry1.logHeaviness(0);
        entry1.logCollectionMethod("Tampons", 2);
        entry1.logFeelings("Sensitive");
        entry1.logFeelings("Anxious");
        entry1.logBreastHealth("Lumpy");

        entry2.logHeaviness(1);
        entry2.logCollectionMethod("Pads", 3);
        entry2.logPain("Back");
        entry2.logPain("Breasts");
    }
}
