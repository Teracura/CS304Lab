package Scenes;

import EventListeners.GLEventListeners.PyramidsSceneRenderer;
import EventListeners.PageComponentAdapter;
import Pages.PageManager;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Pyramids implements Page {
    JFrame frame;
    GLCanvas canvas;
    PyramidsSceneRenderer renderer;

    @Override
    public void init() {
        frame = new JFrame("Pyramids");
        setupFrame();
        addComponents();
        addListeners();
        renderPage();
        setupAnimator();

        PageManager.registerFrameCloseHandler(this, frame);
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
        renderer = new PyramidsSceneRenderer();
        canvas.addGLEventListener(renderer);

        frame.add(canvas, BorderLayout.CENTER);
    }

    @Override
    public void addListeners() {
        frame.addComponentListener(new PageComponentAdapter(this));
    }

    @Override
    public void handleEvents(ActionEvent e) {

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
