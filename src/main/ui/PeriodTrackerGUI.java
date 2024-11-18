package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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

    private final int WIDTH = 1150;
    private final int HEIGHT = 800;

    /*
     * EFFECTS: constructs an instrance of the gui and sets up and runs the
     * application.
     */
    public PeriodTrackerGUI() {
        mainWindow = new JFrame();
        setup();
    }

    /*
     * EFFECTS: sets up all the components of the application and adds them to the
     * main window.
     */
    private void setup() {
        mainWindowSetup();
        mainMenuSetup();
        lowerMenuSetup();
        photoSetup();

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
        mainWindow.setResizable(false);
    }

    /*
     * EFFECTS: sets up the main menu (left) of the application along with the
     * buttons in it.
     */
    private void mainMenuSetup() {
        leftMenuPanel = new JPanel(new GridLayout(4, 1, 0, 0));
        leftMenuPanel.setPreferredSize(new Dimension(250, HEIGHT));
        leftMenuPanel.setBackground(new Color(248, 211, 224));

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
     * EFFECTS: sets up the save/load/clear functionality (bottom menu) of the
     * application.
     */
    private void lowerMenuSetup() {
        lowerMenuPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        lowerMenuPanel.setPreferredSize(new Dimension(WIDTH, 50));
        lowerMenuPanel.setBackground(new Color(248, 211, 224));

        save = new JButton("Save");
        save.setSize(100, 100);

        load = new JButton("Load");
        load.setSize(100, 100);

        clear = new JButton("Clear All Data");
        clear.setSize(100, 100);
        clear.setForeground(Color.RED);

        lowerMenuPanel.add(save);
        lowerMenuPanel.add(load);
        lowerMenuPanel.add(clear);
    }

    /*
     * Referenced from:
     * https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
     * Image taken from:
     * https://www.eka.care/services/be-in-sync-with-your-cycle-tracking-your-
     * menstrual-period
     * EFFECTS: sets up the main image at the center of the application when the app
     * is first run.
     */
    private void photoSetup() {
        try {
            BufferedImage myPicture = ImageIO.read(new File("data/images/main_edited.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            mainWindow.add(picLabel, BorderLayout.CENTER);
        } catch (IOException e) {
            System.out.println("Image file not found.");
        }
    }
}
