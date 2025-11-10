package Scenes;

import EventListeners.GLEventListeners.Enums.Effect;
import EventListeners.GLEventListeners.SecondQuizRenderer;
import EventListeners.PageComponentAdapter;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SecondQuiz implements Page {
    JFrame frame;
    JButton zoomInButton;
    JButton zoomOutButton;
    JButton rotateClockwiseButton;
    JButton rotateCounterClockwiseButton;
    JButton step5;
    JButton stepMinus5;
    JButton fly;
    JButton land;
    JTextField angle;
    JPanel bottonItems;
    JPanel topItems;
    GLCanvas canvas;
    SecondQuizRenderer renderer;

    @Override
    public void init() {
        frame = new JFrame("Second Quiz");
        setupFrame();
        addComponents();
        addListeners();
        renderPage();
        setupAnimator();

        Pages.PageManager.registerFrameCloseHandler(this, frame);
    }

    @Override
    public void setupFrame() {
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.black);
    }

    @Override
    public void setupAnimator() {
        FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.start();
    }

    @Override
    public void addComponents() {
        topItems = new JPanel();
        zoomInButton = new JButton("Zoom In");
        zoomOutButton = new JButton("Zoom Out");
        topItems.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        topItems.add(zoomInButton);
        topItems.add(zoomOutButton);

        frame.add(topItems, BorderLayout.NORTH);

        bottonItems = new JPanel();
        rotateClockwiseButton = new JButton("Rotate Clockwise");
        rotateCounterClockwiseButton = new JButton("Rotate Counter Clockwise");
        angle = new JTextField(5);
        step5 = new JButton("Step 5");
        stepMinus5 = new JButton("Step -5");
        fly = new  JButton("Fly");
        land = new  JButton("Land");
        bottonItems.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottonItems.add(step5);
        bottonItems.add(stepMinus5);
        bottonItems.add(land);
        bottonItems.add(fly);

        frame.add(bottonItems, BorderLayout.SOUTH);

        canvas = new GLCanvas();
        renderer = new SecondQuizRenderer();
        canvas.addGLEventListener(renderer);

        frame.add(canvas, BorderLayout.CENTER);
    }

    @Override
    public void addListeners() {
        frame.addComponentListener(new PageComponentAdapter(this));

        frame.addComponentListener(new PageComponentAdapter(this));

        zoomInButton.setActionCommand("zoom-in");
        zoomInButton.addActionListener(this::handleEvents);
        zoomOutButton.setActionCommand("zoom-out");
        zoomOutButton.addActionListener(this::handleEvents);
        rotateClockwiseButton.setActionCommand("rotate-clockwise");
        rotateClockwiseButton.addActionListener(this::handleEvents);
        rotateCounterClockwiseButton.setActionCommand("rotate-counter-clockwise");
        rotateCounterClockwiseButton.addActionListener(this::handleEvents);
        fly.setActionCommand("fly");
        fly.addActionListener(this::handleEvents);
        land.setActionCommand("land");
        land.addActionListener(this::handleEvents);
        step5.setActionCommand("step-5");
        step5.addActionListener(this::handleEvents);
        stepMinus5.setActionCommand("step-minus-5");
        stepMinus5.addActionListener(this::handleEvents);
    }

    @Override
    public void handleEvents(ActionEvent e) {
        var command = e.getActionCommand();
        try {
            switch (command) {
                case "zoom-in":
                    renderer.setEffect(Effect.ZOOM_IN);
                    break;
                case "zoom-out":
                    renderer.setEffect(Effect.ZOOM_OUT);
                    break;
                case "rotate-clockwise":
                    if (!angle.getText().isEmpty()){
                        renderer.setRotationAmount(Float.parseFloat(angle.getText()));
                    }
                    renderer.setEffect(Effect.ROTATE_CLOCKWISE);
                    break;
                case "rotate-counter-clockwise":
                    if (!angle.getText().isEmpty()){
                        renderer.setRotationAmount(Float.parseFloat(angle.getText()));
                    }
                    renderer.setEffect(Effect.ROTATE_COUNTERCLOCKWISE);
                    break;
                case "step-5":
                    renderer.setEffect(Effect.STEP_5);
                    break;
                case "step-minus-5":
                    renderer.setEffect(Effect.STEP_5_NEGATIVE);
                    break;
                case "fly":
                    renderer.setEffect(Effect.FLY);
                    break;
                case "land":
                    renderer.setEffect(Effect.FLY_NEGATIVE);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown command: " + command);
            }
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid angle: " + angle.getText());
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
