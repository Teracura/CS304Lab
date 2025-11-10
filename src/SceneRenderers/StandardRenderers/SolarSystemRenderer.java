package SceneRenderers.StandardRenderers;

import Physics.Effect;
import Graphics.BasicShapes.Circle;
import Graphics.Color;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class SolarSystemRenderer implements GLEventListener {

    GL2 gl;
    int staticList;
    Effect effect = Effect.DEFAULT;
    float rotationAmount = 0;
    float scaleFactor = 1;
    float rotationAngle = 0;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        gl = glAutoDrawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 1);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-400, 400, -300, 300, -1, 1);

        Circle circle = new Circle.Builder()
                .setCenter(0, 0)
                .setRadius(50)
                .setColor(Color.YELLOW)
                .setFill(true)
                .build();

        Circle circle1 = new Circle.Builder()
                .setCenter(0, 0)
                .setRadius(100)
                .setColor(Color.WHITE)
                .build();

        Circle circle2 = new Circle.Builder()
                .setCenter(0, 0)
                .setRadius(150)
                .setColor(Color.WHITE)
                .build();

        Circle planet = new Circle.Builder()
                .setCenter(100, 0)
                .setRadius(15)
                .setColor(Color.RED)
                .setFill(true)
                .build();

        Circle planet1 = new Circle.Builder()
                .setCenter(150, 0)
                .setRadius(10)
                .setColor(Color.BLUE)
                .setFill(true)
                .build();

        staticList = gl.glGenLists(1);
        gl.glNewList(staticList, GL2.GL_COMPILE);

        circle.draw(gl);
        circle1.draw(gl);
        circle2.draw(gl);
        planet.draw(gl);
        planet1.draw(gl);

        gl.glEndList();

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glPushMatrix();

        switch (effect) {
            case DEFAULT:
                break;
            case ZOOM_IN:
                scaleFactor += 0.1f;
                effect = Effect.DEFAULT;
                break;
            case ZOOM_OUT:
                if (scaleFactor - 0.1 <= 0) {
                    scaleFactor = 0.01f;
                } else {
                    scaleFactor -= 0.1f;
                }
                effect = Effect.DEFAULT;
                break;
            case ROTATE_CLOCKWISE:
                rotationAngle += rotationAmount;
                rotationAngle %= 360;
                effect = Effect.DEFAULT;
                break;
            case ROTATE_COUNTERCLOCKWISE:
                rotationAngle -= rotationAmount;
                rotationAngle %= 360;
                effect = Effect.DEFAULT;
                break;
            case STEP_5:
                rotationAmount += 5f;
                effect = Effect.DEFAULT;
                break;
            case STEP_5_NEGATIVE:
                rotationAmount -= 5f;
                effect = Effect.DEFAULT;
                break;

        }
        gl.glRotatef(rotationAngle, 0, 0, 1);
        gl.glScalef(scaleFactor, scaleFactor, scaleFactor);

        gl.glCallList(staticList);


        gl.glPopMatrix();
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
        gl.glLoadIdentity();
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public void setRotationAmount(float rotationAmount) {
        this.rotationAmount = rotationAmount;
    }
}
