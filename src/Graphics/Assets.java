package Graphics;

import javax.swing.*;
import java.awt.*;

public class Assets {
    public static void frameCenterButton(JFrame frame, JButton button) {
        button.setLocation((frame.getContentPane().getWidth() / 2) - (button.getWidth() / 2),
                (frame.getContentPane().getHeight() / 2) - (button.getHeight() / 2));
    }

    public static void frameCenterButton(JFrame frame, JButton button, int xOffset, int yOffset) {
        button.setLocation((frame.getContentPane().getWidth() / 2) - (button.getWidth() / 2) + xOffset,
                (frame.getContentPane().getHeight() / 2) - (button.getHeight() / 2) + yOffset);
    }

    public static void frameAddButton(JFrame frame, JButton button, int buttonWidth, int buttonHeight) {
        button.setSize(buttonWidth, buttonHeight);
        frame.add(button);
    }
    public static void frameAddButton(JLayeredPane layeredPane, JButton button, int buttonWidth, int buttonHeight, int index) {
        button.setSize(buttonWidth, buttonHeight);
        layeredPane.add(button, index);
    }

    public static void frameAddImageLabel(JFrame frame, JLabel imageLabel, ImageIcon image, int imageWidth, int imageHeight) {
        frameSetImage(imageLabel, image, imageWidth, imageHeight);
        frame.add(imageLabel);
    }

    public static void frameSetImage(JLabel imageLabel, ImageIcon image, int imageWidth, int imageHeight) {
        Image scaled = image.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaled));
        imageLabel.setBounds(0, 0, imageWidth, imageHeight);
    }
}
