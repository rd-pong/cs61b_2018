package hw2;

public class Percolation {
    private int N;
    private boolean[][] grid; // true for opened, false for blocked
    private edu.princeton.cs.algs4.UF gridTo1D;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        this.N = N;
        this.grid = new boolean[N][N];
        this.gridTo1D = new edu.princeton.cs.algs4.UF(N * N + 2); // gridTo1D + virtual top & bottom

//        // connect all sites in top row to virtual top gridTo1D[N]
//        for (int i = 0; i < N; i++) {
//            gridTo1D.union(xyTo1D(0, i), N);
//        }
//
//        // connect all sites in bottom row to virtual bottom gridTo1D[N+1]
//        for (int i = 0; i < N; i++) {
//            gridTo1D.union(xyTo1D(N - 1, i), N + 1);
//        }
    }

    // convert xy coordinate to a int in int[]
    private int xyTo1D(int r, int c) {
        return r * this.N + c;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            this.grid[row][col] = true;
        }

        // connect with surrounding sites
        connectDown(row, col);
        connectUp(row, col);
        connectLeft(row, col);
        connectRight(row, col);
    }

    // connect with left *opened* site, do nothing if current is the leftest
    private void connectLeft(int row, int col) {
        if (col == 0) // if there is no left site
            return;
        if (isOpen(row, col - 1)) // if left is opened
            this.gridTo1D.union(xyTo1D(row, col), xyTo1D(row, col - 1));
    }

    // connect with right *opened* site, do nothing if current is the rightest
    private void connectRight(int row, int col) {
        if (col == N - 1) // there is no right
            return;
        if (isOpen(row, col + 1))
            this.gridTo1D.union(xyTo1D(row, col), xyTo1D(row, col + 1));
    }

    // connect with upper *opened* site, do nothing if current is the upest
    private void connectUp(int row, int col) {
        if (row == 0)
            return;
        if (isOpen(row - 1, col))
            this.gridTo1D.union(xyTo1D(row, col), xyTo1D(row - 1, col));
    }

    // connect with down *opened* site, do nothing if current is the downest
    private void connectDown(int row, int col) {
        if (row == N - 1)
            return;
        if (isOpen(row + 1, col))
            this.gridTo1D.union(xyTo1D(row, col), xyTo1D(row + 1, col));
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row][col];
    }

    // todo CONSTANT TIME is the site (row, col) full? Is there any water?
    public boolean isFull(int row, int col) {
        return isFull_slow(row, col);
    }

    // is the site (row, col) full? Is there any water?
    private boolean isFull_slow(int row, int col) {
        // check if it is connected to the top row
        boolean full = false;
        for (int i = 0; i < N; i++) {
            if (isOpen(row, col) && gridTo1D.connected(xyTo1D(0, i), xyTo1D(row, col))) {
                full = true;
            }
        }
        return full;
    }

    // todo CONSTANT TIME number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites_slow();
    }

    private int numberOfOpenSites_slow() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.grid[i][j] == true)
                    sum++;
            }
        }
        return sum;
    }

    // does the system percolate?
    public boolean percolates() {
        return percolates_slow();
    }

    // is the site (row, col) full? Is there any water?
    private boolean percolates_slow() {
        // check if it is connected to the top row
        boolean percolated = false;
        for (int i = 0; i < N; i++) {
            if (isFull(N - 1, i)) {
                percolated = true;
            }
        }
        return percolated;
    }

    public static void main(String[] args) {
        Percolation per = new Percolation(5);
        per.open(3, 4);
        per.open(2, 4);
        per.open(2, 2);
        per.open(2, 3);
        per.open(0, 2);
        per.open(1, 2);
        System.out.println(per.isFull(1, 2));
        System.out.println(per.numberOfOpenSites());
    }
}

