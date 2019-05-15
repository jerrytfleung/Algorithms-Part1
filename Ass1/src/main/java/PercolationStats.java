import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private double[] stats = null;
    private double mean = 0;
    private double stddev = 0;
    private double lo = 0;
    private double hi = 0;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        } else {
            Percolation sites = null;
            stats = new double[trials];
            for (int i = 0; i < trials; i++) {
                sites = new Percolation(n);
                while (!sites.percolates()) {
                    sites.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
                }
                stats[i] = (double) sites.numberOfOpenSites() / n / n;
            }

            mean = StdStats.mean(stats);
            stddev = StdStats.stddev(stats);
            lo = mean() - (CONFIDENCE_95 * stddev() / Math.sqrt(stats.length));
            hi = mean() + (CONFIDENCE_95 * stddev() / Math.sqrt(stats.length));
        }

    }

    public static void main(String[] args) {
        if (args.length == 2) {
            int n = Integer.parseInt(args[0]);
            int trials = Integer.parseInt(args[1]);
            PercolationStats stats = new PercolationStats(n, trials);
            System.out.println("mean                    = " + stats.mean());
            System.out.println("stddev                  = " + stats.stddev());
            System.out.println("95% confidence interval = " + "[" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;

    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return lo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return hi;
    }

}