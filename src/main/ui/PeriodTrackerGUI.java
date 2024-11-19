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
public class PeriodTrackerGUI {
    private PeriodLog myLog;
    private PeriodEntry entry;

    private JFrame mainWindow;

    private LocalDate date;

    private JLabel display;
    private JPanel leftMenuPanel;
    private JPanel lowerMenuPanel;
    private JPanel trackPageTopRow;
    private JPanel trackPageLogPanel;
    private JPanel trackPageLogPanelHeaviness;

    private JButton track;
    private JButton modify;
    private JButton analyze;
    private JButton view;
    private JButton save;
    private JButton load;
    private JButton clear;
    private JButton home;
    private JButton dateButton;
    
    private JLabel dateLabel;
    private JLabel logHeaviness;
    private JLabel logCollectionMethod;
    private JLabel logPainAreas;
    private JLabel logFeelings;
    private JLabel logBreastHealth;

    private JRadioButton heavinessZero;
    private JRadioButton heavinessOne;
    private JRadioButton heavinessTwo;
    private JRadioButton heavinessThree;
    private JRadioButton heavinessFour;
    private JRadioButton pads;
    private JRadioButton tampons;
    private JRadioButton liners;
    private JRadioButton periodCup;
    private JRadioButton periodUnderwear;
    
    private JCheckBox backPain;
    private JCheckBox headPain;
    private JCheckBox breastkPain;
    private JCheckBox legsPain;
    private JCheckBox jointsPain;
    private JCheckBox vulvarkPain;
    private JCheckBox ovularPain;
    private JCheckBox healthyBreasts;
    private JCheckBox lumpyBreasts;
    private JCheckBox swollenBreasts;
    private JCheckBox tenderBreasts;
    private JCheckBox happy;
    private JCheckBox sad;
    private JCheckBox sensitive;
    private JCheckBox anxious;
    private JCheckBox angry;
    private JCheckBox moodSwings;

    private JTextField dateField;

    private MainMenuButtonListener btnListener;
    private TrackPageEventHandler eventHandler;

    private static final int WIDTH = 1180;
    private static final int HEIGHT = 780;
    private static final String HOME_PAGE = "data/images/main_edited.png";
    private static final String BACKGROUND_IMAGE = "data/images/background.png";

    /*
     * MODIFIES: this
     * EFFECTS: constructs an instrance of the gui and sets up and runs the
     * application.
     */
    public PeriodTrackerGUI() {
        myLog = new PeriodLog(LocalDate.now());
        mainWindow = new JFrame();
        btnListener = new MainMenuButtonListener();
        eventHandler = new TrackPageEventHandler();
        setup();
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up all the components of the application and adds them to the
     * main window.
     */
    private void setup() {
        mainWindowSetup();
        mainMenuSetup();
        lowerMenuSetup();
        homeSetup();

        mainWindow.add(leftMenuPanel, BorderLayout.WEST);
        mainWindow.add(lowerMenuPanel, BorderLayout.SOUTH);
        run();
    }

    private void run() {
        addButtonListener(leftMenuPanel);
        addButtonListener(lowerMenuPanel);
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
     * MODIFIES: this
     * EFFECTS: sets up the main menu (left) of the application along with the
     * buttons in it.
     */
    private void mainMenuSetup() {
        leftMenuPanel = new JPanel(new GridLayout(4, 1, 0, 0));
        leftMenuPanel.setPreferredSize(new Dimension(200, HEIGHT));
        leftMenuPanel.setBackground(new Color(248, 211, 224));

        track = new JButton("Track");
        modify = new JButton("Modify");
        analyze = new JButton("Analyze");
        view = new JButton("View");

        leftMenuPanel.add(track);
        leftMenuPanel.add(modify);
        leftMenuPanel.add(analyze);
        leftMenuPanel.add(view);

        mainMenuButtonsSetup();
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up the buttons for the main menu (track, modify, analyze and
     * view) of the application.
     */
    private void mainMenuButtonsSetup() {
        Font buttonFont = new Font("Helvetica", Font.BOLD, 15);

        track.setToolTipText("Track your menstrual health");
        modify.setToolTipText("Modify a previous period entry");
        analyze.setToolTipText("Analyze your menstrual health");
        view.setToolTipText("View previously entered period entries");

        // Referenced how to loop over buttons from:
        // https://stackoverflow.com/a/23357273
        for (Component c : leftMenuPanel.getComponents()) {
            if (c instanceof JButton) {
                JButton btn = (JButton) c;
                String btnName = "" + btn.getText().charAt(0);
                btnName = btnName + btn.getText().substring(1);
                String srcString = "data/images/" + btnName + ".png";

                btn.setFont(buttonFont);
                btn.setIcon(new ImageIcon(srcString));
                btn.setIconTextGap(10);
                btn.setVerticalTextPosition(SwingConstants.BOTTOM);
                btn.setHorizontalTextPosition(SwingConstants.CENTER);
            }
        }

        track.setForeground(new Color(250, 126, 176));
        modify.setForeground(new Color(230, 92, 154));
        analyze.setForeground(new Color(209, 57, 131));
        view.setForeground(new Color(188, 22, 108));
    }

    /*
     * MODIFIES: this
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
     * MODIFIES: this
     * EFFECTS: sets up the buttons for the lower menu (home, save, load and clear)
     * of the application.
     */
    private void lowerMenuButtonsSetup() {
        Font buttonsFont = new Font("Helvetica", Font.PLAIN, 13);

        save = new JButton("Save");
        load = new JButton("Load");
        clear = new JButton("Clear All Data");
        home = new JButton("Home");

        // homeIcon taken from: https://www.flaticon.com/free-icons/home
        // ImageIcon homeIcon = new ImageIcon("data/images/home.png");
        // home.setIcon(homeIcon);

        save.setFont(buttonsFont);
        load.setFont(buttonsFont);
        clear.setFont(buttonsFont);
        home.setFont(buttonsFont);

        save.setToolTipText("Save your work");
        load.setToolTipText("Load previously saved work");
        clear.setToolTipText("Clear all");

        clear.setForeground(Color.RED);
    }

    /*
     * Referenced from:
     * https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
     * Image taken from:
     * https://www.eka.care/services/be-in-sync-with-your-cycle-tracking-your-
     * menstrual-period
     * MODIFIES: this
     * EFFECTS: sets up the main image at the center of the application when the app
     * is first run.
     */
    private void homeSetup() {
        display = new JLabel(new ImageIcon(HOME_PAGE));
        mainWindow.add(display, BorderLayout.CENTER);
    }

    /*
     * REQUIRES: panel must not be null
     * MODIFIES: this
     * EFFECTS: adds the button listener to all components of the panel if the
     * component is an instance of JButton
     */
    private void addButtonListener(JPanel panel) {
        // Referenced how to loop over buttons from:
        // https://stackoverflow.com/a/23357273
        for (Component c : panel.getComponents()) {
            if (c instanceof JButton) {
                JButton btn = (JButton) c;
                btn.addActionListener(btnListener);
            }
        }
    }

    private LocalDate parseDate(String date) {
        return (LocalDate.parse(date));
    }

    /*
     * TODO:
     * need to add action/event listeners to the buttons
     * need to figure out how to increase the font size of the labels of the buttons
     * - DONE
     * need to figure out how to get to the home page.. - DONE
     * need to update readme
     */

    /*
     * This is a private class that implements ActionListener and is used to handle
     * all button clicks.
     */
    private class MainMenuButtonListener implements ActionListener {

        /*
         * EFFECTS: handles all button clicks by using the event e generated.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(track)) {
                trackAction();
            }

            if (e.getSource().equals(modify)) {
                modifyAction();
            }

            if (e.getSource().equals(analyze)) {
                analyzeAction();
            }

            if (e.getSource().equals(view)) {
                viewAction();
            }

            if (e.getSource().equals(home)) {
                homeAction();
            }

            if (e.getSource().equals(save)) {
                saveAction();
            }

            if (e.getSource().equals(load)) {
                loadAction();
            }

            if (e.getSource().equals(clear)) {
                clearAction();
            }
        }

        /*
         * MODIFIES: this, myLog
         * EFFECTS: performs the track action when user clicks on the track button.
         */
        private void trackAction() {
            trackPageSetup();
            dateField.addActionListener(eventHandler);
            dateButton.addActionListener(eventHandler);
        }

        /*
         * MODIFIES: this, myLog
         * EFFECTS: performs the modify action when user clicks on the modify button.
         */
        private void modifyAction() {
            display.setIcon(new ImageIcon(BACKGROUND_IMAGE));
        }

        /*
         * MODIFIES: this
         * EFFECTS: performs the analyze action when user clicks on the analyze button.
         */
        private void analyzeAction() {
            display.setIcon(new ImageIcon(BACKGROUND_IMAGE));
        }

        /*
         * MODIFIES: this
         * EFFECTS: performs the view action when user clicks on the view button.
         */
        private void viewAction() {
            display.setIcon(new ImageIcon(BACKGROUND_IMAGE));
        }

        /*
         * MODIFIES: this
         * EFFECTS: takes user to the home page when user clicks on the home button.
         */
        private void homeAction() {
            display.setIcon(new ImageIcon(HOME_PAGE));
        }

        /*
         * MODIFIES: this, myLog
         * EFFECTS: performs the save action when user clicks on the save button.
         */
        private void saveAction() {

        }

        /*
         * MODIFIES: this, myLog
         * EFFECTS: performs the load action when user clicks on the load button.
         */
        private void loadAction() {

        }

        /*
         * MODIFIES: this, myLog
         * EFFECTS: performs the clear action when user clicks on the clear button.
         */
        private void clearAction() {

        }

        /*
         * MODIFIES: this
         * EFFECTS: sets up the gui components for the tracking page.
         */
        private void trackPageSetup() {
            display.setLayout(new BorderLayout());
            display.setIcon(new ImageIcon(BACKGROUND_IMAGE));

            trackPageLogPanel = new JPanel(new GridLayout(6, 1, 10, 50));
            trackPageLogPanel.setOpaque(false);

            trackPageTopRow = new JPanel();
            trackPageTopRow.setOpaque(false);

            trackPageLogPanelHeaviness = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            trackPageLogPanelHeaviness.setOpaque(false);
            trackPageLogPanelHeaviness.setPreferredSize(new Dimension(700, 200));

            setTopRowAttributes();
            setLogHeavinessAttributes();

            trackPageLogPanel.add(new JLabel(""));
            trackPageLogPanel.add(trackPageLogPanelHeaviness);
        
            display.add(trackPageTopRow, BorderLayout.NORTH);
            display.add(trackPageLogPanel, BorderLayout.CENTER);
            mainWindow.add(display, BorderLayout.CENTER);
            mainWindow.setVisible(true);

            mainWindow.revalidate();
            mainWindow.repaint();
        }

        /*
         * MODIFIES: this
         * EFFECTS: sets up attributes of all the components in the track page.
         */
        private void setTopRowAttributes() {
            dateLabel = new JLabel("Enter the date: ");
            dateLabel.setFont(new Font("Helvetica", Font.BOLD, 25));

            dateField = new JTextField(15);
            dateField.setFont(new Font("Helvetica", Font.PLAIN, 25));
            dateField.setToolTipText("Enter the date in yyyy/m/d format");

            dateButton = new JButton("Track");
            dateButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
            dateButton.setMargin(new Insets(5, 5, 5, 5));
            dateButton.setPreferredSize(new Dimension(90, 50));

            trackPageTopRow.add(dateLabel);
            trackPageTopRow.add(dateField);
            trackPageTopRow.add(dateButton);
        }

        private void setLogHeavinessAttributes() {
            logHeaviness = new JLabel("Heaviness: ");
            heavinessZero = new JRadioButton("0");
            heavinessOne = new JRadioButton("1");
            heavinessTwo = new JRadioButton("2");
            heavinessThree = new JRadioButton("3");
            heavinessFour = new JRadioButton("4");

            trackPageLogPanelHeaviness.add(logHeaviness);
            trackPageLogPanelHeaviness.add(heavinessZero);
            trackPageLogPanelHeaviness.add(heavinessOne);
            trackPageLogPanelHeaviness.add(heavinessTwo);
            trackPageLogPanelHeaviness.add(heavinessThree);
            trackPageLogPanelHeaviness.add(heavinessFour);

            Font heavinessFont = new Font("Helvetica", Font.PLAIN, 20);
            ButtonGroup heavinessGroup = new ButtonGroup();

            for (Component c : trackPageLogPanelHeaviness.getComponents()) {
                if (c instanceof JRadioButton) {
                    JRadioButton radioBtn = (JRadioButton) c;

                    radioBtn.setFont(heavinessFont);
                    heavinessGroup.add(radioBtn);
                    // radioBtn.setVisible(false);
                }
            }

            logHeaviness.setFont(new Font("Helvetica", Font.BOLD, 20));
            // logHeaviness.setVisible(false);
        }
    }

    /*
     * Handles the events generated by the track page.
     */
    private class TrackPageEventHandler implements ActionListener {

        /*
         * MODIFIES: this, myLog, entry
         * EFFECTS: handles the events generated by the track page.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(dateField) || e.getSource().equals(dateButton)) {
                date = parseDate(dateField.getText());
                entry = new PeriodEntry(date);
                if (myLog.addEntry(entry)) {
                    // show buttons to log data
                } else {
                    display.add(new JLabel("An entry already exists for this date, please try again!"), BorderLayout.CENTER);
                }
            }
            
        }
    }
}
