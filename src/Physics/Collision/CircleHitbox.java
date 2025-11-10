package Physics.Collision;

import Graphics.Coordinate;

public record CircleHitbox(Coordinate center, double radius) implements Hitbox {

    @Override
    public boolean contains(Coordinate p) {
        double dx = p.x() - center.x();
        double dy = p.y() - center.y();
        return dx * dx + dy * dy <= radius * radius;
    }

    @Override
    public boolean intersects(Hitbox other) {
        if (other instanceof CircleHitbox(Coordinate center1, double radius1)) {
            double dx = center1.x() - center.x();
            double dy = center1.y() - center.y();
            double r = radius + radius1;
            return dx * dx + dy * dy <= r * r;
        }
        if (other instanceof RectangleHitbox r) {
            return r.intersects(this);
        }
        return false;
    }
}
