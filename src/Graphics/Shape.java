package Graphics;

import Physics.Collision.Hitbox;
import com.jogamp.opengl.GL2;

public interface Shape<T> {
    void move(double x, double y);
    void draw(GL2 gl);
    void rotate(double angle);
    void scale(double scaleFactor);
    T copy();
    Hitbox getHitbox();
}
