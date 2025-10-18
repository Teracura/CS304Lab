package Graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class Circle{
    Coordinate center;
    int radius;

    public Circle(Coordinate center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public Circle(int x, int y, int radius) {
        this.center = new Coordinate(x, y);
        this.radius = radius;
    }

    public Coordinate getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public void draw(GL2 gl) {
        draw(gl, false, 360);
    }

    public void draw(GL2 gl, boolean fill) {
        draw(gl, fill, 360);
    }

    public void draw(GL2 gl, boolean fill, int iterations) {
        draw(gl, fill, iterations, 0);
    }

    public void draw(GL2 gl, boolean fill, int iterations, double startAngle) {
        if (iterations <= 0) return;

        double delta = 2 * Math.PI / iterations;

        if (fill) {
            gl.glBegin(GL2.GL_POLYGON);
            for (int i = 0; i <= iterations; i++) {
                double angle = i * delta + Math.toRadians(startAngle);
                double x = center.x() + radius * Math.cos(angle);
                double y = center.y() + radius * Math.sin(angle);
                gl.glVertex2d(x, y);
            }
            gl.glEnd();
        } else {
            gl.glBegin(GL2.GL_LINE_LOOP);
            for (int i = 0; i < iterations; i++) {
                double angle = i * delta + Math.toRadians(startAngle);
                double x = center.x() + radius * Math.cos(angle);
                double y = center.y() + radius * Math.sin(angle);
                gl.glVertex2d(x, y);
            }
            gl.glEnd();
        }
    }

}
