package Scenes;

import EventListeners.GLEventListeners.TownDrawingRenderer;
import EventListeners.PageComponentAdapter;
import Logic.PageManager;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainDrawing implements Page {
    JFrame frame;
    GLEventListener townDrawingRenderer;
    GLCanvas canvas;

    @Override
    public void init() {
        frame = new JFrame("Main Drawing Page");

        setupFrame();
        addComponents();
        addListeners();
        renderPage();
        startAnimation();

        PageManager.registerFrameCloseHandler(this, frame);
    }

    private void startAnimation() {
        FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.start();
    }

    @Override
    public void setupFrame() {
        frame.setSize(1600, 1000);
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.black);
    }

    @Override
    public void addComponents() {
        canvas = new GLCanvas();
        townDrawingRenderer = new TownDrawingRenderer();
        canvas.addGLEventListener(townDrawingRenderer);
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
        canvas.setSize(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
        canvas.setBounds(0, 0, frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
