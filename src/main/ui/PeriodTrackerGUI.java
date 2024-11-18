package ui;

import javax.swing.*;
import java.awt.*;

/*
 * This is the graphical user interface of the PeriodTracker application.
 * It creates the main window and all additional components.
 */
public class PeriodTrackerGUI {
    private JFrame mainWindow;
    private JPanel leftMenuPanel;
    private JPanel lowerMenuPanel;
    private JButton track;
    private JButton modify;
    private JButton analyze;
    private JButton view;
    private JButton save;
    private JButton load;
    private JButton clear;

    private final int WIDTH = 900;
    private final int HEIGHT = 600;

    /*
     * EFFECTS: constructs an instrance of the gui and sets up and runs the application.
     */
    public PeriodTrackerGUI() {
        mainWindow = new JFrame();
        setup();
    }

    /*
     * EFFECTS: sets up all the components of the application and adds them to the main window.
     */
    private void setup() {
        mainWindowSetup();
        mainMenuSetup();
        lowerMenuSetup();
        mainWindow.add(leftMenuPanel, BorderLayout.WEST);
        mainWindow.add(lowerMenuPanel, BorderLayout.SOUTH);
        run();
    }

    private void run() {

    }

    /*
     * EFFECTS: sets up the main window of the application.
     */
    private void mainWindowSetup() {
        mainWindow.setTitle("Period Tracker");
        mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainWindow.setSize(WIDTH, HEIGHT);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }

    /*
     * EFFECTS: sets up the main menu (left) of the application along with the buttons in it.
     */
    private void mainMenuSetup() {
        leftMenuPanel = new JPanel(new GridLayout(4, 1, 0, 0));
        leftMenuPanel.setPreferredSize(new Dimension(250, HEIGHT));
        track = new JButton("Track");
        modify = new JButton("Modify");
        analyze = new JButton("Analyze");
        view = new JButton("View");
        leftMenuPanel.add(track);
        leftMenuPanel.add(modify);
        leftMenuPanel.add(analyze);
        leftMenuPanel.add(view);
    }

    /*
     * EFFECTS: sets up the save/load/clear functionality (bottom menu) of the application.
     */
    private void lowerMenuSetup() {
        lowerMenuPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 10));
        save = new JButton("Save");
        load = new JButton("Load");
        clear = new JButton("Clear All Data");
        clear.setForeground(Color.RED);
        lowerMenuPanel.add(save);
        lowerMenuPanel.add(load);
        lowerMenuPanel.add(clear);
    }
}
