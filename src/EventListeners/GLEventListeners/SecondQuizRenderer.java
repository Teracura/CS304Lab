package EventListeners.GLEventListeners;

import EventListeners.GLEventListeners.Enums.Effect;
import Graphics.BasicShapes.Circle;
import Graphics.BasicShapes.Rectangle;
import Graphics.BasicShapes.Triangle;
import Graphics.Color;
import Graphics.Coordinate;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class SecondQuizRenderer implements GLEventListener {
    GL2 gl;
    int staticList;
    int dynamicList;
    Effect effect = Effect.DEFAULT;
    float rotationAmount;
    float scaleFactor = 1;
    float rotationAngle = 0;
    float deltaX = 0;
    float deltaY = 0;
    Triangle triangle;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        gl = glAutoDrawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 1);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-400, 400, -300, 300, -1, 1);

        staticList = gl.glGenLists(1);

        Rectangle rectangle = new Rectangle.Builder().setFill(true).setCenter(0,-300).setHeight(100).setWidth(1000).setColor(Color.GRAY).build();
        triangle = new Triangle(new Coordinate(0,0), 100, 100, rotationAngle);


        gl.glNewList(staticList, GL2.GL_COMPILE);
        rectangle.draw(gl);


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
            case STEP_5:
                rotationAngle += 5f;
                effect = Effect.DEFAULT;
                break;
            case STEP_5_NEGATIVE:
                rotationAngle -= 5f;
                effect = Effect.DEFAULT;
                break;
            case FLY:
                deltaY += (float) (10 * Math.cos(Math.toRadians(rotationAngle)));
                deltaX += (float) (10 * -Math.sin(Math.toRadians(rotationAngle)));
                effect = Effect.DEFAULT;
                break;
            case FLY_NEGATIVE:
                deltaY -= (float) (10 * Math.cos(Math.toRadians(rotationAngle)));
                deltaX -= (float) (10 * -Math.sin(Math.toRadians(rotationAngle)));
                effect = Effect.DEFAULT;
                break;
        }

        gl.glScalef(scaleFactor, scaleFactor, scaleFactor);
        gl.glCallList(staticList);
        triangle.setAngle(rotationAngle);
        gl.glTranslatef(deltaX, deltaY - 200, 0);


        triangle.draw(gl,true,Color.RED);




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
