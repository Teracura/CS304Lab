package Scenes;

import java.awt.event.ActionEvent;

public interface Page {
    void init();
    void setupFrame();
    void setupAnimator();
    void addComponents();

    void addListeners();

    void handleEvents(ActionEvent e);

    void dispose();

    void renderPage();

    boolean isVisible();

    void setVisible(boolean b);
}
