package Physics.Collision;

import Graphics.Coordinate;

public interface Hitbox {
    boolean contains(Coordinate point);
    boolean intersects(Hitbox other);
}
