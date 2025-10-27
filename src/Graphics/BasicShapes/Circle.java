package Graphics.BasicShapes;

import Graphics.Color;
import Graphics.Coordinate;
import com.jogamp.opengl.GL2;

public class Circle {
    private final Coordinate center;
    private final double radius;
    private final Color color;

    private Circle(Coordinate center, double radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    public Coordinate getCenter() { return center; }
    public double getRadius() { return radius; }
    public Color getColor() { return color; }

    public void draw(GL2 gl) {
        draw(gl, false, 360, 0);
    }

    public void draw(GL2 gl, boolean fill) {
        draw(gl, fill, 360, 0);
    }

    public void draw(GL2 gl, boolean fill, int iterations) {
        draw(gl, fill, iterations, 0);
    }

    public void draw(GL2 gl, boolean fill, int iterations, double startAngle) {
        if (iterations <= 0) return;

        color.useColorGl(gl);

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

    public static class Builder {
        private Coordinate center;
        private double radius;
        private Color color = Color.WHITE;

        public Builder setCenter(Coordinate center) {
            this.center = center;
            return this;
        }

        public Builder setCenter(double x, double y) {
            this.center = new Coordinate(x, y);
            return this;
        }

        public Builder setRadius(double radius) {
            this.radius = radius;
            return this;
        }

        public Builder setColor(Color color) {
            this.color = color;
            return this;
        }

        public Circle build() {
            if (center == null) throw new IllegalArgumentException("Center cannot be null");
            if (radius <= 0) throw new IllegalArgumentException("Radius must be positive");
            if (color == null) color = Color.WHITE;

            return new Circle(center, radius, color);
        }
    }
}
