package Graphics;

import com.jogamp.opengl.GL2;

public class Sun {
    Coordinate center;
    double radius;
    double rayLength;
    int rayCount;
    Color color;

    public Sun(Coordinate center, double radius) {
        this(center, radius, radius * 0.5, 12, Color.YELLOW);
    }

    public Sun(Coordinate center, double radius, double rayLength, int rayCount, Color color) {
        this.center = center;
        this.radius = radius;
        this.rayLength = rayLength;
        this.rayCount = rayCount;
        this.color = color;
    }

    public void draw(GL2 gl) {
        color.useColorGl(gl);

        Circle circle = new Circle(center, radius);
        color.useColorGl(gl);
        circle.draw(gl, true);

        gl.glLineWidth(2.0f);
        gl.glBegin(GL2.GL_LINES);
        for (int i = 0; i < rayCount; i++) {
            double angle = 2 * Math.PI * i / rayCount;
            double x1 = center.x() + (radius + rayLength/2) * Math.cos(angle);
            double y1 = center.y() + (radius + rayLength/2) * Math.sin(angle);
            double x2 = center.x() + (radius + rayLength) * Math.cos(angle);
            double y2 = center.y() + (radius + rayLength) * Math.sin(angle);

            gl.glVertex2d(x1, y1);
            gl.glVertex2d(x2, y2);
        }
        gl.glEnd();
        gl.glLineWidth(1.0f);
    }
}
