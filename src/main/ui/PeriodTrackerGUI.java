package ui;

import javax.swing.*;
import java.awt.*;

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

    public PeriodTrackerGUI() {
        mainWindow = new JFrame();
        setup();
    }

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

    private void mainWindowSetup() {
        mainWindow.setTitle("Period Tracker");
        mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainWindow.setSize(WIDTH, HEIGHT);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }

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
