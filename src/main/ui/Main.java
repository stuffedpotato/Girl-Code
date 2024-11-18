package ui;

import java.io.FileNotFoundException;

/*
 * Main class creates an instance of the PeriodTracker application.
 */
public class Main {
    public static void main(String[] args) {
        try {
            new PeriodTrackerConsole();
            // new PeriodTrackerGUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found.");
        }
        
    }
}
