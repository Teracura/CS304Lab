package Game;

import Graphics.Coordinate;

import java.awt.*;
import java.awt.event.*;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {
    private int inputState = 0;
    public Coordinate mouseGlobal = new Coordinate(0, 0);
    public Coordinate mouseOrtho = new Coordinate(0, 0);
    public int viewportWidth;
    public int viewportHeight;
    public boolean mousePressed;

    public double orthoLeft, orthoRight, orthoBottom, orthoTop;

    public void setOrthoBounds(double left, double right, double bottom, double top) {
        this.orthoLeft = left;
        this.orthoRight = right;
        this.orthoBottom = bottom;
        this.orthoTop = top;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> inputState = Input.set(inputState, Input.UP);
            case KeyEvent.VK_DOWN -> inputState = Input.set(inputState, Input.DOWN);
            case KeyEvent.VK_LEFT -> inputState = Input.set(inputState, Input.LEFT);
            case KeyEvent.VK_RIGHT -> inputState = Input.set(inputState, Input.RIGHT);
            case KeyEvent.VK_Z -> inputState = Input.set(inputState, Input.Z);
            case KeyEvent.VK_X -> inputState = Input.set(inputState, Input.X);
            case KeyEvent.VK_C -> inputState = Input.set(inputState, Input.C);
            case KeyEvent.VK_PLUS -> inputState = Input.set(inputState, Input.PLUS);
            case KeyEvent.VK_MINUS -> inputState = Input.set(inputState, Input.MINUS);
            case KeyEvent.VK_EQUALS -> inputState = Input.set(inputState, Input.EQUALS);
            case KeyEvent.VK_A ->  inputState = Input.set(inputState, Input.A);
            case KeyEvent.VK_D ->  inputState = Input.set(inputState, Input.D);
            case KeyEvent.VK_R ->  inputState = Input.set(inputState, Input.R);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> inputState = Input.clear(inputState, Input.UP);
            case KeyEvent.VK_DOWN -> inputState = Input.clear(inputState, Input.DOWN);
            case KeyEvent.VK_LEFT -> inputState = Input.clear(inputState, Input.LEFT);
            case KeyEvent.VK_RIGHT -> inputState = Input.clear(inputState, Input.RIGHT);
            case KeyEvent.VK_Z -> inputState = Input.clear(inputState, Input.Z);
            case KeyEvent.VK_X -> inputState = Input.clear(inputState, Input.X);
            case KeyEvent.VK_C -> inputState = Input.clear(inputState, Input.C);
            case KeyEvent.VK_PLUS -> inputState = Input.clear(inputState, Input.PLUS);
            case KeyEvent.VK_MINUS -> inputState = Input.clear(inputState, Input.MINUS);
            case KeyEvent.VK_EQUALS -> inputState = Input.clear(inputState, Input.EQUALS);
            case KeyEvent.VK_A ->  inputState = Input.clear(inputState, Input.A);
            case KeyEvent.VK_D ->  inputState = Input.clear(inputState, Input.D);
            case KeyEvent.VK_R ->  inputState = Input.clear(inputState, Input.R);
        }
    }

    public int getInputState() {
        return inputState;
    }

    @Override public void keyTyped(KeyEvent e) {}

    @Override public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
        inputState = Input.set(inputState, Input.MOUSE_LEFT);
        updateMouseCoordinates(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
        inputState = Input.clear(inputState, Input.MOUSE_LEFT);
        updateMouseCoordinates(e);
    }

    @Override public void mouseEntered(MouseEvent e) {}

    @Override public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        updateMouseCoordinates(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        updateMouseCoordinates(e);
    }

    private void updateMouseCoordinates(MouseEvent e) {
        int screenX = e.getX();
        int screenY = e.getY();
        mouseGlobal = new Coordinate(screenX, screenY);

        double nx = (double) screenX / viewportWidth;
        double ny = (double) screenY / viewportHeight;

        double orthoX = orthoLeft + nx * (orthoRight - orthoLeft);
        double orthoY = orthoTop - ny * (orthoTop - orthoBottom);

        mouseOrtho = new Coordinate(orthoX, orthoY);
    }


}
