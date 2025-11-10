package Scenes;

import EventListeners.GLEventListeners.RotatingTrianglesRenderer;
import EventListeners.PageComponentAdapter;
import Pages.PageManager;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import Graphics.Assets;

public class SimpleApp implements Page {
    private JFrame frame;
    private JButton exitButton;
    private JLayeredPane layerPane;
    private GLCanvas canvas;

    public SimpleApp() {

    }

    @Override
    public void init() {
        frame = new JFrame("Start Menu");
        layerPane = new JLayeredPane();
        setupFrame();
        addComponents();
        addListeners();
        setupAnimator();
        PageManager.registerFrameCloseHandler(this, frame);
    }
    @Override
    public void setupFrame() {
        frame.setSize(1600, 1000);
        frame.setLayout(new BorderLayout());
        frame.setContentPane(layerPane);
        layerPane.setLayout(null);
    }

    public void setupAnimator() {
        canvas = new GLCanvas();
        canvas.setSize(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
        GLEventListener listener = new RotatingTrianglesRenderer();
        canvas.addGLEventListener(listener);
        layerPane.add(canvas, 1);

        frame.addComponentListener(new PageComponentAdapter(this));

        layerPane.setOpaque(false);
        frame.setBackground(Color.BLACK);

        FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.start();
    }

    @Override
    public void addComponents() {
        exitButton = new JButton("Exit");
        int buttonWidth = frame.getContentPane().getWidth() / 4;
        int buttonHeight = frame.getContentPane().getHeight() / 10;
        Assets.frameAddButton(layerPane, exitButton, buttonWidth, buttonHeight, 0);
    }

    @Override
    public void addListeners() {
        frame.addComponentListener(new PageComponentAdapter(this));
        exitButton.setActionCommand("exit");
        exitButton.addActionListener(this::handleEvents);
    }

    @Override
    public void handleEvents(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "exit":
                PageManager.disposePage(this);
                break;
            default:
                throw new IllegalArgumentException("Unknown command: " + command);
        }
    }

    @Override
    public void dispose() {
        frame.dispose();
    }

    @Override
    public void renderPage() {
        canvas.setSize(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
        try {
            exitButton.setSize(frame.getContentPane().getWidth() / 4,
                    frame.getContentPane().getHeight() / 10);
            Assets.frameCenterButton(frame, exitButton, 0, frame.getHeight() / 4);
        } catch (NullPointerException ex) {
            System.out.println("Null pointer exception, make sure you've called addComponents() first for the page");
        }
    }

    @Override
    public boolean isVisible() {
        return frame.isVisible();
    }

    @Override
    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
