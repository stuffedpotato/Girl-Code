package ui;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;

/*
 * This class creates the home page of the application.
 */
public class HomePage extends JPanel {
    private Image homePageImage;
    private BackgroundPanel background;

    private static final String HOME_PAGE = "data/images/main_edited.png";

    /*
     * EFFECTS: creates an object of the home page of the application.
     */
    public HomePage() {
        this.setLayout(new BorderLayout());
        setBackground();
        this.add(background);
    }

    /*
     * Referenced from:
     * https://stackoverflow.com/questions/1466240/how-to-set-an-image-as-a-background-for-frame-in-swing-gui-of-java
     * Image taken from:
     * https://www.eka.care/services/be-in-sync-with-your-cycle-tracking-your-
     * menstrual-period
     * MODIFIES: this
     * EFFECTS: sets up the main image at the center of the application when the app
     * is first run.
     */
    private void setBackground() {
        try {
            homePageImage = ImageIO.read(new File(HOME_PAGE));
        } catch (IOException e) {
            System.out.println("Unable to access BACKGROUND_IMAGE");
        }
        
        background = new BackgroundPanel(homePageImage);
    }
}
