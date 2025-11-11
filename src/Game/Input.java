package Game;

public enum Input {

    UP(1),
    DOWN(1 << 1),
    LEFT(1 << 2),
    RIGHT(1 << 3),
    Z(1 << 4),
    X(1 << 5),
    C(1 << 6),
    MOUSE_LEFT(1 << 7),
    A(1 << 8),
    D(1 << 9),
    PLUS(1 << 10),
    MINUS(1 << 11),
    EQUALS(1 << 12),
    R(1 << 13);

    public final int bit;

    Input(int bit) {
        this.bit = bit;
    }

    public static boolean isSet(int state, Input flag) {
        return (state & flag.bit) != 0;
    }

    public static int set(int state, Input flag) {
        return state | flag.bit;
    }

    public static int clear(int state, Input flag) {
        return state & ~flag.bit;
    }

    public static int toggle(int state, Input flag) {
        return state ^ flag.bit;
    }

}
