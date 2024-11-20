package ui;

import java.io.FileNotFoundException;
import javax.swing.*;

/*
 * Main class creates an instance of the PeriodTracker application.
 */
public class Main {
    public static void main(String[] args) {
        // try {
        //     new PeriodTrackerConsole();
        // } catch (FileNotFoundException e) {
        //     System.out.println("Unable to open file.");
        // }

        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                new PeriodTrackerController();
            }
        });
    }
}
