package EventListeners;

import Scenes.Page;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PageComponentAdapter extends ComponentAdapter {

    private final Page page;

    public PageComponentAdapter(Page page) {
        this.page = page;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        page.renderPage();
    }
}