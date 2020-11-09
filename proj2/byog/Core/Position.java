package byog.Core;

import org.junit.Test;

public class Position {
    public int x;
    public int y;

    public Position(int X, int Y) {
        this.x = X;
        this.y = Y;
    }

    public static double triArea(Position pos1, Position pos2, Position pos3) {
        return Math.abs(0.5 * (pos1.x * (pos2.y - pos3.y) + pos2.x * (pos3.y - pos1.y) + pos3.x * (pos1.y - pos2.y)));
    }

    public int distance(Position testPos) {
        return (int) Math.round(Math.pow(Math.pow(this.x - testPos.x, 2) + Math.pow(this.y - testPos.y, 2), 0.5));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public static void main(String[] args) {
        // test tri area
        Position pos1 = new Position(0, 0);
        Position pos2 = new Position(2, 0);
        Position pos3 = new Position(0, 1);
        double area = triArea(pos1, pos2, pos3);
    }
}


