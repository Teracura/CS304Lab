package EventListeners.GLEventListeners;

import Graphics.BasicShapes.Circle;
import Graphics.Coordinate;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class RotatingTrianglesRenderer implements GLEventListener {
    double angle;
    Circle circle;
    Coordinate center = new Coordinate(250, 150);
    int radius = 70;
    GL2 gl;

    int staticList;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        gl = glAutoDrawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 1);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, 500, 0, 300, -1, 1);
        angle = 0;
        circle = new Circle(center, radius);

        staticList = gl.glGenLists(1);
        gl.glNewList(staticList, GL2.GL_COMPILE);
        circle.draw(gl, true, 3, 210);
        gl.glEndList();
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glColor3f(1, 0, 0);
        gl.glCallList(staticList);

        gl.glColor3f(0, 1, 0);
        circle.draw(gl, false, 3, 0 + angle);
        gl.glColor3f(0, 0, 1);
        circle.draw(gl, false, 3, 15 + angle);
        gl.glColor3f(1, 1, 0);
        circle.draw(gl, false, 3, 30 + angle);
        gl.glColor3f(0, 1, 1);
        circle.draw(gl, false, 3, 45 + angle);
        gl.glColor3f(1, 0, 1);
        circle.draw(gl, false, 3, 60 + angle);
        gl.glColor3f(1, 1, 1);
        circle.draw(gl, false, 3, 75 + angle);
        gl.glColor3f(0.5f, 0.5f, 1);
        circle.draw(gl, false, 3, 90 + angle);
        gl.glColor3f(0.5f, 1, 0.5f);
        circle.draw(gl, false, 3, 115 + angle);
        gl.glColor3f(1, 0.5f, 0.5f);
        circle.draw(gl, false, 3, 130 + angle);
        gl.glColor3f(0.5f, 0f, 0.5f);
        circle.draw(gl, false, 3, 145 + angle);
        gl.glColor3f(0.5f, 0.5f, 0.5f);
        circle.draw(gl, false, 3, 160 + angle);
        gl.glColor3f(0.5f, 1f, 1f);
        circle.draw(gl, false, 3, 175 + angle);


        gl.glEnd();

        angle = (angle + 2) % 360;
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }
}
