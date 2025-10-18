package Graphics;

public record Coordinate(float x, float y) {

    public Coordinate copy() {
        return new Coordinate(x, y);
    }

    public float distanceTo(Coordinate other) {
        float dx = other.x - x;
        float dy = other.y - y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return "(%.2f, %.2f)".formatted(x, y);
    }
}
