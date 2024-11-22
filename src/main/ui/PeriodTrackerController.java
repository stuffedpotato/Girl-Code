package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.PeriodEntry;
import model.PeriodLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;

import java.util.List;

import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This is the graphical user interface of the PeriodTracker application.
 * It creates the main window and all additional components.
 * References: 
 * Graphical icons used for track, modify, analyze and view created by my boyfriend, Shashank Gupta.
 * Swing elements: https://youtube.com/playlist?list=PL3bGLnkkGnuV699lP_f9DvxyK5lMFpq6U&si=Xo6xqJMaPsw6SwEE
 */
public class PeriodTrackerController implements ActionListener {
    // Period Log & Period Entry instances
    private PeriodLog myLog;
    private PeriodEntry entry;
    private LocalDate date;

    private static final String JSON_DIRECTORY = "./data/mylog.json";
    private JsonWriter writer;
    private JsonReader reader;

    private MainDisplay mainDisplay;
    private LeftMenuPanel leftMenuPanel;
    private LowerMenuPanel lowerMenuPanel;

    private TrackPage trackPage;

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

        writer = new JsonWriter(JSON_DIRECTORY);
        reader = new JsonReader(JSON_DIRECTORY);

        mainWindow = new JFrame();
        mainDisplay = new MainDisplay(this);
        leftMenuPanel = new LeftMenuPanel(this, HEIGHT);
        lowerMenuPanel = new LowerMenuPanel(this);

        this.trackPage = mainDisplay.getTrackPage();

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
        mainWindow.setLayout(new BorderLayout(0, 0));
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

    /*
     * EFFECTS: checks where the event occured and calls other methods based on
     * that.
     */
    @SuppressWarnings("methodlength")
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Track":
                mainDisplay.displayPage("TrackPage");
                break;
            case "Analyze":
                mainDisplay.displayPage("HomePage");
                break;
            case "View":
                mainDisplay.displayPage("ViewPage");
                break;
            case "checkDate":
                checkDate(trackPage.getDate());
                break;
            case "saveButton":
                addEntry();
                break;
            case "Save":
                saveLog();
                break;
            case "Load":
                loadLog();
                break;
            case "Clear All Data":
                clearLog();
                break;
            default:
                mainDisplay.displayPage("HomePage");
                break;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: parses the date to a LocalDate object and checks that no entry
     * exists with the date entered.
     */
    private boolean checkDate(String date) {
        this.date = parseDate(date);

        if (myLog.findEntry(this.date)) {
            JOptionPane.showMessageDialog(null, "An entry already exists with this date!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            trackPage.resetLogPanel();
            return false;
        } else {
            trackPage.showLogPanel();
            return true;
        }
    }

    /*
     * MODIFIES: this, entry, myLog, trackPage
     * EFFECTS: adds entry to myLog, resets the tracking page and hides it.
     */
    private void addEntry() {
        if (checkDate(date.toString())) {
            entry = new PeriodEntry(date);

            logHeavinessLevel(trackPage.getHeavinessLevel());
            entry.logCollectionMethod(trackPage.getCollectionMethod(), trackPage.getCollectionNumUsed());
            logPainAreas();
            logBreastHealth();
            logFeelings();
            myLog.addEntry(entry);

            JOptionPane.showMessageDialog(null, "Saved!",
                    "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "An entry already exists with this date!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        trackPage.resetLogPanel();
        trackPage.hideLogPanel();
    }

    /*
     * MODIFIES: JSON_DIRECTORY
     * EFFECTS: saves myLog to JSON_DIRECTORY.
     * Referenced from JSONSerializationDemo
     * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
     */
    private void saveLog() {
        try {
            writer.open();
            writer.write(myLog);
            writer.close();
            JOptionPane.showMessageDialog(null, "Saved " + myLog.getDate() + "'s log to " + JSON_DIRECTORY + ".",
                    "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_DIRECTORY + ".",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
     * MODIFIES: myLog
     * EFFECTS: loads from previousy saved log from JSON_DIRECTORY.
     * Referenced from JSONSerializationDemo
     * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
     */
    private void loadLog() {
        try {
            myLog = reader.read();
            JOptionPane.showMessageDialog(null, "Loaded " + myLog.getDate() + "'s log from " + JSON_DIRECTORY + ".",
                    "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_DIRECTORY + ".",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearLog() {
        if (myLog.clearLog()) {
            JOptionPane.showMessageDialog(null, "All entries have been removed from your log!",
                    "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No entries available to remove!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
     * REQUIRES: heavinessLevel must be >= 0
     * MODIFIES: entry, myLog
     * EFFECTS: logs heaviness level to entry.
     */
    private void logHeavinessLevel(int heavinessLevel) {
        if (heavinessLevel == -1) {
            entry.logHeaviness(0);
        } else {
            entry.logHeaviness(heavinessLevel);
        }
    }

    /*
     * MODIFIES: entry, myLog
     * EFFECTS: logs pain areas to entry.
     */
    private void logPainAreas() {
        List<String> painAreas = trackPage.getPainAreas();

        for (String s : painAreas) {
            entry.logPain(s);
        }
    }

    /*
     * MODIFIES: entry, myLog
     * EFFECTS: logs breast health to entry.
     */
    private void logBreastHealth() {
        List<String> breastHealth = trackPage.getBreastHealth();

        if (breastHealth.isEmpty()) {
            return;
        }

        for (String s : breastHealth) {
            entry.logBreastHealth(s);
        }
    }

    /*
     * MODIFIES: entry, myLog
     * EFFECTS: logs feelings to entry.
     */
    private void logFeelings() {
        List<String> feelings = trackPage.getFeelings();

        if (feelings.isEmpty()) {
            return;
        }

        for (String s : feelings) {
            entry.logFeelings(s);
        }
    }

    /*
     * EFFECTS: created this method because my ViewPage was not updating its
     * contents when user was loading myLog from JSON.
     */
    public PeriodLog getLog() {
        return myLog;
    }
}