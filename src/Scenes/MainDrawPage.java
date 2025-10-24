package Scenes;

import EventListeners.PageComponentAdapter;
import EventListeners.GLEventListeners.SimpleGLEventListener;
import Logic.PageManager;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainDrawPage implements Page {
    private JFrame frame;
    private JButton triangleButton;
    private JButton dotButton;
    private SimpleGLEventListener listener;
    private GLCanvas canvas;

    public MainDrawPage() {

    }

    @Override
    public void init() {
        frame = new JFrame("Main Draw Page");
        setupFrame();
        addComponents();
        addListeners();
        PageManager.registerFrameCloseHandler(this, frame);
    }

    @Override
    public void setupFrame() {
        frame.setSize(1080, 700);
        frame.setLayout(new BorderLayout());
    }

    @Override
    public void setupAnimator() {

    }


    @Override
    public void addComponents() {
        dotButton = new JButton("Draw Points");
        triangleButton = new JButton("Draw Triangle");

        canvas = new GLCanvas();
        canvas.setSize(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
        listener = new SimpleGLEventListener();
        canvas.addGLEventListener(listener);

        JLayeredPane layerPane = new JLayeredPane();
        frame.setContentPane(layerPane);
        layerPane.setLayout(new BorderLayout());

        layerPane.setOpaque(false);
        frame.setBackground(Color.CYAN);

        layerPane.add(canvas, BorderLayout.CENTER, 1);
        layerPane.add(triangleButton, BorderLayout.NORTH, 0);
        layerPane.add(dotButton, BorderLayout.SOUTH, 0);
    }

    @Override
    public void addListeners() {
        triangleButton.setActionCommand("draw-triangle");
        triangleButton.addActionListener(this::handleEvents);
        dotButton.setActionCommand("draw-points");
        dotButton.addActionListener(this::handleEvents);
        frame.addComponentListener(new PageComponentAdapter(this));
    }

    @Override
    public void handleEvents(ActionEvent e) {
        var command = e.getActionCommand();
        switch (command) {
            case "draw-triangle":
                listener.onDrawTriangle(canvas);
                break;
            case "draw-points":
                listener.onDrawPoints(canvas);
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
