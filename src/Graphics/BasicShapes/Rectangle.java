package Graphics.BasicShapes;

import Graphics.Color;
import Graphics.Coordinate;
import Graphics.Shape;
import com.jogamp.opengl.GL2;

public class Rectangle implements Shape {
    Coordinate center;
    double width;
    double height;
    double rotation;
    Color color;
    boolean fill;

    private Rectangle(Coordinate center, double width, double height, double rotation, Color color, boolean fill) {
        this.center = center;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.color = color;
        this.fill = fill;
    }

    public Coordinate[] getVertices() {
        double halfW = width / 2.0;
        double halfH = height / 2.0;
        double x = center.x();
        double y = center.y();
        return new Coordinate[]{
                rotateAndTranslate(new Coordinate(x - halfW, y - halfH)),
                rotateAndTranslate(new Coordinate(x + halfW, y - halfH)),
                rotateAndTranslate(new Coordinate(x + halfW, y + halfH)),
                rotateAndTranslate(new Coordinate(x - halfW, y + halfH))
        };
    }

    private Coordinate rotateAndTranslate(Coordinate p) {
        double rad = Math.toRadians(rotation);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        double dx = p.x() - center.x();
        double dy = p.y() - center.y();

        double rx = dx * cos - dy * sin;
        double ry = dx * sin + dy * cos;

        double x = center.x() + rx;
        double y = center.y() + ry;

        return new Coordinate(x, y);
    }


    public Coordinate getCenter() {
        return center;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }

    public void setCenter(Coordinate center) {
        this.center = center;
    }

    public void setCenter(double x, double y) {
        this.center = new Coordinate(x, y);
    }

    @Override
    public void move(double x, double y) {
        center = center.add(new Coordinate(x, y));
    }

    @Override
    public void draw(GL2 gl) {
        var points = getVertices();
        color.useColorGl(gl);
        if (fill) {
            gl.glBegin(GL2.GL_POLYGON);
            for (Coordinate point : points) {
                gl.glVertex2d(point.x() + Math.cos(Math.toRadians(rotation)), point.y() + Math.sin(Math.toRadians(rotation)));
            }
            gl.glEnd();
            return;
        }

        gl.glBegin(GL2.GL_LINE_LOOP);
        for (Coordinate point : points) {
            gl.glVertex2d(point.x(), point.y());
        }
        gl.glEnd();
    }

    @Override
    public void rotate(double angle) {
        rotation += angle;
    }

    @Override
    public void scale(double scaleFactor) {
        height *= scaleFactor;
        width *= scaleFactor;
    }

    @Override
    public Rectangle copy() {
        return new Rectangle(center, width, height, rotation, color, fill);
    }

    public static class Builder {
        private Coordinate center;
        private double width;
        private double height;
        private double rotation;
        private Color color;
        private boolean fill;

        public Builder setCenter(Coordinate center) {
            this.center = center;
            return this;
        }

        public Builder setCenter(double x, double y) {
            this.center = new Coordinate(x, y);
            return this;
        }

        public Builder setWidth(double width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(double height) {
            this.height = height;
            return this;
        }

        public Builder setRotation(double rotation) {
            this.rotation = rotation;
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

        public Rectangle build() {
            if (center == null) throw new IllegalArgumentException("Center cannot be null");
            if (height <= 0) throw new IllegalArgumentException("Height must be positive");
            if (color == null) color = Color.WHITE;

            return new Rectangle(center, width, height, rotation, color, fill);
        }
    }
}
