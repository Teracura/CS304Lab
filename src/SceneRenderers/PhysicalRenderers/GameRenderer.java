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

public class GameRenderer implements GLEventListener, GameLoop {

    MovementHandler movementHandler;
    public InputHandler inputHandler;
    LoopState loopState;
    GL2 gl;

    Coordinate playerPosition = new Coordinate(0, 0);
    Circle player;
    Coordinate playerSpeed = new Coordinate(0.5, 0.5);
    boolean sprint = false;

    Rectangle wall;

    Rectangle objective;
    Coordinate objectiveVelocity;
    double objectiveSpeed = 0.8;

    public GameRenderer() {
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

        movementHandler.bind(Input.LEFT, () -> movePlayer(new Coordinate(-playerSpeed.x(), 0)));
        movementHandler.bind(Input.RIGHT, () -> movePlayer(new Coordinate(playerSpeed.x(), 0)));
        movementHandler.bind(Input.UP, () -> movePlayer(new Coordinate(0, playerSpeed.y())));
        movementHandler.bind(Input.DOWN, () -> movePlayer(new Coordinate(0, -playerSpeed.y())));
        movementHandler.bind(Input.Z, () -> sprint = true);

        player = new Circle.Builder().setFill(true).setCenter(playerPosition).setRadius(10).setColor(Color.RED).build();
        wall = new Rectangle.Builder().setFill(true).setCenter(-30, 0).setWidth(30).setHeight(100).setColor(Color.BLUE).build();
        objective = new Rectangle.Builder().setFill(true).setCenter(30, 0).setWidth(100).setHeight(100).setColor(Color.GREEN).build();

        objectiveVelocity = RandomUtils.randomDirection().multiply(objectiveSpeed);
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
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void physicsUpdate() {
        movementHandler.update();
        moveObjective();
        checkPlayerObjectiveCollision();
    }

    public void movePlayer(Coordinate delta) {
        if (sprint) {
            delta = delta.multiply(2);
            sprint = false;
        }
        Coordinate newPos = playerPosition.add(delta);
        player.setCenter(newPos);
        Hitbox playerHitbox = player.getHitbox();
        if (playerHitbox.intersects(wall.getHitbox())) {
            player.setCenter(playerPosition);
        } else {
            playerPosition = newPos;
        }
    }


    private void moveObjective() {
        if (Math.random() < 0.02) {
            objectiveVelocity = RandomUtils.randomDirection().multiply(objectiveSpeed);
        }

        Coordinate newPos = objective.getCenter().add(objectiveVelocity);
        double halfWidth = objective.getWidth() / 2;
        double halfHeight = objective.getHeight() / 2;

        if (newPos.x() - halfWidth < -400) {
            newPos = new Coordinate(-400 + halfWidth, newPos.y());
            objectiveVelocity = new Coordinate(-objectiveVelocity.x(), objectiveVelocity.y());
        }
        if (newPos.x() + halfWidth > 400) {
            newPos = new Coordinate(400 - halfWidth, newPos.y());
            objectiveVelocity = new Coordinate(-objectiveVelocity.x(), objectiveVelocity.y());
        }
        if (newPos.y() - halfHeight < -300) {
            newPos = new Coordinate(newPos.x(), -300 + halfHeight);
            objectiveVelocity = new Coordinate(objectiveVelocity.x(), -objectiveVelocity.y());
        }
        if (newPos.y() + halfHeight > 300) {
            newPos = new Coordinate(newPos.x(), 300 - halfHeight);
            objectiveVelocity = new Coordinate(objectiveVelocity.x(), -objectiveVelocity.y());
        }

        objective.setCenter(newPos);
    }

    private void checkPlayerObjectiveCollision() {
        if (objective.getHitbox().contains(playerPosition)) {
            objective.setColor(Color.YELLOW);
        } else {
            objective.setColor(Color.GREEN);
        }
    }

    @Override
    public void renderUpdate(GL2 gl) {
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glPushMatrix();

        wall.draw(gl);
        objective.draw(gl);
        player.draw(gl);

        gl.glPopMatrix();
    }
}
