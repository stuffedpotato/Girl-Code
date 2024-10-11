package model;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

/*
 * This class is used to log all period entries and store them.
 */
public class PeriodLog {
    //fields

    /*
     * EFFECTS: creates an instance of PeriodLog by creating an array list to store PeriodEntry entries.
     */
    public PeriodLog() {
        //stub
    }

    /*
     * REQUIRES: entry must not be null.
     * MODIFIES: this
     * EFFECTS: adds the period entry to the list of all entries if it does not exist already. Checks using date of entry.
     */
    public void addEntry(PeriodEntry entry) {
        //stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: if period log contains any elements (entries), removes all elements and returns true. Returns false otherwise.
     */
    public boolean clearLog() {
        return true; //stub
    }

    /*
     * REQUIRES: date must not be null.
     * EFFECTS: returns the requested entry from the log based on the date entered.
     */
    public PeriodEntry getEntry(LocalDate date) {
        return null; //stub
    }

    /*
     * EFFECTS: returns the list of all period entries that exist in the period log.
     */
    public List<PeriodEntry> getLog() {
        return null; //stub
    }

    /*
     * REQUIRES: list must not be null and entry must not be null.
     * EFFECTS: checks to see if entry already exists in the list and returns true if found. Returns false otherwise.
     */
    private boolean findDuplicates(List<PeriodEntry> list, PeriodEntry entry) {
        return true; //stub
    }

}
