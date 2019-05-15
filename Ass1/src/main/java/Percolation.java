import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int size;
    private WeightedQuickUnionUF sitesWithTop;
    private WeightedQuickUnionUF sitesWithTopAndBottom;
    private boolean[] sitesStatus;
    private int opened;

    // create n-by-n grid, with all sitesWithTop blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        } else {
            size = n;
            sitesWithTop = new WeightedQuickUnionUF(size * size + 1);
            sitesWithTopAndBottom = new WeightedQuickUnionUF(size * size + 2);
            sitesStatus = new boolean[size * size];

            for (int i = 0; i < size * size; i++) {
                sitesStatus[i] = false;
            }
            opened = 0;
            for (int i = 0; i < size; i++) {
                sitesWithTop.union(size * size, i);
                sitesWithTopAndBottom.union(size * size, i);
                sitesWithTopAndBottom.union(size * size + 1, (size - 1) * size + i);
            }
        }
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (1 <= row && row <= size && 1 <= col && col <= size) {
            int x = convert(row);
            int y = convert(col);
            if (!sitesStatus[twoToOne(x, y)]) {
                // Open it
                sitesStatus[twoToOne(x, y)] = true;
                opened++;

                // Try up
                if (row - 1 >= 1 && isOpen(row - 1, col)) {
                    sitesWithTop.union(twoToOne(x, y), twoToOne((x - 1), y));
                    sitesWithTopAndBottom.union(twoToOne(x, y), twoToOne((x - 1), y));

                }

                // Try down
                if (row + 1 <= size && isOpen(row + 1, col)) {
                    sitesWithTop.union(twoToOne(x, y), twoToOne((x + 1), y));
                    sitesWithTopAndBottom.union(twoToOne(x, y), twoToOne((x + 1), y));
                }

                // Try left
                if (col - 1 >= 1 && isOpen(row, col - 1)) {
                    sitesWithTop.union(twoToOne(x, y), twoToOne(x, (y - 1)));
                    sitesWithTopAndBottom.union(twoToOne(x, y), twoToOne(x, (y - 1)));
                }

                // Try right
                if (col + 1 <= size && isOpen(row, col + 1)) {
                    sitesWithTop.union(twoToOne(x, y), twoToOne(x, (y + 1)));
                    sitesWithTopAndBottom.union(twoToOne(x, y), twoToOne(x, (y + 1)));
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    // is site (row, col) open
    public boolean isOpen(int row, int col) {
        if (1 <= row && row <= size && 1 <= col && col <= size) {
            return sitesStatus[twoToOne(convert(row), convert(col))];
        } else {
            throw new IllegalArgumentException();
        }
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if (1 <= row && row <= size && 1 <= col && col <= size) {
            return (isOpen(row, col) && sitesWithTop.connected(twoToOne(convert(row), convert(col)), size * size));
        } else {
            throw new IllegalArgumentException();
        }
    }

    // number of open sitesWithTop
    public int numberOfOpenSites() {
        return opened;
    }

    // does the system percolate?
    public boolean percolates() {
        if (size == 1) {
            return isOpen(1, 1);
        } else {
            return sitesWithTopAndBottom.connected(size * size, size * size + 1);
        }
    }

    private int convert(int coord) {
        return coord - 1;
    }

    private int twoToOne(int row, int col) {
        return row * size + col;
    }
}