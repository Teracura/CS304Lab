package Game;

import java.awt.event.*;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {
    private int inputState = 0; // all input flags
    public int mouseX, mouseY;
    public boolean mousePressed;

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
        }
    }

    public int getInputState() {
        return inputState;
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
        }
    }

    @Override public void keyTyped(KeyEvent e) {}

    @Override public void mouseClicked(MouseEvent e) {}

    @Override public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    @Override public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    @Override public void mouseEntered(MouseEvent e) {}

    @Override public void mouseExited(MouseEvent e) {}

    @Override public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
