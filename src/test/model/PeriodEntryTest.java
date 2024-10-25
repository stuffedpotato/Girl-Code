package model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PeriodEntryTest {

    private PeriodEntry testEntry1;
    private LocalDate date1;

    @BeforeEach
    void runBefore() {
        date1 = LocalDate.now();
        testEntry1 = new PeriodEntry(date1);
    }

    @Test
    void testConstructor() {
        LocalDate date2 = LocalDate.of(2024, 10, 01);
        PeriodEntry testEntry2 = new PeriodEntry(date2);

        assertEquals(date1, testEntry1.getDate());
        assertEquals(date2, testEntry2.getDate());
    }

    @Test
    void testLogHeaviness() {
        testEntry1.logHeaviness(1);
        assertEquals(1, testEntry1.getHeaviness());

        testEntry1.logHeaviness(2);
        assertEquals(2, testEntry1.getHeaviness());

    }

    @Test
    void testLogPain() {
        testEntry1.logPain("Back");
        testEntry1.logPain("Breasts");
        List<String> result = testEntry1.getPain();

        assertEquals(2, result.size());
        assertEquals("Back", result.get(0));
        assertEquals("Breasts", result.get(1));

        assertTrue(testEntry1.resetPain());
        assertEquals(0, result.size());

        assertFalse(testEntry1.resetPain());
    }

    @Test
    void testLogCollectionMethod() {
        testEntry1.logCollectionMethod("Tampon", 3);
        testEntry1.logCollectionMethod("Tampon", 1);
        assertEquals("Tampon", testEntry1.getCollectionMethod());
        assertEquals(4, testEntry1.getCollectionNumUsed());

        testEntry1.resetCollectionMethodNumUsed();
        assertEquals(0, testEntry1.getCollectionNumUsed());
        testEntry1.logCollectionMethod("Pads", 1);
        assertEquals("Pads", testEntry1.getCollectionMethod());
        assertEquals(1, testEntry1.getCollectionNumUsed());
    }

    @Test
    void testLogFeelings() {
        testEntry1.logFeelings("Sensitive");
        testEntry1.logFeelings("Anxious");
        List<String> result = testEntry1.getFeelingsList();

        assertEquals(2, result.size());
        assertEquals("Sensitive", result.get(0));
        assertEquals("Anxious", result.get(1));

        // Testing for duplication
        testEntry1.logFeelings("Sensitive");
        assertEquals(2, result.size());
        assertEquals("Sensitive", result.get(0));
        assertEquals("Anxious", result.get(1));

        assertTrue(testEntry1.resetFeelings());
        assertEquals(0, result.size());

        assertFalse(testEntry1.resetFeelings());
    }

    @Test
    void testLogBreastHealth() {
        testEntry1.logBreastHealth("Swollen");
        testEntry1.logBreastHealth("Lumpy");
        List<String> result = testEntry1.getBreastHealth();

        assertEquals(2, result.size());
        assertEquals("Swollen", result.get(0));
        assertEquals("Lumpy", result.get(1));

        // Testing for duplication
        testEntry1.logBreastHealth("Lumpy");
        assertEquals(2, result.size());
        assertEquals("Swollen", result.get(0));
        assertEquals("Lumpy", result.get(1));

        assertTrue(testEntry1.resetBreastHealth());
        assertEquals(0, result.size());

        assertFalse(testEntry1.resetBreastHealth());
    }

    @Test
    void testToString() {
        assertFalse(testEntry1.toString().contains("Areas of pain"));
        assertFalse(testEntry1.toString().contains("Feelings"));
        assertFalse(testEntry1.toString().contains("Breast health"));
        assertTrue(testEntry1.toString().contains("Date: " + date1));

        testEntry1.logPain("Back");
        testEntry1.logFeelings("Sensitive");
        testEntry1.logBreastHealth("Swollen");

        assertTrue(testEntry1.toString().contains("Areas of pain: " + testEntry1.getPain()));
        assertTrue(testEntry1.toString().contains("Feelings: " + testEntry1.getFeelingsList()));
        assertTrue(testEntry1.toString().contains("Breast health: " + testEntry1.getBreastHealth()));
    }
}
