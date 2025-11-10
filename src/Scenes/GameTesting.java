package Scenes;

import SceneRenderers.PhysicalRenderers.GameRenderer;
import Game.PageComponentAdapter;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameTesting implements Page {
    JFrame frame;
    GLCanvas canvas;
    GameRenderer renderer;

    @Override
    public void init() {
        frame = new JFrame("Second Quiz");
        setupFrame();
        addComponents();
        addListeners();
        renderPage();
        setupAnimator();

        Game.PageManager.registerFrameCloseHandler(this, frame);
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
        canvas = new GLCanvas();
        renderer = new GameRenderer();
        canvas.addGLEventListener(renderer);
        canvas.addKeyListener(renderer.inputHandler);
        canvas.addMouseListener(renderer.inputHandler);
        canvas.addMouseMotionListener(renderer.inputHandler);

        frame.add(canvas, BorderLayout.CENTER);
    }

    @Override
    public void addListeners() {
        frame.addComponentListener(new PageComponentAdapter(this));
    }

    @Override
    public void handleEvents(ActionEvent e) {
        var command = e.getActionCommand();

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
