package ui;

import model.PeriodEntry;
import model.PeriodLog;
import javax.swing.*;
import java.awt.*;

import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This is the graphical user interface of the PeriodTracker application.
 * It creates the main window and all additional components.
 * Credits: Graphical icons used for track, modify, analyze and view created by my boyfriend, Shashank Gupta.
 */
public class PeriodTrackerController implements ActionListener {
    // Period Log & Period Entry instances
    private PeriodLog myLog;
    private PeriodEntry entry;
    private LocalDate date;

    private MainDisplay mainDisplay;
    private LeftMenuPanel leftMenuPanel;
    private LowerMenuPanel lowerMenuPanel;

    // Main frame
    private JFrame mainWindow;

    // All constant fields
    private static final int WIDTH = 1180;
    private static final int HEIGHT = 780;

    /*
     * MODIFIES: this
     * EFFECTS: constructs an instrance of the gui and sets up and runs the
     * application.
     */
    public PeriodTrackerController() {
        myLog = new PeriodLog(LocalDate.now());

        mainWindow = new JFrame();
        mainDisplay = new MainDisplay(this);
        leftMenuPanel = new LeftMenuPanel(this, HEIGHT);
        lowerMenuPanel = new LowerMenuPanel(this);

        setup();
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up all the components of the application and adds them to the
     * main window.
     */
    private void setup() {
        mainWindowSetup();

        mainWindow.add(mainDisplay, BorderLayout.CENTER);
        mainDisplay.displayPage("HomePage");
        mainWindow.add(leftMenuPanel, BorderLayout.WEST);
        mainWindow.add(lowerMenuPanel, BorderLayout.SOUTH);

    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up the main window of the application.
     */
    private void mainWindowSetup() {
        mainWindow.setTitle("Welcome to Girl Code!");
        mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainWindow.setSize(WIDTH, HEIGHT);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
        mainWindow.setResizable(false);
    }

    /*
     * REQUIRES: date must be in the format yyyy-m-d and must not be null.
     * EFFECTS: parses the String date and returns a LocalDate object.
     */
    private LocalDate parseDate(String date) {
        return (LocalDate.parse(date));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Track":
                mainDisplay.displayPage("TrackPage");
                break;
            case "Modify":
                mainDisplay.displayPage("HomePage");
                break;
            case "Analyze":
                mainDisplay.displayPage("HomePage");
                break;
            case "View":
                mainDisplay.displayPage("HomePage");
                break;
        }
    }
}