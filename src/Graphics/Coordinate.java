package Graphics;

public record Coordinate(double x, double y) {

    public Coordinate copy() {
        return new Coordinate(x, y);
    }

    public double distanceTo(Coordinate other) {
        double dx = other.x - x;
        double dy = other.y - y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return "(%.2f, %.2f)".formatted(x, y);
    }
}
