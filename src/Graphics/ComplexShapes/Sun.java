package Graphics.ComplexShapes;

import Graphics.BasicShapes.Circle;
import Graphics.Color;
import Graphics.Coordinate;
import com.jogamp.opengl.GL2;

public record Sun(Coordinate center, double radius, double rayLength, int rayCount, Color color) {

    public void draw(GL2 gl) {
        new Circle.Builder().setCenter(center).setRadius(radius).setColor(color).setFill(true).build().draw(gl);

        gl.glLineWidth(2.0f);
        gl.glBegin(GL2.GL_LINES);
        for (int i = 0; i < rayCount; i++) {
            double angle = 2 * Math.PI * i / rayCount;
            double x1 = center.x() + (radius + rayLength / 2) * Math.cos(angle);
            double y1 = center.y() + (radius + rayLength / 2) * Math.sin(angle);
            double x2 = center.x() + (radius + rayLength) * Math.cos(angle);
            double y2 = center.y() + (radius + rayLength) * Math.sin(angle);

            gl.glVertex2d(x1, y1);
            gl.glVertex2d(x2, y2);
        }
        gl.glEnd();
        gl.glLineWidth(1.0f);
    }

    public static class Builder {
        private Coordinate center;
        private double radius;
        private double rayLength;
        private int rayCount = 12;
        private Color color;

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

        public Builder setRayLength(double rayLength) {
            this.rayLength = rayLength;
            return this;
        }

        public Builder setRayCount(int rayCount) {
            this.rayCount = rayCount;
            return this;
        }

        public Builder setColor(Color color) {
            this.color = color;
            return this;
        }

        public Sun build() {
            if (center == null) throw new IllegalArgumentException("Center cannot be null");
            if (radius <= 0) throw new IllegalArgumentException("Radius must be positive");
            if (rayLength <= 0) rayLength = radius * 0.5;

            return new Sun(center, radius, rayLength, rayCount, color);
        }
    }
}
