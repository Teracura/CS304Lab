package Scenes;

import java.awt.event.ActionEvent;

public interface Page {
    void addComponents();

    void addListeners();

    void show();

    void hide();

    void handleEvents(ActionEvent e);

    void dispose();

    void renderPage();
}
