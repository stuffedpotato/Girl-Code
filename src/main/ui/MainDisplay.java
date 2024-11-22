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
    private PeriodTrackerController controller;

    private TrackPage trackPage;
    private HomePage homePage;
    private ViewPage viewPage;

    /*
     * REQUIRES: listener must not be null.
     * MODIFIES: this, PeriodTrackerController
     * EFFECTS: creates an object of the main center display of the application
     * using CardLayout.
     */
    public MainDisplay(ActionListener listener) {
        this.listener = listener;
        this.controller = (PeriodTrackerController) listener;
        layout = new CardLayout();
        trackPage = new TrackPage(this.listener);
        homePage = new HomePage();
        viewPage = new ViewPage(controller.getLog());

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
        this.add(viewPage, "ViewPage");
    }

    /*
     * REQUIRES: page must be either "TrackPage", "HomePage" or "ViewPage"
     * MODIFIES: this, PeriodTrackerController
     * EFFECTS: displays the page depending on user input.
     */
    public void displayPage(String page) {
        if (page == "ViewPage") {
            viewPage.updateLog(controller.getLog());
        }

        layout.show(this, page);
    }

    // Getters

    public TrackPage getTrackPage() {
        return trackPage;
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public ViewPage getViewPage() {
        return viewPage;
    }
}
