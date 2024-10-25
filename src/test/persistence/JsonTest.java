package persistence;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.PeriodEntry;

public class JsonTest {
    protected void checkEntry(PeriodEntry entry1, PeriodEntry entry2) {
        assertEquals(LocalDate.parse("2024-10-14"), entry1.getDate());
        assertEquals(0, entry1.getHeaviness());
        assertEquals("Tampons", entry1.getCollectionMethod());
        assertEquals(2, entry1.getCollectionNumUsed());
        List<String> list = entry1.getFeelingsList();
        assertEquals(2, list.size());
        assertEquals("Sensitive", list.get(0));
        assertEquals("Anxious", list.get(1));
        list = entry1.getBreastHealth();
        assertEquals(1, list.size());
        assertEquals("Lumpy", list.get(0));

        assertEquals(LocalDate.parse("2024-10-11"), entry2.getDate());
        assertEquals(1, entry2.getHeaviness());
        assertEquals("Pads", entry2.getCollectionMethod());
        assertEquals(3, entry2.getCollectionNumUsed());
        list = entry2.getPain();
        assertEquals(2, list.size());
        assertEquals("Back", list.get(0));
        assertEquals("Breasts", list.get(1));
        list = entry1.getBreastHealth();
    }
}
