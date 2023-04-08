package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private double[] xt;
    private final int t;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        xt = new double[T];
        t = T;
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                p.open(row, col);
            }
            xt[i] = p.numberOfOpenSites();
        }
    }

    public double mean() {
        double sum = 0;
        for (int i = 0; i < t; i++) {
            sum += xt[i];
        }
        return sum / t;
    }

    public double stddev() {
        double sum = 0;
        double u = mean();
        for (int i = 0; i < t; i++) {
            sum += Math.pow(xt[i] - u, 2);
        }
        return sum / (t - 1);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.pow(t, 0.5);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.pow(t, 0.5);
    }
}
