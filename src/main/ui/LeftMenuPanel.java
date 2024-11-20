package ui;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;

/*
 * This class creates the left menu panel of the application.
 */
public class LeftMenuPanel extends JPanel {

    private int height;

    private ActionListener listener;

    private JButton track;
    private JButton modify;
    private JButton analyze;
    private JButton view;

    /*
     * REQUIRES: listener must not be empty, height = height of the application
     * window.
     * MODIFIES: this
     * EFFECTS: creates an object of the left menu panel.
     */
    public LeftMenuPanel(ActionListener listener, int height) {
        this.height = height;
        this.listener = listener;

        setup();
    }

    /*
     * MODIFIES: this, PeriodTrackerController
     * EFFECTS: sets up the main menu (left) of the application along with the
     * buttons in it.
     */
    private void setup() {
        this.setLayout(new GridLayout(4, 1, 0, 0));
        this.setPreferredSize(new Dimension(200, height));
        this.setBackground(new Color(248, 211, 224));
        this.setBorder(BorderFactory.createEmptyBorder());

        track = new JButton("Track");
        modify = new JButton("Modify");
        analyze = new JButton("Analyze");
        view = new JButton("View");

        this.add(track);
        this.add(modify);
        this.add(analyze);
        this.add(view);

        mainMenuButtonsSetup();
        addListener();
    }

    /*
     * MODIFIES: this, PeriodTrackerController
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
        for (Component c : this.getComponents()) {
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
     * MODIFIES: this, PeriodTrackerController
     * EFFECTS: adds the button listener to all components of this panel if the
     * component is an instance of JButton
     */
    private void addListener() {
        for (Component c : this.getComponents()) {
            if (c instanceof JButton) {
                JButton btn = (JButton) c;
                btn.addActionListener(listener);
            }
        }
    }
}
