package Graphics.BasicShapes;

import Graphics.Color;
import Graphics.Coordinate;
import com.jogamp.opengl.GL2;

public class Ellipse {
    Coordinate center;
    double width;
    double height;


    public Ellipse(Coordinate center, double width, double height) {
        this.center = center;
        this.width = width;
        this.height = height;
    }

    public Ellipse(double x, double y, double width, double height) {
        this.center = new Coordinate(x, y);
        this.width = width;
        this.height = height;
    }

    public Coordinate getCenter() {
        return center;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void draw(GL2 gl) {
        draw(gl, false);
    }

    public void draw(GL2 gl, boolean fill) {
        draw(gl, fill, Color.BLACK);
    }
    public void draw(GL2 gl, boolean fill, Color color) {
        draw(gl, fill, color, 360);
    }

    public void draw(GL2 gl, boolean fill, Color color, int iterations) {
        draw(gl, fill, color, iterations, 0);
    }

    public void draw(GL2 gl, boolean fill, Color color, int iterations, double startAngle) {
        if (iterations <= 0) return;

        double delta = 2 * Math.PI / iterations;
        color.useColorGl(gl);
        if (fill) {
            gl.glBegin(GL2.GL_POLYGON);
            for (int i = 0; i <= iterations; i++) {
                double angle = i * delta + Math.toRadians(startAngle);
                double x = center.x() + width * Math.cos(angle);
                double y = center.y() + height * Math.sin(angle);
                gl.glVertex2d(x, y);
            }
            gl.glEnd();
        } else {
            gl.glBegin(GL2.GL_LINE_LOOP);
            for (int i = 0; i < iterations; i++) {
                double angle = i * delta + Math.toRadians(startAngle);
                double x = center.x() + width * Math.cos(angle);
                double y = center.y() + height * Math.sin(angle);
                gl.glVertex2d(x, y);
            }
            gl.glEnd();
        }
    }
}
