package ui;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;

public class TrackPage extends JPanel {
    private ActionListener listener;

    private Image backgroundImage;

    // All panels
    private BackgroundPanel background;
    private JPanel topRow;
    private JPanel logHeavinessPanel;
    private JPanel logCollectionMethodPanel;
    private JPanel logNumCollectionMethodPanel;
    private JPanel logPainAreasPanel;
    private JPanel logBreastHealthPanel;
    private JPanel logFeelingsPanel;

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

    private static final Font OPTIONS_FONT = new Font("Helvetica", Font.PLAIN, 20);
    private static final String BACKGROUND_IMAGE = "data/images/background.png";

    public TrackPage(ActionListener listener) {
        this.listener = listener;
        setup();
    }

    private void setup() {
        setBackground();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setTopRowAttributes();
        setLogHeavinessAttributes();
        setLogCollectionMethodAttributes();
        setLogNumCollectionMethodAttributes();
        setLogPainAreasAttributes();
        setLogBreastHealthAttributes();
        setLogFeelingsAttributes();

        this.add(background);
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        // Adding all logging panels to the main panel
        addPanelsToThis();
        this.setOpaque(false);
        addListener();
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up attributes of all the components in the track page.
     */
    private void setTopRowAttributes() {
        topRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        topRow.setOpaque(false);

        dateLabel = new JLabel("Enter the date: ");
        dateLabel.setFont(new Font("Helvetica", Font.BOLD, 25));

        dateField = new JTextField(15);
        dateField.setFont(new Font("Helvetica", Font.PLAIN, 25));
        dateField.setToolTipText("Enter the date in yyyy/m/d format");

        dateButton = new JButton("Track");
        dateButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
        dateButton.setMargin(new Insets(5, 5, 5, 5));

        topRow.add(dateLabel);
        topRow.add(dateField);
        topRow.add(dateButton);
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up the heaviness panel's attributes.
     */
    private void setLogHeavinessAttributes() {
        logHeavinessPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        setJpanelAttributes(logHeavinessPanel);

        logHeaviness = new JLabel("Heaviness: ");
        heavinessZero = new JRadioButton("0");
        heavinessOne = new JRadioButton("1");
        heavinessTwo = new JRadioButton("2");
        heavinessThree = new JRadioButton("3");
        heavinessFour = new JRadioButton("4");

        logHeavinessPanel.add(logHeaviness);
        logHeavinessPanel.add(heavinessZero);
        logHeavinessPanel.add(heavinessOne);
        logHeavinessPanel.add(heavinessTwo);
        logHeavinessPanel.add(heavinessThree);
        logHeavinessPanel.add(heavinessFour);

        ButtonGroup heavinessButtonGroup = new ButtonGroup();

        addRadioButtonsToButtonGroupSetFont(logHeavinessPanel, heavinessButtonGroup);
        setFont(logHeavinessPanel, OPTIONS_FONT);

        logHeaviness.setFont(new Font("Helvetica", Font.BOLD, 20));
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up the collection method panel's attributes.
     */
    private void setLogCollectionMethodAttributes() {
        logCollectionMethodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        setJpanelAttributes(logCollectionMethodPanel);

        logCollectionMethod = new JLabel("Collection Method: ");
        pads = new JRadioButton("Pads");
        tampons = new JRadioButton("Tampons");
        liners = new JRadioButton("Liners");
        periodCup = new JRadioButton("Period Cup");
        periodUnderwear = new JRadioButton("Period Underwear");

        logCollectionMethodPanel.add(logCollectionMethod);
        logCollectionMethodPanel.add(pads);
        logCollectionMethodPanel.add(tampons);
        logCollectionMethodPanel.add(liners);
        logCollectionMethodPanel.add(periodCup);
        logCollectionMethodPanel.add(periodUnderwear);

        ButtonGroup collectionMethodButtonGroup = new ButtonGroup();

        addRadioButtonsToButtonGroupSetFont(logCollectionMethodPanel, collectionMethodButtonGroup);
        setFont(logCollectionMethodPanel, OPTIONS_FONT);

        logCollectionMethod.setFont(new Font("Helvetica", Font.BOLD, 20));
    }

    /*
     * MODIFIES: this, PeriodTrackerController
     * EFFECTS: sets up the attributes to collect the number of collection method products used panel.
     */
    private void setLogNumCollectionMethodAttributes() {
        logNumCollectionMethodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        setJpanelAttributes(logNumCollectionMethodPanel);

        logNumCollectionMethod = new JLabel("Number of products used: ");
        numCollectionMethods = new JTextField(10);

        logNumCollectionMethodPanel.add(logNumCollectionMethod);
        logNumCollectionMethodPanel.add(numCollectionMethods);

        logNumCollectionMethod.setFont(new Font("Helvetica", Font.BOLD, 20));
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up the pain areas' panel's attributes.
     */
    private void setLogPainAreasAttributes() {
        logPainAreasPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        setJpanelAttributes(logPainAreasPanel);

        logPainAreas = new JLabel("Pain Areas: ");
        backPain = new JCheckBox("Back");
        headPain = new JCheckBox("Head");
        breastPain = new JCheckBox("Breast");
        legsPain = new JCheckBox("Legs");
        jointsPain = new JCheckBox("Joints");
        vulvarPain = new JCheckBox("Vulvar");
        ovularPain = new JCheckBox("Ovulation");

        logPainAreasPanel.add(logPainAreas);
        logPainAreasPanel.add(backPain);
        logPainAreasPanel.add(headPain);
        logPainAreasPanel.add(breastPain);
        logPainAreasPanel.add(legsPain);
        logPainAreasPanel.add(jointsPain);
        logPainAreasPanel.add(vulvarPain);
        logPainAreasPanel.add(ovularPain);

        setFont(logPainAreasPanel, OPTIONS_FONT);

        logPainAreas.setFont(new Font("Helvetica", Font.BOLD, 20));
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up the breast health panel's attributes.
     */
    private void setLogBreastHealthAttributes() {
        logBreastHealthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        setJpanelAttributes(logBreastHealthPanel);

        logBreastHealth = new JLabel("Breast Health: ");
        healthyBreasts = new JCheckBox("Healthy");
        lumpyBreasts = new JCheckBox("Lumpy");
        swollenBreasts = new JCheckBox("Swollen");
        tenderBreasts = new JCheckBox("Tender");

        logBreastHealthPanel.add(logBreastHealth);
        logBreastHealthPanel.add(healthyBreasts);
        logBreastHealthPanel.add(lumpyBreasts);
        logBreastHealthPanel.add(swollenBreasts);
        logBreastHealthPanel.add(tenderBreasts);

        setFont(logBreastHealthPanel, OPTIONS_FONT);

        logBreastHealth.setFont(new Font("Helvetica", Font.BOLD, 20));
    }

    private void setLogFeelingsAttributes() {
        logFeelingsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        setJpanelAttributes(logFeelingsPanel);

        logFeelings = new JLabel("Feelings: ");
        happy = new JCheckBox("Happy");
        sad = new JCheckBox("Sad");
        sensitive = new JCheckBox("Sensitive");
        anxious = new JCheckBox("Anxious");
        angry = new JCheckBox("Angry");
        moodSwings = new JCheckBox("Mood Swings");

        logFeelingsPanel.add(logFeelings);
        logFeelingsPanel.add(happy);
        logFeelingsPanel.add(sad);
        logFeelingsPanel.add(sensitive);
        logFeelingsPanel.add(anxious);
        logFeelingsPanel.add(angry);
        logFeelingsPanel.add(moodSwings);

        setFont(logFeelingsPanel, OPTIONS_FONT);

        logFeelings.setFont(new Font("Helvetica", Font.BOLD, 20));
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds all panels to this main panel (TrackPage).
     */
    private void addPanelsToThis() {
        background.add(topRow);
        background.add(logHeavinessPanel);
        background.add(logCollectionMethodPanel);
        background.add(logNumCollectionMethodPanel);
        background.add(logPainAreasPanel);
        background.add(logFeelingsPanel);
        background.add(logBreastHealthPanel);
    }

    private void addListener() {
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
     * Referenced from:
     * https://stackoverflow.com/questions/1466240/how-to-set-an-image-as-a-
     * background-for-frame-in-swing-gui-of-java
     * Image taken from:
     * https://www.eka.care/services/be-in-sync-with-your-cycle-tracking-your-
     * menstrual-period
     * MODIFIES: this
     * EFFECTS: sets up the main image at the center of the application when the app
     * is first run.
     */
    private void setBackground() {
        try {
            backgroundImage = ImageIO.read(new File(BACKGROUND_IMAGE));
        } catch (IOException e) {
            System.out.println("Unable to access BACKGROUND_IMAGE");
        }

        background = new BackgroundPanel(backgroundImage);
    }
}
