package EventListeners.GLEventListeners;

import Graphics.BasicShapes.*;
import Graphics.Color;
import Graphics.Coordinate;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.util.packrect.Rect;

public class FirstQuizRenderer implements GLEventListener {
    GL2 gl;

    int staticList;
    Coordinate center = new Coordinate(500, 300);

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        gl = glAutoDrawable.getGL().getGL2();

        Color.BLACK.clearColorGl(gl);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, 1000, 0, 600, -1, 1);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        staticList = gl.glGenLists(2);

        Circle circle = new Circle.Builder()
                .setCenter(center.x() + 200, 150 + 70)
                .setRadius(70)
                .setColor(Color.DARK_GRAY)
                .build();

        Circle circle1 = new Circle.Builder()
                .setCenter(center.x() - 200, 150 + 70)
                .setRadius(70)
                .setColor(Color.DARK_GRAY)
                .build();

        Circle circle2 = new Circle.Builder()
                .setCenter(center.x() + 280, 150 + 135)
                .setRadius(25)
                .setColor(Color.YELLOW)
                .build();

        Rectangle rectangle = new Rectangle(new Coordinate(center.x(), 150 + 35 + 100), 600, 115);
        Rectangle rectangle1 = new Rectangle(new Coordinate(center.x() - 300, 150 + 35 + 100 + 50), 20, 20);
        Rectangle rectangle2 = new Rectangle(new Coordinate(center.x(), center.y() + 65), 210, 90);
        Rectangle rectangle3 = new Rectangle(new Coordinate(center.x(), center.y() + 75), 100, 50);

        Triangle triangle = new Triangle(new Coordinate(center.x() - 100, center.y() + 65), 140, 90, 0);
        Triangle triangle1 = new Triangle(new Coordinate(center.x() + 100, center.y() + 65), 140, 90, 0);
        Triangle triangle2 = new Triangle(new Coordinate(center.x() - 100, center.y() + 75), 60, 60);


        gl.glNewList(staticList, GL2.GL_COMPILE);
        triangle.draw(gl, true, new Color(0.5, 0, 0, 1));
        triangle1.draw(gl, true, new Color(0.5, 0, 0, 1));
        rectangle2.draw(gl, true, new Color(0.5, 0, 0, 1));
        rectangle.draw(gl, true, Color.RED);
        rectangle1.draw(gl, true, Color.GRAY);

        circle.draw(gl, true);
        circle1.draw(gl, true);

        circle2.draw(gl, true);

        rectangle3.draw(gl, true, Color.GRAY);
        triangle2.draw(gl, true, Color.GRAY);
        gl.glEndList();
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glCallList(staticList);

        gl.glBegin(GL2.GL_LINES);
        Color.ORANGE.useColorGl(gl);
        gl.glVertex2d(1000, 150);
        gl.glVertex2d(0, 150);
        Color.WHITE.useColorGl(gl);
        gl.glVertex2d(center.x() + 250, 150 + 150);
        gl.glVertex2d(center.x() - 250, 150 + 150);
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }
}
