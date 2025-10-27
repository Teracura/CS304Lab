package Graphics.ComplexShapes;

import Graphics.BasicShapes.Circle;
import Graphics.Color;
import Graphics.Coordinate;
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

    public void draw(GL2 gl) {
        Circle main = new Circle.Builder()
                .setCenter(center)
                .setRadius(radius)
                .setColor(color)
                .build();

        Circle left = new Circle.Builder()
                .setCenter(center.x() - spread, center.y())
                .setRadius(radius * 0.8)
                .setColor(color)
                .build();

        Circle right = new Circle.Builder()
                .setCenter(center.x() + spread, center.y())
                .setRadius(radius * 0.8)
                .setColor(color)
                .build();

        main.draw(gl, true);
        left.draw(gl, true);
        right.draw(gl, true);
    }

}
