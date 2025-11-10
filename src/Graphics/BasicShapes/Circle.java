package Graphics.BasicShapes;

import Graphics.Color;
import Graphics.Coordinate;
import Graphics.Shape;
import Physics.Collision.CircleHitbox;
import Physics.Collision.Hitbox;
import com.jogamp.opengl.GL2;

public class Circle implements Shape {
    private Coordinate center;
    private double radius;
    private Color color;
    private boolean fill = false;
    private int iterations = 360;
    private double startAngle = 0;

    private Circle(Coordinate center, double radius, Color color, boolean fill, int iterations, double startAngle) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.fill = fill;
        this.iterations = iterations;
        this.startAngle = startAngle;
    }

    public Coordinate getCenter() { return center; }
    public double getRadius() { return radius; }
    public Color getColor() { return color; }

    @Override
    public void move(double x, double y) {
        center = center.add(new Coordinate(x, y));
    }

    @Override
    public void rotate(double angle) {
        startAngle += angle;
    }

    @Override
    public void scale(double scaleFactor) {
        radius *= scaleFactor;
    }

    @Override
    public Circle copy() {
        return new Circle(center, radius, color, fill, iterations, startAngle);
    }

    @Override
    public Hitbox getHitbox() {
        return new CircleHitbox(center, radius);
    }

    @Override
    public void draw(GL2 gl) {
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
        private boolean fill = false;
        private int iterations = 360;
        private double startAngle = 0;

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

        public Builder setFill(boolean fill) {
            this.fill = fill;
            return this;
        }

        public Builder setIterations(int iterations) {
            this.iterations = iterations;
            return this;
        }

        public Builder setStartAngle(double startAngle) {
            this.startAngle = startAngle;
            return this;
        }

        public Circle build() {
            if (center == null) throw new IllegalArgumentException("Center cannot be null");
            if (radius <= 0) throw new IllegalArgumentException("Radius must be positive");
            if (color == null) color = Color.WHITE;

            return new Circle(center, radius, color, fill, iterations, startAngle);
        }
    }
}
