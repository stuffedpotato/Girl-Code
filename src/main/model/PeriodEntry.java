package model;

import java.time.LocalDate;

//This class represents a single period entry created by the user. It logs details of the period entry along with the date the entry corresponds to.
public class PeriodEntry {
    //fields

    /*
     * REQUIRES: date must not be null.
     * EFFECTS: constructs an object of PeriodEntry and initializes this.date to the date passed by user (i.e. the date they are logging their period for).
     */
    public PeriodEntry(LocalDate date) {
        //stub
    }

    /*
     * REQUIRES: level must be between 1 and 4 (both inclusive).
     * MODIFIES: this
     * EFFECTS: logs the heaviness of the period where level 1 corresponds to light flow, 2 corresponds to medium flow, 3 corresponds to heavy flow and 4 corresponds to very heavy flow.
     */
    private void logHeaviness(int level) {
        //stub
    }

    /*
     * REQUIRES: area must not be null, level must be between 1 and 4 (both inclusive).
     * MODIFIES: this
     * EFFECTS: logs the area of the pain along with the level of pain experienced. Level 1 corresponds to mild, 2 is moderate, 3 is high and 4 means user needed medical attention.
     */
    private void logPain(String area, int level) {
        //stub
    }

    /*
     * REQUIRES: collectionMethod must not be null, numUsed must be >0.
     * MODIFIES: this
     * EFFECTS: logs the collection method used the day of tracking. numUsed represents the number used of that particular collection method for the day (ex. user used 3 pads in total the day of tracking).
     */
    private void logCollectionMethod(String collectionMethod, int numUsed) {
        //stub
    }

    /*
     * REQUIRES: feeling must not be null.
     * MODIFIES: this
     * EFFECTS: logs how the user felt the day of tracking (ex. sad, happy, sensitive, angry, etc.).
     */
    private void logFeelings(String feeling) {
        //stub
    }

    /*
     * REQUIRES: condition must not be null. Note: breast pain must NOT be logged here and must be logged in logPain method instead.
     * MODIFIES: this
     * EFFECTS: logs the breast health of the user (ex. swollen breasts, lumps, etc.).
     */
    private void logBreastHealth(String condition) {
        //stub
    }

    private void getHeaviness() {
        //stub
    }

    private void getPain() {
        //stub
    }

    private void getCollectionMethod() {
        //stub
    }

    private void getFeelings() {
        //stub
    }

    private void getBreastHealth() {
        //stub
    }

    private void setHeaviness(int level) {
        //stub
    }

    private void setPain(String area, int level) {
        //stub
    }

    private void setCollectionMethod(String collectionMethod, int numUsed) {
        //stub
    }

    private void setFeelings(String feeling) {
        //stub
    }

    private void setBreastHealth(String condition) {
        //stub
    }

}
