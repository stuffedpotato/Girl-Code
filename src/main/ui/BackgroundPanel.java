package ui;

import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;

/*
 * Referenced from: https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
 * Extends JPanel to add an image as a background.
 */
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    /*
     * REQUIRES: image must not be null
     * EFFECTS: Creates an instance of the BackgroundPanel and sets backgroundImage
     * to image.
     */
    public BackgroundPanel(Image image) {
        this.backgroundImage = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    /*
     * REQUIRES: image must not be null.
     * MODIFIES: this
     * EFFECTS: sets backgroundImage to image.
     */
    public void setImage(Image image) {
        backgroundImage = image;
    }
}
