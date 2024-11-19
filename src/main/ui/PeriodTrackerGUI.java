package ui;

import model.PeriodEntry;
import model.PeriodLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;

/*
 * This is the graphical user interface of the PeriodTracker application.
 * It creates the main window and all additional components.
 */
public class PeriodTrackerGUI {
    private PeriodLog myLog;
    private ButtonListener btnListener;
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
    private JButton home;

    private final int WIDTH = 1150;
    private final int HEIGHT = 790;

    /*
     * EFFECTS: constructs an instrance of the gui and sets up and runs the
     * application.
     */
    public PeriodTrackerGUI() {
        myLog = new PeriodLog(LocalDate.now());
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
        mainWindow.setTitle("Girl Code");
        mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainWindow.setSize(WIDTH, HEIGHT);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
        mainWindow.setResizable(false);
        btnListener = new ButtonListener();
    }

    /*
     * EFFECTS: sets up the main menu (left) of the application along with the
     * buttons in it.
     */
    private void mainMenuSetup() {
        leftMenuPanel = new JPanel(new GridLayout(4, 1, 0, 0));
        leftMenuPanel.setPreferredSize(new Dimension(250, HEIGHT));
        leftMenuPanel.setBackground(new Color(248, 211, 224));

        mainMenuButtonsSetup();

        leftMenuPanel.add(track);
        leftMenuPanel.add(modify);
        leftMenuPanel.add(analyze);
        leftMenuPanel.add(view);
    }

    /*
     * EFFECTS: sets up the buttons for the main menu (track, modify, analyze and view) of the application.
     */
    private void mainMenuButtonsSetup() {
        Font buttonFont = new Font("Helvetica", Font.BOLD, 35);

        track = new JButton("Track");
        modify = new JButton("Modify");
        analyze = new JButton("Analyze");
        view = new JButton("View");

        track.setFont(buttonFont);
        modify.setFont(buttonFont);
        analyze.setFont(buttonFont);
        view.setFont(buttonFont);

        track.setForeground(new Color(250, 126, 176));
        modify.setForeground(new Color(230, 92, 154));
        analyze.setForeground(new Color(209, 57, 131));
        view.setForeground(new Color(188, 22, 108));
    }

    /*
     * EFFECTS: sets up the home/save/load/clear functionality (bottom menu) of the
     * application.
     */
    private void lowerMenuSetup() {
        lowerMenuPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        lowerMenuPanel.setBackground(new Color(248, 211, 224));

        lowerMenuButtonsSetup();

        lowerMenuPanel.add(home);
        lowerMenuPanel.add(save);
        lowerMenuPanel.add(load);
        lowerMenuPanel.add(clear);
    }

    /*
     * EFFECTS: sets up the buttons for the lower menu (home, save, load and clear) of the application.
     */
    private void lowerMenuButtonsSetup() {
        Font buttonsFont = new Font("Helvetica", Font.PLAIN, 13);

        save = new JButton("Save");
        load = new JButton("Load");
        clear = new JButton("Clear All Data");
        home = new JButton("Home");

        save.setFont(buttonsFont);
        load.setFont(buttonsFont);
        clear.setFont(buttonsFont);
        home.setFont(buttonsFont);

        clear.setForeground(Color.RED);
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

    /*
     * TODO:
     * need to add action/event listeners to the buttons
     * need to figure out how to increase the font size of the labels of the buttons - DONE
     * need to figure out how to get to the home page.. - DONE
     * need to update readme
     */

    /*
     * This is a private class that implements ActionListener and is used to handle all button clicks.
     */
    private class ButtonListener implements ActionListener {
        
        /*
         * EFFECTS: handles all button clicks by using the event e generated.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("track")) {
                trackAction();
            }

            if (e.getActionCommand().equals("modify")) {
                modifyAction();
            }

            if (e.getActionCommand().equals("analyze")) {
                analyzeAction();
            }

            if (e.getActionCommand().equals("view")) {
                
            }

            if (e.getActionCommand().equals("home")) {
                
            }

            if (e.getActionCommand().equals("save")) {
                
            }

            if (e.getActionCommand().equals("load")) {
                
            }

            if (e.getActionCommand().equals("clear")) {
                
            }
        }

        /*
         * EFFECTS: performs the track action when user clicks on the track button.
         */
        private void trackAction() {

        }

        /*
         * EFFECTS: performs the modify action when user clicks on the modify button.
         */
        private void modifyAction() {

        }

        /*
         * EFFECTS: performs the analyze action when user clicks on the analyze button.
         */
        private void analyzeAction() {

        }
    }
}
