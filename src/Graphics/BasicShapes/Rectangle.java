package Graphics.BasicShapes;

import Graphics.Color;
import Graphics.Coordinate;
import com.jogamp.opengl.GL2;

public class Rectangle {
    Coordinate center;
    double width;
    double height;
    double rotation;

    public Rectangle(Coordinate center, double width, double height) {
        this.center = center;
        this.width = width;
        this.height = height;
        this.rotation = 0;
    }

    public Rectangle(Coordinate center, double width, double height, double rotation) {
        this.center = center;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
    }

    public Rectangle(double x, double y, double width, double height) {
        this.center = new Coordinate(x, y);
        this.width = width;
        this.height = height;
        this.rotation = 0;
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
                rotateAndTranslate(new Coordinate(x- halfW, y + halfH))
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

    public void draw(GL2 gl) {
        draw(gl, false);
    }

    public void draw(GL2 gl, boolean fill) {
        draw(gl, fill, Color.BLACK);
    }

    public void draw(GL2 gl, boolean fill, Color color) {
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
}
