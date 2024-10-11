package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PeriodLogTest {
    private PeriodLog testLog;
    private PeriodEntry testEntry1;
    private PeriodEntry testEntry2;
    private List<PeriodEntry> testList;
    private LocalDate date1;
    private LocalDate date2;
    
    @BeforeEach
    void runBefore() {
        testLog = new PeriodLog();

        date1 = LocalDate.now();
        date2 = LocalDate.of(2024,10,05);

        testEntry1 = new PeriodEntry(date1);
        testEntry2 = new PeriodEntry(date2);
    }

    @Test
    void testConstructor() {
        testList = testLog.getLog();
        assertEquals(0, testList.size());
    }

    @Test
    void testAddEntry() {
        testLog.addEntry(testEntry1);
        testLog.addEntry(testEntry2);
        testList = testLog.getLog();

        //Testing for duplication
        testLog.addEntry(new PeriodEntry(LocalDate.of(2024, 10, 05)));

        assertEquals(2, testList.size());
        assertEquals(testEntry1, testLog.getEntry(date1));
        assertEquals(testEntry2, testLog.getEntry(date2));
    }

    @Test
    void testClearLog() {
        testLog.addEntry(testEntry1);
        testLog.addEntry(testEntry2);
        testList = testLog.getLog();
        assertEquals(2, testList.size());

        assertTrue(testLog.clearLog());
        assertEquals(0, testList.size());
        assertFalse(testLog.clearLog());
    }

    //To test for existence of entry
    @Test
    void testGetEntry() {
        assertEquals(null, testLog.getEntry(date1));

        testLog.addEntry(testEntry1);
        testLog.addEntry(testEntry2);
        assertEquals(testEntry1, testLog.getEntry(date1));

        //To test when no entry with the given date exists
        assertEquals(null, testLog.getEntry(LocalDate.of(2024, 9, 30)));
    }
}
