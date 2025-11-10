package SceneRenderers.PhysicalRenderers;

import Game.GameLoop;
import Game.Input;
import Game.InputHandler;
import Game.LoopState;
import Graphics.BasicShapes.Circle;
import Graphics.Color;
import Graphics.Coordinate;
import Physics.MovementHandler;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class GameRenderer implements GLEventListener, GameLoop {
    MovementHandler movementHandler;
    public InputHandler inputHandler;
    LoopState loopState;
    GL2 gl;
    Coordinate speed = new Coordinate(5, 5);
    Coordinate playerPosition = new Coordinate(0, 0);
    Circle player;

    public GameRenderer(){
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
        gl.glOrtho(-400, 400, -300, 300, -1, 1);

        movementHandler.bind(Input.LEFT, () -> move(speed.multiply(new Coordinate(-1, 0))));
        movementHandler.bind(Input.RIGHT, () -> move(speed.multiply(new Coordinate(1, 0))));
        movementHandler.bind(Input.UP, () -> move(speed.multiply(new Coordinate(0, 1))));
        movementHandler.bind(Input.DOWN, () -> move(speed.multiply(new Coordinate(0, -1))));
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

        float aspect = (float) w / (float) h;
        float viewHeight = 600;
        float viewWidth = viewHeight * aspect;

        gl.glOrtho(-viewWidth / 2, viewWidth / 2, -viewHeight / 2, viewHeight / 2, -1, 1);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        player = new Circle.Builder().setFill(true).setCenter(playerPosition).setRadius(10).setColor(Color.RED).build();
        gl.glLoadIdentity();
    }

    @Override
    public void physicsUpdate() {
        movementHandler.update();
    }

    @Override
    public void renderUpdate(GL2 gl) {
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glPushMatrix();

        player.draw(gl);

        gl.glPopMatrix();

    }

    public void move(Coordinate speed){
        playerPosition = playerPosition.add(speed);
        player.setCenter(playerPosition);
    }
}
