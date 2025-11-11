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

    public Coordinate add(Coordinate other) {
        return new Coordinate(x + other.x, y + other.y);
    }

    public Coordinate multiply(Coordinate factor) {
        return new Coordinate(x * factor.x(), y * factor.y());
    }

    public Coordinate multiply(double factor) {
        return new Coordinate(x * factor, y * factor);
    }

    @Override
    public String toString() {
        return "(%.2f, %.2f)".formatted(x, y);
    }
}
