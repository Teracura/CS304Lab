package EventListeners.GLEventListeners;

import Graphics.Circle;
import Graphics.Coordinate;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.awt.GLCanvas;

public class SimpleGLEventListener implements GLEventListener {
    double angle;
    Circle circle;
    Coordinate center = new Coordinate(250, 150);
    int radius = 70;

    boolean drawTriangle = false;
    boolean drawPoints = false;
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
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        if (drawTriangle) {
            drawTriangle(gl);
        } else if (drawPoints) {
            drawPoints(gl);
        }

        drawCircle(gl);
    }

    private void drawTriangle(GL2 gl) {
        gl.glColor3f(1, 0, 0);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(100, 100);
        gl.glVertex2f(200, 200);
        gl.glVertex2f(150, 250);
        gl.glEnd();
    }

    private void drawPoints(GL2 gl) {
        gl.glColor3f(0, 1, 0);
        gl.glPointSize(20);
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2f(100, 100);
        gl.glVertex2f(200, 200);
        gl.glVertex2f(150, 250);
        gl.glEnd();
    }

    private void drawCircle(GL2 gl) {
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
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }


    public void onDrawTriangle(GLCanvas canvas) {
        drawTriangle = true;
        drawPoints = false;
        canvas.display();
    }

    public void onDrawPoints(GLCanvas canvas) {
        drawTriangle = false;
        drawPoints = true;
        canvas.display();
    }
}
