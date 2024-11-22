package ui;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import model.PeriodEntry;
import model.PeriodLog;

import java.util.List;

import java.awt.*;

/*
 * This class creates the View page of the application.
 */
public class ViewPage extends JPanel {
    private PeriodLog myLog;

    private Image backgroundImage;

    // All panels
    private JScrollPane scrollPane;
    private BackgroundPanel background;
    private JPanel topRowPanel;
    private JPanel viewPanel;

    private JLabel topRowLabel;

    private static final String BACKGROUND_IMAGE = "data/images/background.png";

    /*
     * MODIFIES: this
     * EFFECTS: creates an instance of the view panel and saves myLog.
     */
    public ViewPage(PeriodLog myLog) {
        this.myLog = myLog;

        setup();
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up all the components of the view panel.
     */
    private void setup() {
        setBackground();

        this.setLayout(new BorderLayout(0, 0));
        this.setBorder(BorderFactory.createEmptyBorder());

        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        background.setBorder(BorderFactory.createEmptyBorder());

        this.add(background);
        setTopRow();
        setViewLog();

        this.setOpaque(false);
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up the top row of the View panel.
     */
    private void setTopRow() {
        topRowPanel = new JPanel();
        topRowPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        topRowPanel.setOpaque(false);

        topRowLabel = new JLabel("Your Period Log");
        topRowLabel.setFont(new Font("Helevetica", Font.BOLD, 20));

        topRowPanel.add(topRowLabel);
        background.add(topRowPanel);
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up the panel that contains the log.
     */
    private void setViewLog() {
        viewPanel = new JPanel();
        viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
        viewPanel.setOpaque(false);

        addEntriesToViewPanel();
        setScrollPane();

        background.add(scrollPane);
        viewPanel.setOpaque(false);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds entries from myLog to the view panel.
     */
    private void addEntriesToViewPanel() {
        List<PeriodEntry> myLogList = myLog.getLog();

        for (int i = 0; i < myLogList.size(); i++) {
            PeriodEntry entry = myLogList.get(i);

            JLabel date = new JLabel("Date: " + entry.getDate());
            JLabel heaviness = new JLabel("Heaviness: " + entry.getHeaviness());
            JLabel collectionMethod = new JLabel("Collection Method: " + entry.getCollectionMethod());
            JLabel numProducts = new JLabel("Total number of products used: " + entry.getCollectionNumUsed());
            JLabel painAreas = new JLabel("Areas of pain: " + entry.getPain());
            JLabel feelings = new JLabel("Feelings: " + entry.getFeelingsList());
            JLabel breastHealth = new JLabel("Breast Health: " + entry.getBreastHealth());

            JPanel entryPanel = new JPanel();
            setPanelAttributes(entryPanel);

            entryPanel.add(date);
            entryPanel.add(heaviness);
            entryPanel.add(collectionMethod);
            entryPanel.add(numProducts);
            entryPanel.add(painAreas);
            entryPanel.add(feelings);
            entryPanel.add(breastHealth);

            setFont(entryPanel, new Font("Helevetica", Font.PLAIN, 20));
            setOpacity(entryPanel);

            viewPanel.add(entryPanel);
        }
    }

    /*
     * REQUIRES: updatedLog must not be empty
     * MODIFIES: this
     * EFFECTS: updates the view page as user saves/loads entries.
     */
    public void updateLog(PeriodLog updatedLog) {
        myLog = updatedLog;

        viewPanel.removeAll();
        addEntriesToViewPanel();
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds scroll pane to the view page.
     * Referenced from:
     * https://stackoverflow.com/questions/10346449/scrolling-a-jpanel
     */
    private void setScrollPane() {
        scrollPane = new JScrollPane(viewPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
     * REQUIRES: panel must not be null
     * MODIFIES: this
     * EFFECTS: sets up panel's attributes.
     */
    private void setPanelAttributes(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
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
     * EFFECTS: sets opacity of labels to false.
     */
    private void setOpacity(JPanel panel) {
        for (Component c : panel.getComponents()) {
            if (c instanceof JLabel) {
                JLabel label = (JLabel) c;
                label.setOpaque(false);
            }
        }
    }
}
