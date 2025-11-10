package Game;

import Scenes.Page;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;

public class PageManager {
    private static HashSet<Page> openedPages;

    private PageManager() {
    }

    public static void init() {
        openedPages = new HashSet<>();
    }

    public static void switchPage(Page current, Page newPage) {
        switchPage(current, newPage, false);
    }

    public static void switchPage(Page current, Page newPage, boolean dispose) {
        if (current != null) {
            if (dispose) {
                disposePage(current);
            } else {
                hidePage(current);
            }
        }

        showPage(newPage);
    }

    public static void showPage(Page page) {
        if (page == null) return;
        if (!openedPages.contains(page)) {
            page.init();
            page.setVisible(true);
            openedPages.add(page);
        } else if (!page.isVisible()) {
            page.setVisible(true);
        }
    }

    public static void hidePage(Page page) {
        if (page != null) page.setVisible(false);
    }

    public static void disposePage(Page page) {
        if (page != null) {
            page.dispose();
            openedPages.remove(page);
        }
        if (openedPages.isEmpty()) {
            System.exit(0);
        }
    }

    public static void registerFrameCloseHandler(Page page, JFrame frame) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                disposePage(page);
            }
        });
    }


    public static HashSet<Page> getOpenedPages() {
        return openedPages;
    }
}
