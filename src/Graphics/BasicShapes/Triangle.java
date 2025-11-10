package Graphics.BasicShapes;

import Graphics.Color;
import Graphics.Coordinate;
import Graphics.Shape;
import Physics.Collision.Hitbox;
import Physics.Collision.RectangleHitbox;
import com.jogamp.opengl.GL2;

public class Triangle implements Shape {
    Coordinate center;
    double height;
    double bottomWidth;
    double rotation;

    public Triangle(Coordinate center, double bottomWidth, double height) {
        this(center, bottomWidth, height, 0);
    }

    public Triangle(Coordinate center, double bottomWidth, double height, double rotation) {
        this.center = center;
        this.bottomWidth = bottomWidth;
        this.height = height;
        this.rotation = rotation;
    }

    public Coordinate getCenter() {
        return center;
    }

    public Triangle copy(){
        return new Triangle(center.copy(), bottomWidth, height, rotation);
    }

    @Override
    public void move(double x, double y){
        move(new Coordinate(x, y));
    }

    public void move(Coordinate delta){
        center = center.add(delta);
    }

    @Override
    public void scale(double factor){
        bottomWidth *= factor;
        height *= factor;
    }

    public void setCenter(Coordinate center) {
        this.center = center;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBottomWidth() {
        return bottomWidth;
    }

    public void setBottomWidth(double bottomWidth) {
        this.bottomWidth = bottomWidth;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    private Coordinate[] getVertices() {
        Coordinate top = new Coordinate(0, height / 2);
        Coordinate left = new Coordinate(-bottomWidth / 2, -height / 2);
        Coordinate right = new Coordinate(bottomWidth / 2, -height / 2);

        return new Coordinate[]{
                rotateAndTranslate(top),
                rotateAndTranslate(right),
                rotateAndTranslate(left)
        };
    }

    private Coordinate rotateAndTranslate(Coordinate p) {
        double rad = Math.toRadians(rotation);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        double x = center.x() + p.x() * cos - p.y() * sin;
        double y = center.y() + p.x() * sin + p.y() * cos;

        return new Coordinate(x, y);
    }

    public void setAngle(double angle){
        rotation = angle;
    }

    @Override
    public void draw(GL2 gl) {
        draw(gl, false);
    }

    @Override
    public void rotate(double angle) {

    }

    @Override
    public Hitbox getHitbox() {
        return new RectangleHitbox(center, bottomWidth, height);
    }

    public void draw(GL2 gl, boolean fill) {
        draw(gl, fill, Color.BLACK);
    }

    public void draw(GL2 gl, boolean fill, Color color) {
        Coordinate[] vertices = getVertices();
        color.useColorGl(gl);

        if (fill)
            gl.glBegin(GL2.GL_TRIANGLES);
        else
            gl.glBegin(GL2.GL_LINE_LOOP);

        for (Coordinate v : vertices)
            gl.glVertex2d(v.x(), v.y());

        gl.glEnd();
    }
}
