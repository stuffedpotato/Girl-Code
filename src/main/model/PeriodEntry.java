package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import persistence.Writable;

/*
 * This class represents a single period entry created by the user. 
 * It logs details of the period entry along with the date the entry corresponds to.
 */
public class PeriodEntry implements Writable {

    private LocalDate date;
    private int heavinessLevel;
    private int totalNumProductsUsed;
    private List<String> painAreas;
    private List<String> feelingsList;
    private List<String> breastConditions;
    private String collectionMethod;

    /*
     * REQUIRES: date must not be null.
     * EFFECTS: constructs an object of PeriodEntry and initializes this.date to the
     * date passed by user (i.e. the date they are logging their period for).
     */
    public PeriodEntry(LocalDate date) {
        this.date = date;
        totalNumProductsUsed = 0;
        painAreas = new ArrayList<String>();
        feelingsList = new ArrayList<String>();
        breastConditions = new ArrayList<String>();

    }

    /*
     * REQUIRES: level must be between 0 and 4 (both inclusive).
     * MODIFIES: this, PeriodLog
     * EFFECTS: logs the heaviness of the period where level 0 means user is not on
     * their period, level 1 corresponds to light flow, 2 corresponds to medium
     * flow, 3 corresponds to heavy flow and 4 corresponds to very heavy flow.
     */
    public void logHeaviness(int level) {
        heavinessLevel = level;
    }

    /*
     * REQUIRES: area must not be null.
     * MODIFIES: this, PeriodLog
     * EFFECTS: logs the area of the pain if the area has not previously been
     * entered and keeps a list of all areas of pain for the day.
     */
    public void logPain(String area) {
        loggingToList(painAreas, area);
    }

    /*
     * REQUIRES: collectionMethod must not be null, numUsed must be >0.
     * MODIFIES: this, PeriodLog
     * EFFECTS: logs the collection method and total num used of the collection
     * method for the day user is tracking.
     */
    public void logCollectionMethod(String collectionMethod, int numUsed) {
        this.collectionMethod = stringManipulation(collectionMethod);
        totalNumProductsUsed = totalNumProductsUsed + numUsed;
    }

    /*
     * REQUIRES: feeling must not be null.
     * MODIFIES: this, PeriodLog
     * EFFECTS: logs how the user felt the day of tracking (ex. sad, happy,
     * sensitive, angry, etc.) and keeps a list of all feelings for the day.
     */
    public void logFeelings(String feeling) {
        loggingToList(feelingsList, feeling);
    }

    /*
     * REQUIRES: condition must not be null. Note: breast pain must NOT be logged
     * here and must be logged in logPain method instead.
     * MODIFIES: this, PeriodLog
     * EFFECTS: logs the breast health of the user (ex. swollen breasts, lumps,
     * etc.).
     */
    public void logBreastHealth(String condition) {
        loggingToList(breastConditions, condition);
    }

    public LocalDate getDate() {
        return this.date;
    }

    public int getHeaviness() {
        return this.heavinessLevel;
    }

    public List<String> getPain() {
        return painAreas;
    }

    public String getCollectionMethod() {
        return collectionMethod;
    }

    public int getCollectionNumUsed() {
        return totalNumProductsUsed;
    }

    public List<String> getFeelingsList() {
        return feelingsList;
    }

    public List<String> getBreastHealth() {
        return breastConditions;
    }

    /*
     * Setters seemed unnecessary for my program so instead I have designed some
     * resetters to reset certain fields. User can reset a field and then use the
     * log methods to log new conditions.
     */

    /*
     * MODIFIES: this, PeriodLog
     * EFFECTS: empties list of pain areas if the list has any components and
     * returns true. If no components, does not do anything and return false.
     */
    public boolean resetPain() {
        return reset(painAreas);
    }

    /*
     * MODIFIES: this, PeriodLog
     * EFFECTS: resets total number of products used to 0 for the entry.
     */
    public void resetCollectionMethodNumUsed() {
        totalNumProductsUsed = 0;
    }

    /*
     * MODIFIES: this, PeriodLog
     * EFFECTS: empties list of feelings if the list has any components and returns
     * true. If no components, does not do anything and return false.
     */
    public boolean resetFeelings() {
        return reset(feelingsList);
    }

    /*
     * MODIFIES: this, PeriodLog
     * EFFECTS: empties list of breast conditions if the list has any components and
     * returns true. If no components, does not do anything and return false.
     */
    public boolean resetBreastHealth() {
        return reset(breastConditions);
    }

    /*
     * REQUIRES: list must not be null, s must not be null.
     * MODIFIES: this, PeriodLog
     * EFFECTS: adds s to list if it does not exist in the list already.
     */
    private void loggingToList(List<String> list, String s) {
        s = stringManipulation(s);

        if (list.isEmpty()) {
            list.add(s);
        } else {
            if (!findDuplicates(list, s)) {
                list.add(s);
            }
        }
    }

    /*
     * REQUIRES: s must not be null.
     * EFFECTS: formats the string s as: Abcd, i.e. makes the first letter uppercase
     * and the rest lowercase. Returns the formatted string.
     */
    private String stringManipulation(String s) {
        s = s.toLowerCase();
        String firstLetter = s.toUpperCase().substring(0, 1);
        s = firstLetter + s.substring(1);
        return s;
    }

    /*
     * REQUIRES: list must not be null and s must not be null.
     * EFFECTS: checks to see if s exists in the list. Returns true if yes and false
     * if no.
     */
    private boolean findDuplicates(List<String> list, String s) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(s)) {
                return true;
            }
        }

        return false;
    }

    /*
     * REQUIRES: list must not be null.
     * MODIFIES: this, PeriodLog
     * EFFECTS: empties the list if the list is not empty already and returns true.
     * Otherwise, returns false.
     */
    private boolean reset(List<String> list) {
        if (list.isEmpty()) {
            return false;
        }

        return list.removeAll(list);
    }

    /*
     * EFFECTS: returns string representation of the entry.
     */
    public String toString() {
        String result = "\nDate: " + date + "\nHeaviness: " + heavinessLevel
                + "\nCollection method: " + collectionMethod
                + "\nTotal number of products used: " + totalNumProductsUsed;

        if (!painAreas.isEmpty()) {
            result = result + "\nAreas of pain: " + painAreas;
        }
        if (!feelingsList.isEmpty()) {
            result = result + "\nFeelings: " + feelingsList;
        }
        if (!breastConditions.isEmpty()) {
            result = result + "\nBreast health: " + breastConditions;
        }

        return (result + "\n");
    }
}
