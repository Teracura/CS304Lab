package EventListeners.GLEventListeners;

import EventListeners.GLEventListeners.Enums.SolarSystemButtonEffect;
import Graphics.BasicShapes.Circle;
import Graphics.Color;
import Graphics.Coordinate;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class SolarSystemRenderer implements GLEventListener {

    GL2 gl;
    int staticList;
    SolarSystemButtonEffect effect = SolarSystemButtonEffect.DEFAULT;
    float rotationAmount;
    float scaleFactor = 1;
    float rotationAngle = 0;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        gl = glAutoDrawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 1);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-400, 400, -300, 300, -1, 1);
        var circle = new Circle(new Coordinate(0, 0), 50);
        var circle1 = new Circle(new Coordinate(0, 0), 100);
        var circle2 = new Circle(new Coordinate(0, 0), 150);
        var planet = new Circle(new Coordinate(100, 0), 15);
        var planet1 = new Circle(new Coordinate(150, 0), 10);

        staticList = gl.glGenLists(1);
        gl.glNewList(staticList, GL2.GL_COMPILE);
        Color.YELLOW.useColorGl(gl);
        circle.draw(gl, true);
        Color.WHITE.useColorGl(gl);
        circle1.draw(gl, false);
        circle2.draw(gl, false);
        Color.RED.useColorGl(gl);
        planet.draw(gl,true);
        Color.BLUE.useColorGl(gl);
        planet1.draw(gl,true);

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
                effect = SolarSystemButtonEffect.DEFAULT;
                break;
            case ZOOM_OUT:
                if (scaleFactor - 0.1 <= 0) {
                    scaleFactor = 0.01f;
                } else{
                    scaleFactor -= 0.1f;
                }
                effect = SolarSystemButtonEffect.DEFAULT;
                break;
            case ROTATE_CLOCKWISE:
                rotationAngle += rotationAmount;
                effect = SolarSystemButtonEffect.DEFAULT;
                break;
            case ROTATE_COUNTERCLOCKWISE:
                rotationAngle -= rotationAmount;
                effect = SolarSystemButtonEffect.DEFAULT;
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

    public void setEffect(SolarSystemButtonEffect effect) {
        this.effect = effect;
    }

    public void setRotationAmount(float rotationAmount) {
        this.rotationAmount = rotationAmount;
    }
}
