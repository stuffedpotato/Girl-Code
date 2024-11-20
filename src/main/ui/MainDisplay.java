package ui;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;

/*
 * This class creates the main display (dynamic depending on user choice) of the application.
 */
public class MainDisplay extends JPanel {
    private CardLayout layout;
    private ActionListener listener;

    private TrackPage trackPage;
    private HomePage homePage;

    /*
     * REQUIRES: listener must not be null.
     * MODIFIES: this, PeriodTrackerController
     * EFFECTS: creates an object of the main center display of the application
     * using CardLayout.
     */
    public MainDisplay(ActionListener listener) {
        this.listener = listener;
        layout = new CardLayout();
        trackPage = new TrackPage(this.listener);
        homePage = new HomePage();

        setup();
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds different pages to the layout manager.
     */
    private void setup() {
        this.setLayout(layout);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.add(trackPage, "TrackPage");
        this.add(homePage, "HomePage");
    }

    /*
     * REQUIRES: page must be either "TrackPage", "HomePage" or "ViewPage"
     * MODIFIES: this, PeriodTrackerController
     * EFFECTS: displays the page depending on user input.
     */
    public void displayPage(String page) {
        layout.show(this, page);
    }

    public TrackPage getTrackPage() {
        return trackPage;
    }

    public HomePage getHomePage() {
        return homePage;
    }
}
