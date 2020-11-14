package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] thresholds;
    private int N;
    private int T;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();

        thresholds = new double[T];
        this.N = N;
        this.T = T;

        for (int i = 0; i < T; i++) {
            Percolation perc = pf.make(N);
            // open till percolate
            while (!perc.percolates())
                perc.open(uniRandInt(), uniRandInt());
            thresholds[i] = (double) perc.numberOfOpenSites() / (N * N);
        }
    }

    // create random int between 0 and N-1
    private int uniRandInt() {
        // Returns a random integer uniformly in [0 N).
        return StdRandom.uniform(this.N);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return this.mean() - 1.96 * this.stddev() / Math.sqrt(this.T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return this.mean() + 1.96 * this.stddev() / Math.sqrt(this.T);
    }

    private static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats stats = new PercolationStats(5, 5, pf);
    }
}
