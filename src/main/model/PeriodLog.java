package model;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

/*
 * This class is used to log all period entries and store them. 
 * It also has methods to access all/any entries as well as 
 * has methods that can be called if user wants to analyze their period log.
 */
public class PeriodLog {
    private List<PeriodEntry> myLog;

    /*
     * EFFECTS: creates an instance of PeriodLog by creating an array list to store
     * PeriodEntry entries.
     */
    public PeriodLog() {
        myLog = new ArrayList<PeriodEntry>();
    }

    /*
     * REQUIRES: entry must not be null.
     * MODIFIES: this
     * EFFECTS: adds the period entry to the list of all entries if it does not
     * exist already. Checks using date of entry.
     */
    public boolean addEntry(PeriodEntry entry) {
        if (myLog.isEmpty()) {
            myLog.add(entry);
            return true;
        } else {
            if (!findEntry(entry)) {
                myLog.add(entry);
                return true;
            }
        }
        
        return false;
    }

    /*
     * MODIFIES: this
     * EFFECTS: if period log contains any elements (entries), removes all elements
     * and returns true. Returns false otherwise.
     */
    public boolean clearLog() {
        if (myLog.isEmpty()) {
            return false;
        }

        myLog.removeAll(myLog);
        return true;
    }

    /*
     * REQUIRES: date must not be null.
     * EFFECTS: returns the requested entry from the log based on the date entered
     * if entry exists in the log.
     */
    public PeriodEntry getEntry(LocalDate date) {
        if (myLog.isEmpty()) {
            return null;
        }

        for (int i = 0; i < myLog.size(); i++) {
            PeriodEntry entry = myLog.get(i);

            if (entry.getDate().equals(date)) {
                return entry;
            }
        }

        return null;
    }

    /*
     * EFFECTS: returns the list of all period entries that exist in the period log.
     */
    public List<PeriodEntry> getLog() {
        return myLog;
    }

    /*
     * REQUIRES: myLog must not be null and entry must not be null.
     * EFFECTS: checks to see if entry already exists in myLog and returns true if
     * found.
     * Returns false otherwise.
     */
    private boolean findEntry(PeriodEntry entry) {
        LocalDate date = entry.getDate();

        for (int i = 0; i < myLog.size(); i++) {
            if (myLog.get(i).getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    // ANALYTICS

    /*
     * EFFECTS: returns average cycle length based on number of days that user
     * recorded a heaviness level of 1-4 and total number of entries in the log.
     */
    public int getAverageCycleLength() {
        if (myLog.isEmpty()) {
            return 0;
        }

        int numDays = 0;
        int average = 0;
        int size = myLog.size();

        for (int i = 0; i < size; i++) {
            PeriodEntry entry = myLog.get(i);

            if (entry.getHeaviness() > 0) {
                numDays++;
            }
        }

        if (size < 28) {
            average = numDays;
        } else {
            double cycles = size / 28;
            average = (int) (numDays / cycles);
        }

        return average;
    }

    /*
     * EFFECTS: returns string representation of the log.
     */
    @Override
    public String toString() {

        if (myLog.isEmpty()) {
            return ("Nothing to display");
        }

        String result = "";

        for (int i = 0; i < myLog.size(); i++) {
            result = result + myLog.get(i);
        }

        return result;
    }
}
