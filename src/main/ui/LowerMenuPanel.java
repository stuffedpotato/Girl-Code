package ui;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;

/*
 * This class creates the bottom menu panel of the application.
 */
public class LowerMenuPanel extends JPanel {
    private ActionListener listener;

    private JButton save;
    private JButton load;
    private JButton clear;
    private JButton home;

    /*
     * REQUIRES: listener must not be null
     * MODIFIES: this
     * EFFECTS: creates an object of the bottom menu panel of the application.
     */
    public LowerMenuPanel(ActionListener listener) {
        this.listener = listener;

        setup();
    }

    /*
     * MODIFIES: this, PeriodController
     * EFFECTS: sets up the home/save/load/clear functionality (bottom menu) of the
     * application.
     */
    private void setup() {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        this.setBackground(new Color(248, 211, 224));

        lowerMenuButtonsSetup();

        this.add(home);
        this.add(save);
        this.add(load);
        this.add(clear);

        addListener();
    }

    /*
     * MODIFIES: this, PeriodTrackerController
     * EFFECTS: sets up the buttons for the lower menu (home, save, load and clear)
     * of the application.
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

        save.setToolTipText("Save your work");
        load.setToolTipText("Load previously saved work");
        clear.setToolTipText("Clear all");

        clear.setForeground(Color.RED);
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
