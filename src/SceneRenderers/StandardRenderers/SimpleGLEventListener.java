package SceneRenderers.StandardRenderers;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.awt.GLCanvas;

public class SimpleGLEventListener implements GLEventListener {
    boolean drawTriangle = false;
    boolean drawPoints = false;
    GL2 gl;


    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        gl = glAutoDrawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 1);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, 500, 0, 300, -1, 1);
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
