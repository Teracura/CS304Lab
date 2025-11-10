package Graphics.ComplexShapes;

import Graphics.BasicShapes.Circle;
import Graphics.Color;
import Graphics.Coordinate;
import Graphics.Shape;
import com.jogamp.opengl.GL2;

public class Cloud {
    Coordinate center;
    double radius;
    double spread;
    Color color;

    public Cloud(Coordinate center, double radius) {
        this(center, radius, radius * 0.8, Color.WHITE);
    }

    public Cloud(Coordinate center, double radius, double spread, Color color) {
        this.center = center;
        this.radius = radius;
        this.spread = spread;
        this.color = color;
    }

    public void move(double x, double y) {
        center = new Coordinate(center.x() + x, center.y() + y);
    }

    public void draw(GL2 gl) {
        Circle main = new Circle.Builder()
                .setCenter(center)
                .setRadius(radius)
                .setColor(color)
                .setFill(true)
                .build();

        Circle left = main.copy();
        Circle right = main.copy();

        left.move(-spread, 0);
        left.scale(0.8);
        right.move(spread, 0);
        right.scale(0.8);

        main.draw(gl);
        left.draw(gl);
        right.draw(gl);
    }

    public void rotate(double angle) {

    }

    public void scale(double scaleFactor) {
        radius *= scaleFactor;
        spread *= scaleFactor;
    }

    public Cloud copy() {
        return new Cloud(center, radius, spread, color);
    }

}
