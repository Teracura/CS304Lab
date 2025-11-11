package SceneRenderers.PhysicalRenderers;

import Game.GameLoop;
import Game.Input;
import Game.InputHandler;
import Game.LoopState;
import Graphics.BasicShapes.Circle;
import Graphics.BasicShapes.Rectangle;
import Graphics.Color;
import Graphics.Coordinate;
import Physics.Collision.Hitbox;
import Physics.MovementHandler;
import Physics.RandomUtils;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class ThirdQuizRenderer implements GLEventListener, GameLoop {

    MovementHandler movementHandler;
    public InputHandler inputHandler;
    LoopState loopState;
    GL2 gl;

    Rectangle player;

    double rotationValue = 0.1;

    public ThirdQuizRenderer() {
        inputHandler = new InputHandler();
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        loopState = new LoopState();
        movementHandler = new MovementHandler(inputHandler);
        gl = glAutoDrawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 1);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        inputHandler.setOrthoBounds(-400, 400, -300, 300);
        gl.glOrtho(-400, 400, -300, 300, -1, 1);

        movementHandler.bind(Input.EQUALS, this::increaseRotation);
        movementHandler.bind(Input.PLUS, this::increaseRotation);
        movementHandler.bind(Input.MINUS, this::decreaseRotation);
        movementHandler.bind(Input.R, this::reset);
        movementHandler.bind(Input.A, this::rotateCounter);
        movementHandler.bind(Input.D, this::rotateClock);


        player = new Rectangle.Builder().setFill(true).setCenter(0, 0).setWidth(100).setHeight(100).setColor(Color.RED).build();
    }

    private void reset() {
        rotationValue = 0.1;
        player.setRotation(0);
    }

    private void decreaseRotation() {
        if (rotationValue <= 0) {
            rotationValue += 0.01;
            if (rotationValue > 0) {
                rotationValue = -0.01;
            }
        } else {
            rotationValue -= 0.01;
            if (rotationValue < 0) {
                rotationValue = 0.01;
            }
        }
    }

    private void increaseRotation() {
        if (rotationValue <= 0) {
            rotationValue -= 0.01;
        } else{
            rotationValue += 0.01;
        }
    }

    private void rotateCounter() {
        if (rotationValue < 0){
            rotationValue *= -1;
        }
    }

    private void rotateClock(){
        if (rotationValue > 0){
            rotationValue *= -1;
        }
    }

    private void rotate(){
        player.setRotation(player.getRotation() + rotationValue);
    }


    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        handleLoop(loopState, gl);
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int w, int h) {
        gl.glViewport(0, 0, w, h);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        float aspect = (float) w / h;
        float viewHeight = 600;
        float viewWidth = viewHeight * aspect;

        gl.glOrtho(-viewWidth / 2, viewWidth / 2, -viewHeight / 2, viewHeight / 2, -1, 1);
        inputHandler.setOrthoBounds(-viewWidth / 2, viewWidth / 2, -viewHeight / 2, viewHeight / 2);

        inputHandler.viewportWidth = w;
        inputHandler.viewportHeight = h;

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void physicsUpdate() {
        movementHandler.update();
        rotate();
    }



    @Override
    public void renderUpdate(GL2 gl) {
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glPushMatrix();
        player.draw(gl);

        gl.glPopMatrix();
    }
}
