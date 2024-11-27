package ui;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;

/*
 * This class creates the tracking page of the application.
 */
public class TrackPage extends JPanel {
    private ActionListener listener;

    private Image backgroundImage;

    // All panels
    private BackgroundPanel background;
    private JPanel logAttributesPanel;
    private JPanel topRow;
    private JPanel logHeavinessPanel;
    private JPanel logCollectionMethodPanel;
    private JPanel logNumCollectionMethodPanel;
    private JPanel logPainAreasPanel;
    private JPanel logBreastHealthPanel;
    private JPanel logFeelingsPanel;

    // All buttons
    private JButton trackButton;
    private JButton saveButton;

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
    private ButtonGroup heavinessButtonGroup;

    // Collection method options
    private JRadioButton pads;
    private JRadioButton tampons;
    private JRadioButton liners;
    private JRadioButton periodCup;
    private JRadioButton periodUnderwear;
    private ButtonGroup collectionMethodButtonGroup;

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

    /*
     * REQUIRES: listener must not be null
     * EFFECTS: creates the tracking page and its components.
     */
    public TrackPage(ActionListener listener) {
        this.listener = listener;
        setup();
    }

    /*
     * MODIFIES: this, PeriodTrackerController
     * EFFECTS: sets up the main tracking display and all other panels inside it.
     */
    private void setup() {
        setBackground();

        this.setLayout(new BorderLayout(0, 0));
        this.setBorder(BorderFactory.createEmptyBorder());

        logAttributesPanel = new JPanel();
        logAttributesPanel.setLayout(new BoxLayout(logAttributesPanel, BoxLayout.Y_AXIS));

        setTopRowAttributes();
        setLogHeavinessAttributes();
        setLogCollectionMethodAttributes();
        setLogNumCollectionMethodAttributes();
        setLogPainAreasAttributes();
        setLogBreastHealthAttributes();
        setLogFeelingsAttributes();
        setSaveButtonAttributes();

        this.add(background);
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        background.setBorder(BorderFactory.createEmptyBorder());

        // Adding all logging panels to the main panel
        addPanelsToThis();
        this.setOpaque(false);
        logAttributesPanel.setOpaque(false);
        hideLogPanel();
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

        trackButton = new JButton("Track");
        trackButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
        trackButton.setMargin(new Insets(5, 5, 5, 5));

        topRow.add(dateLabel);
        topRow.add(dateField);
        topRow.add(trackButton);
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

        heavinessButtonGroup = new ButtonGroup();

        addRadioButtonsToButtonGroup(logHeavinessPanel, heavinessButtonGroup);
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

        collectionMethodButtonGroup = new ButtonGroup();

        addRadioButtonsToButtonGroup(logCollectionMethodPanel, collectionMethodButtonGroup);
        setFont(logCollectionMethodPanel, OPTIONS_FONT);

        logCollectionMethod.setFont(new Font("Helvetica", Font.BOLD, 20));
    }

    /*
     * MODIFIES: this, PeriodTrackerController
     * EFFECTS: sets up the attributes to collect the number of collection method
     * products used panel.
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

    /*
     * MODIFIES: this
     * EFFECTS: creates the attributes for the feelings panel.
     */
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
     * EFFECTS: creates the attributes of the saveButton of the tracking page.
     */
    private void setSaveButtonAttributes() {
        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        saveButton.setMargin(new Insets(10, 10, 10, 10));
        saveButton.setForeground(Color.RED);
        saveButton.setBorderPainted(true);
        saveButton.setToolTipText("Click here to save this entry");
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds all panels to this main panel (TrackPage).
     */
    private void addPanelsToThis() {
        background.add(topRow);
        logAttributesPanel.add(logHeavinessPanel);
        logAttributesPanel.add(logCollectionMethodPanel);
        logAttributesPanel.add(logNumCollectionMethodPanel);
        logAttributesPanel.add(logPainAreasPanel);
        logAttributesPanel.add(logFeelingsPanel);
        logAttributesPanel.add(logBreastHealthPanel);
        logAttributesPanel.add(saveButton);
        background.add(logAttributesPanel);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds listener to interactive buttons in this panel.
     */
    private void addListener() {
        trackButton.setActionCommand("checkDate");
        trackButton.addActionListener(listener);

        dateField.setActionCommand("checkDate");
        dateField.addActionListener(listener);

        saveButton.setActionCommand("saveButton");
        saveButton.addActionListener(listener);
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
    private void addRadioButtonsToButtonGroup(JPanel panel, ButtonGroup btnGroup) {
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

    /*
     * MODIFIES: this, PeriodTrackerController
     * EFFECTS: displays the logAttributesPanel
     */
    protected void showLogPanel() {
        logAttributesPanel.setVisible(true);
    }

    /*
     * MODIFIES: this, PeriodTrackerController
     * EFFECTS: hides the logAttributesPanel
     */
    protected void hideLogPanel() {
        logAttributesPanel.setVisible(false);
    }

    /*
     * MODIFIES: this, PeriodTrackerController
     * EFFECTS: resets the topRow and logAttributesPanel.
     */
    protected void resetLogPanel() {
        dateField.setText("");
        heavinessButtonGroup.clearSelection();
        collectionMethodButtonGroup.clearSelection();

        for (Component comp : logAttributesPanel.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;

                for (Component c : panel.getComponents()) {
                    if (c instanceof JTextField) {
                        JTextField textField = (JTextField) c;
                        textField.setText("");
                    } else if (c instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) c;
                        checkBox.setSelected(false);
                    }
                }
            }
        }
    }

    // Getters

    protected String getDate() {
        return dateField.getText();
    }

    protected int getHeavinessLevel() {
        if (heavinessZero.isSelected()) {
            return 0;
        } else if (heavinessOne.isSelected()) {
            return 1;
        } else if (heavinessTwo.isSelected()) {
            return 2;
        } else if (heavinessThree.isSelected()) {
            return 3;
        } else if (heavinessFour.isSelected()) {
            return 4;
        }

        return -1;
    }

    protected String getCollectionMethod() {
        if (pads.isSelected()) {
            return "Pads";
        } else if (tampons.isSelected()) {
            return "Tampons";
        } else if (liners.isSelected()) {
            return "Liners";
        } else if (periodCup.isSelected()) {
            return "Period cup";
        } else if (periodUnderwear.isSelected()) {
            return "Period underwear";
        }

        return "null";
    }

    protected int getCollectionNumUsed() {
        String text = numCollectionMethods.getText();

        if (text == "") {
            return 0;
        }

        return parseInt(text);
    }

    protected List<String> getPainAreas() {
        List<String> painAreas = new ArrayList<String>();

        for (Component c : logPainAreasPanel.getComponents()) {
            if (c instanceof JCheckBox) {
                JCheckBox checkbox = (JCheckBox) c;
                
                if (checkbox.isSelected()) {
                    painAreas.add("" + checkbox.getText());
                }
            }
        } 

        if (painAreas.size() == 0) {
            painAreas.add("none");
        }

        return painAreas;
    }

    protected List<String> getBreastHealth() {
        List<String> breastHealth = new ArrayList<String>();

        if (healthyBreasts.isSelected()) {
            breastHealth.add("healthy");
        }
        if (lumpyBreasts.isSelected()) {
            breastHealth.add("lumpy");
        }
        if (tenderBreasts.isSelected()) {
            breastHealth.add("tender");
        }
        if (swollenBreasts.isSelected()) {
            breastHealth.add("swollen");
        }

        return breastHealth;
    }

    protected List<String> getFeelings() {
        List<String> feelings = new ArrayList<String>();

        if (happy.isSelected()) {
            feelings.add("happy");
        }
        if (sad.isSelected()) {
            feelings.add("sad");
        }
        if (anxious.isSelected()) {
            feelings.add("anxious");
        }
        if (sensitive.isSelected()) {
            feelings.add("sensitive");
        }
        if (angry.isSelected()) {
            feelings.add("angry");
        }
        if (moodSwings.isSelected()) {
            feelings.add("mood swings");
        }

        return feelings;
    }

    /*
     * REQUIRES: text must not be null.
     * EFFECTS: parses String to int. if exception caught, returns 0.
     */
    private int parseInt(String text) {
        try {
            int num = Integer.parseInt(text);
            return num;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
