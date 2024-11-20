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

    private LeftMenuPanel leftMenuPanel;
    private LowerMenuPanel lowerMenuPanel;

    // Main frame
    private JFrame mainWindow;

    // All panels
    private JLabel centerDisplay;
    private JPanel trackPageTopRow;
    private JPanel trackPageLogPanel;
    private JPanel trackPageLogHeavinessPanel;
    private JPanel trackPageLogCollectionMethodPanel;
    private JPanel trackPageLogPainAreasPanel;
    private JPanel trackPageLogBreastHealthPanel;
    private JPanel trackPageLogFeelingsPanel;
    private JPanel trackPageLogNumCollectionMethodPanel;

    // All buttons
    private JButton dateButton;
    
    // All labels
    private JLabel dateLabel;
    private JLabel logHeaviness;
    private JLabel logCollectionMethod;
    private JLabel logNumCollectionMethod;
    private JLabel logPainAreas;
    private JLabel logFeelings;
    private JLabel logBreastHealth;

    // Heaviness options
    private JRadioButton heavinessZero;
    private JRadioButton heavinessOne;
    private JRadioButton heavinessTwo;
    private JRadioButton heavinessThree;
    private JRadioButton heavinessFour;

    // Collection method options
    private JRadioButton pads;
    private JRadioButton tampons;
    private JRadioButton liners;
    private JRadioButton periodCup;
    private JRadioButton periodUnderwear;
    
    // Pain areas options
    private JCheckBox backPain;
    private JCheckBox headPain;
    private JCheckBox breastPain;
    private JCheckBox legsPain;
    private JCheckBox jointsPain;
    private JCheckBox vulvarPain;
    private JCheckBox ovularPain;

    // Breast health options
    private JCheckBox healthyBreasts;
    private JCheckBox lumpyBreasts;
    private JCheckBox swollenBreasts;
    private JCheckBox tenderBreasts;

    // Feelings options
    private JCheckBox happy;
    private JCheckBox sad;
    private JCheckBox sensitive;
    private JCheckBox anxious;
    private JCheckBox angry;
    private JCheckBox moodSwings;

    // All text fields
    private JTextField dateField;
    private JTextField numCollectionMethods;

    // All action listeners
    private MainMenuButtonListener btnListener;
    private TrackPageEventHandler eventHandler;

    //All constant fields
    private static final int WIDTH = 1180;
    private static final int HEIGHT = 780;
    private static final String HOME_PAGE = "data/images/main_edited.png";
    private static final String BACKGROUND_IMAGE = "data/images/background.png";
    private static final Font OPTIONS_FONT = new Font("Helvetica", Font.PLAIN, 20);

    /*
     * MODIFIES: this
     * EFFECTS: constructs an instrance of the gui and sets up and runs the
     * application.
     */
    public PeriodTrackerController() {
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
        leftMenuPanel = new LeftMenuPanel(this, HEIGHT);
        lowerMenuPanel = new LowerMenuPanel(this);
        homeSetup();

        mainWindow.add(leftMenuPanel, BorderLayout.WEST);
        mainWindow.add(lowerMenuPanel, BorderLayout.SOUTH);

        run();
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up action listeners to the main panels to run the program.
     */
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
        centerDisplay = new JLabel(new ImageIcon(HOME_PAGE));
        mainWindow.add(centerDisplay, BorderLayout.CENTER);
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

    /*
     * REQUIRES: date must be in the format yyyy-m-d and must not be null.
     * EFFECTS: parses the String date and returns a LocalDate object.
     */
    private LocalDate parseDate(String date) {
        return (LocalDate.parse(date));
    }

    /*
     * TODO:
     * need to add action/event listeners to the buttons - DONE
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
            String actionCommand = e.getActionCommand();

            switch(actionCommand) {
                case "Track":
                    trackAction();
                    break;
                case "Modify":
                    modifyAction();
                    break;
                case "Analyze":
                    analyzeAction();
                    break;
                case "View":
                    viewAction();
                    break;    
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
            centerDisplay.setIcon(new ImageIcon(BACKGROUND_IMAGE));
        }

        /*
         * MODIFIES: this
         * EFFECTS: performs the analyze action when user clicks on the analyze button.
         */
        private void analyzeAction() {
            centerDisplay.setIcon(new ImageIcon(BACKGROUND_IMAGE));
        }

        /*
         * MODIFIES: this
         * EFFECTS: performs the view action when user clicks on the view button.
         */
        private void viewAction() {
            centerDisplay.setIcon(new ImageIcon(BACKGROUND_IMAGE));
        }

        /*
         * MODIFIES: this
         * EFFECTS: takes user to the home page when user clicks on the home button.
         */
        private void homeAction() {
            centerDisplay.setIcon(new ImageIcon(HOME_PAGE));
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
         * EFFECTS: sets up the panels for the tracking page and adds them to the main window.
         */
        private void trackPageSetup() {
            centerDisplay.setLayout(new BorderLayout());
            centerDisplay.setIcon(new ImageIcon(BACKGROUND_IMAGE));
            centerDisplay.setBorder(null);

            trackPageLogPanel = new JPanel(new GridLayout(6, 1, 10, 0));
            trackPageLogPanel.setOpaque(false);
            
            setTopRowAttributes();
            setLogHeavinessAttributes();
            setLogCollectionMethodAttributes();
            setLogNumCollectionMethodAttributes();
            setLogPainAreasAttributes();
            setLogBreastHealthAttributes();
            setLogFeelingsAttributes();

            // Adding all logging panels to the main panel
            trackPageLogPanel.add(trackPageLogHeavinessPanel);
            trackPageLogPanel.add(trackPageLogCollectionMethodPanel);
            trackPageLogPanel.add(trackPageLogNumCollectionMethodPanel);
            trackPageLogPanel.add(trackPageLogPainAreasPanel);
            trackPageLogPanel.add(trackPageLogBreastHealthPanel);
            trackPageLogPanel.add(trackPageLogFeelingsPanel);

            centerDisplay.add(trackPageTopRow, BorderLayout.NORTH);
            centerDisplay.add(trackPageLogPanel, BorderLayout.CENTER);

            System.out.println("Insets centerDisplay: " + centerDisplay.getInsets());
            System.out.println("Insets trackPageLogPanel: " + trackPageLogPanel.getInsets());
            System.out.println("Insets trackPageLogCollectionMethodPanel: " + trackPageLogCollectionMethodPanel.getInsets());
            System.out.println("Insets mainWindow: " + mainWindow.getInsets());

            mainWindow.add(centerDisplay, BorderLayout.CENTER);

            mainWindow.setVisible(true);
            mainWindow.revalidate();
            mainWindow.repaint();
        }

        /*
         * MODIFIES: this
         * EFFECTS: sets up attributes of all the components in the track page.
         */
        private void setTopRowAttributes() {
            trackPageTopRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
            trackPageTopRow.setOpaque(false);

            dateLabel = new JLabel("Enter the date: ");
            dateLabel.setFont(new Font("Helvetica", Font.BOLD, 25));

            dateField = new JTextField(15);
            dateField.setFont(new Font("Helvetica", Font.PLAIN, 25));
            dateField.setToolTipText("Enter the date in yyyy/m/d format");

            dateButton = new JButton("Track");
            dateButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
            dateButton.setMargin(new Insets(5, 5, 5, 5));
            //dateButton.set
            //dateButton.setPreferredSize(new Dimension(90, 50));

            trackPageTopRow.add(dateLabel);
            trackPageTopRow.add(dateField);
            trackPageTopRow.add(dateButton);
        }

        /*
         * MODIFIES: this
         * EFFECTS: sets up the heaviness panel's attributes.
         */
        private void setLogHeavinessAttributes() {
            trackPageLogHeavinessPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
            setJpanelAttributes(trackPageLogHeavinessPanel);

            logHeaviness = new JLabel("Heaviness: ");
            heavinessZero = new JRadioButton("0");
            heavinessOne = new JRadioButton("1");
            heavinessTwo = new JRadioButton("2");
            heavinessThree = new JRadioButton("3");
            heavinessFour = new JRadioButton("4");

            trackPageLogHeavinessPanel.add(logHeaviness);
            trackPageLogHeavinessPanel.add(heavinessZero);
            trackPageLogHeavinessPanel.add(heavinessOne);
            trackPageLogHeavinessPanel.add(heavinessTwo);
            trackPageLogHeavinessPanel.add(heavinessThree);
            trackPageLogHeavinessPanel.add(heavinessFour);

            ButtonGroup heavinessButtonGroup = new ButtonGroup();

            addRadioButtonsToButtonGroupSetFont(trackPageLogHeavinessPanel, heavinessButtonGroup);
            setFont(trackPageLogHeavinessPanel, OPTIONS_FONT);

            logHeaviness.setFont(new Font("Helvetica", Font.BOLD, 20));

            // setVisible(trackPageLogHeavinessPanel, false);
        }

        /*
         * MODIFIES: this
         * EFFECTS: sets up the collection method panel's attributes.
         */
        private void setLogCollectionMethodAttributes() {
            trackPageLogCollectionMethodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
            setJpanelAttributes(trackPageLogCollectionMethodPanel);

            logCollectionMethod = new JLabel("Collection Method: ");
            pads = new JRadioButton("Pads");
            tampons = new JRadioButton("Tampons");
            liners = new JRadioButton("Liners");
            periodCup = new JRadioButton("Period Cup");
            periodUnderwear = new JRadioButton("Period Underwear");

            trackPageLogCollectionMethodPanel.add(logCollectionMethod);
            trackPageLogCollectionMethodPanel.add(pads);
            trackPageLogCollectionMethodPanel.add(tampons);
            trackPageLogCollectionMethodPanel.add(liners);
            trackPageLogCollectionMethodPanel.add(periodCup);
            trackPageLogCollectionMethodPanel.add(periodUnderwear);

            ButtonGroup collectionMethodButtonGroup = new ButtonGroup();

            addRadioButtonsToButtonGroupSetFont(trackPageLogCollectionMethodPanel, collectionMethodButtonGroup);
            setFont(trackPageLogCollectionMethodPanel, OPTIONS_FONT);

            logCollectionMethod.setFont(new Font("Helvetica", Font.BOLD, 20));

            // setVisible(trackPageLogCollectionMethodPanel, false);
        }

        private void setLogNumCollectionMethodAttributes() {
            trackPageLogNumCollectionMethodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
            setJpanelAttributes(trackPageLogNumCollectionMethodPanel);

            logNumCollectionMethod = new JLabel("Number of products used: ");
            numCollectionMethods = new JTextField(10);

            trackPageLogNumCollectionMethodPanel.add(logNumCollectionMethod);
            trackPageLogNumCollectionMethodPanel.add(numCollectionMethods);

            logNumCollectionMethod.setFont(new Font("Helvetica", Font.BOLD, 20));

            // setVisible(trackPageLogNumCollectionMethodPanel, false);
        }

        /*
         * MODIFIES: this
         * EFFECTS: sets up the pain areas' panel's attributes.
         */
        private void setLogPainAreasAttributes() {
            trackPageLogPainAreasPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
            setJpanelAttributes(trackPageLogPainAreasPanel);

            logPainAreas = new JLabel("Pain Areas: ");
            backPain = new JCheckBox("Back");
            headPain = new JCheckBox("Head");
            breastPain = new JCheckBox("Breast");
            legsPain = new JCheckBox("Legs");
            jointsPain = new JCheckBox("Joints");
            vulvarPain = new JCheckBox("Vulvar");
            ovularPain = new JCheckBox("Ovulation");

            trackPageLogPainAreasPanel.add(logPainAreas);
            trackPageLogPainAreasPanel.add(backPain);
            trackPageLogPainAreasPanel.add(headPain);
            trackPageLogPainAreasPanel.add(breastPain);
            trackPageLogPainAreasPanel.add(legsPain);
            trackPageLogPainAreasPanel.add(jointsPain);
            trackPageLogPainAreasPanel.add(vulvarPain);
            trackPageLogPainAreasPanel.add(ovularPain);

            setFont(trackPageLogPainAreasPanel, OPTIONS_FONT);

            logPainAreas.setFont(new Font("Helvetica", Font.BOLD, 20));

            // setVisible(trackPageLogPainAreasPanel, false);
        }

        /*
         * MODIFIES: this
         * EFFECTS: sets up the breast health panel's attributes.
         */
        private void setLogBreastHealthAttributes() {
            trackPageLogBreastHealthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
            setJpanelAttributes(trackPageLogBreastHealthPanel);

            logBreastHealth = new JLabel("Breast Health: ");
            healthyBreasts = new JCheckBox("Healthy");
            lumpyBreasts = new JCheckBox("Lumpy");
            swollenBreasts = new JCheckBox("Swollen");
            tenderBreasts = new JCheckBox("Tender");

            trackPageLogBreastHealthPanel.add(logBreastHealth);
            trackPageLogBreastHealthPanel.add(healthyBreasts);
            trackPageLogBreastHealthPanel.add(lumpyBreasts);
            trackPageLogBreastHealthPanel.add(swollenBreasts);
            trackPageLogBreastHealthPanel.add(tenderBreasts);

            setFont(trackPageLogBreastHealthPanel, OPTIONS_FONT);

            logBreastHealth.setFont(new Font("Helvetica", Font.BOLD, 20));

            // setVisible(trackPageLogBreastHealthPanel, false);
        }

        private void setLogFeelingsAttributes() {
            trackPageLogFeelingsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
            setJpanelAttributes(trackPageLogFeelingsPanel);

            logFeelings = new JLabel("Feelings: ");
            happy = new JCheckBox("Happy");
            sad = new JCheckBox("Sad");
            sensitive = new JCheckBox("Sensitive");
            anxious = new JCheckBox("Anxious");
            angry = new JCheckBox("Angry");
            moodSwings = new JCheckBox("Mood Swings");

            trackPageLogFeelingsPanel.add(logFeelings);
            trackPageLogFeelingsPanel.add(happy);
            trackPageLogFeelingsPanel.add(sad);
            trackPageLogFeelingsPanel.add(sensitive);
            trackPageLogFeelingsPanel.add(anxious);
            trackPageLogFeelingsPanel.add(angry);
            trackPageLogFeelingsPanel.add(moodSwings);

            setFont(trackPageLogFeelingsPanel, OPTIONS_FONT);

            logFeelings.setFont(new Font("Helvetica", Font.BOLD, 20));

            // setVisible(trackPageLogFeelingsPanel, false);
        }

        /*
         * REQUIRES: panel must not be null
         * MODIFIES: this
         * EFFECTS: sets up the panel's preferred size and opacity.
         */
        private void setJpanelAttributes(JPanel panel) {
            panel.setOpaque(false);
            panel.setPreferredSize(new Dimension(700, 200));
        }

        /*
         * REQUIRES: panel must not be null, btnGroup must not be null.
         * MODIFIES: this
         * EFFECTS: adds radio buttons in the panel to the btnGroup.
         */
        private void addRadioButtonsToButtonGroupSetFont(JPanel panel, ButtonGroup btnGroup) {
            for (Component c : panel.getComponents()) {
                if (c instanceof JRadioButton) {
                    JRadioButton radioBtn = (JRadioButton) c;
                    btnGroup.add(radioBtn);
                }
            }
        }

        /*
         * REQUIRES: panel must not be null, font must not null.
         * MODIFIES: this
         * EFFECTS: sets up font of all the components in the panel
         */
        private void setFont(JPanel panel, Font font) {
            for (Component c : panel.getComponents()) {
                c.setFont(font);
            }
        }

        /*
         * REQUIRES: panel must not be null.
         * MODIFIES: this
         * EFFECTS: sets up the visibility of all the components in the panel.
         */
        private void setVisible(JPanel panel, boolean visibility) {
            for (Component c : panel.getComponents()) {
                c.setVisible(visibility);
            }
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
                    centerDisplay.add(new JLabel("An entry already exists for this date, please try again!"), BorderLayout.CENTER);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
