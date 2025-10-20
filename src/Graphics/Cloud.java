package Graphics;

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
        Circle main = new Circle(center, radius);
        Circle left = new Circle(new Coordinate(center.x() - spread, center.y()), radius * 0.8);
        Circle right = new Circle(new Coordinate(center.x() + spread, center.y()), radius * 0.8);

        color.useColorGl(gl);
        main.draw(gl, true);
        left.draw(gl, true);
        right.draw(gl, true);
    }
}
