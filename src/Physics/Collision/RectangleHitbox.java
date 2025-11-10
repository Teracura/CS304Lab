package Physics.Collision;

import Graphics.Coordinate;

public record RectangleHitbox(Coordinate center, double width, double height) implements Hitbox {

    @Override
    public boolean contains(Coordinate p) {
        return Math.abs(p.x() - center.x()) <= width / 2 &&
                Math.abs(p.y() - center.y()) <= height / 2;
    }

    @Override
    public boolean intersects(Hitbox other) {
        if (other instanceof RectangleHitbox(Coordinate center1, double width1, double height1)) {
            return Math.abs(center.x() - center1.x()) <= (width + width1) / 2 &&
                    Math.abs(center.y() - center1.y()) <= (height + height1) / 2;
        }
        if (other instanceof CircleHitbox(Coordinate center1, double radius)) {
            // Simple circle-rect collision (unrotated)
            double dx = Math.abs(center1.x() - center.x());
            double dy = Math.abs(center1.y() - center.y());

            double closestX = Math.max(dx - width / 2, 0);
            double closestY = Math.max(dy - height / 2, 0);
            return closestX * closestX + closestY * closestY <= radius * radius;
        }
        return false;
    }

}
