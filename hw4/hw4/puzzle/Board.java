package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

import java.util.Objects;

public class Board implements WorldState {
    private static final int BLANK = 0;
    private int size; // N by N
    private int[][] tiles;
    private int[][] goal;

    /**
     * Constructs a board from an N-by-N array of tiles where
     * tiles[i][j] = tile at row i, column j
     *
     * @param tiles
     */
    public Board(int[][] tiles) {
        this.size = tiles.length;
        this.tiles = new int[size][size];

        // tiles
        // ( this.tiles = tiles;) Your Board class is mutable and you should be making a copy of the values in the passed tiles array. Please see the FAQ!
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }

        // goal
        int c = 1;
        goal = new int[size][size];
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                this.goal[i][j] = c;
                c++;
            }
        }
        this.goal[size - 1][size - 1] = BLANK;

    }

    /**
     * Returns value of tile at row i, column j (or 0 if blank)
     *
     * @param i
     * @param j
     * @return
     */
    public int tileAt(int i, int j) {
        if (i >= 0 && j >= 0 && i <= this.size - 1 && j <= this.size - 1) {
            return this.tiles[i][j];
        } else {
            throw new java.lang.IndexOutOfBoundsException("both i and j are between 0 and N − 1");
        }
    }

    public int size() {
        return this.size;
    }

    /**
     * Returns neighbors of this board.
     * SPOILERZ: This is the answer.
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    // todo if goal is blank, then do not count
    public int hamming() {
        int diff = 0;
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                if (tileAt(i, j) != BLANK && tileAt(i, j) != goal[i][j]) {
                    diff++;
                }
            }
        }
        return diff;
    }

    public int manhattan() {
        int diff = 0;
        for (int i = 0; i < this.size(); i++) { // todo sth wrong heres
            for (int j = 0; j < this.size(); j++) {
                if (tileAt(i, j) != BLANK && tileAt(i, j) != goal[i][j]) {
                    int[] idx = indexOf(goal[i][j]);
                    diff = diff + Math.abs(idx[0] - i) + Math.abs(idx[1] - j);
                }
            }
        }
        return diff;
    }

    private int[] indexOf(int item) {
        int[] index;
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                if (tileAt(i, j) == item) {
                    return new int[]{i, j};
                }
            }
        }
        throw new RuntimeException("indexOf: cannot find index in 2D array");
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
//        return hamming();
    }

    public boolean equals(Object y) {
        // self check
        if (this == y)
            return true;
        // null check
        if (y == null)
            return false;
        // type check and cast
        if (getClass() != y.getClass())
            return false;

        Board boardObj = (Board) y;
        // size comparison
        if (Objects.equals(this.size, boardObj.size))
            return false;
        // tiles comparison, cannot compare directly, should be in elemen/t
        boolean ret;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.tiles[i][j] == ((Board) y).tiles[i][j])
                    return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
