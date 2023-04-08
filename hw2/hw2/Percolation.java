package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] perc;
    private final int N;
    private final int blocked = -1;
    private final int open = 0;
    private final int full = 1;
    private int openSites = 0;
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        perc = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                perc[i][j] = blocked;
            }
        }
    }

    public void open(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col) || isFull(row, col)) {
            return;
        }
        perc[row][col] = open;
        if (row == 0) {
            perc[row][col] = full;
        } else if (isFull(row - 1, col)) {
            perc[row][col] = full;
        } else if (row < N - 1 && isFull(row + 1, col)) {
            perc[row][col] = full;
        } else if (col > 0 && isFull(row, col - 1)) {
            perc[row][col] = full;
        } else if (col < N - 1 && isFull(row, col + 1)) {
            perc[row][col] = full;
        }
        openSites++;
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException();
        }
        return perc[row][col] == open;
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException();
        }
        return perc[row][col] == full;
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        for (int i = 0; i < N; i++) {
            if (isFull(N - 1, i)) {
                return true;
            }
        }
        return false;
    }

    public void check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (perc[i][j] == blocked) {
                    System.out.print('#');
                } else if (isFull(i, j)) {
                    System.out.print('f');
                } else if (isOpen(i, j)) {
                    System.out.print('o');
                } else {
                    System.out.print('-');
                }
            }
            System.out.println();
        }

        System.out.println(this.percolates());
        System.out.println(this.numberOfOpenSites());
    }
    public static void main(String[] args) {
        // test1
        Percolation p1 = new Percolation(2);
        p1.open(0, 1);
        p1.open(1, 1);
        p1.check();

        // test2
        Percolation p2 = new Percolation(3);
        p2.open(0, 0);
        p2.open(1, 1);
        p2.open(2, 1);
        p2.check();

        // test3
        Percolation p3 = new Percolation(5);
        p3.open(0, 4);
        p3.open(1, 4);
        p3.open(2, 4);
        p3.open(3, 4);
        p3.open(3, 3);
        p3.open(3, 2);
        p3.open(2, 2);
        p3.open(1, 2);
        p3.open(1, 1);
        p3.open(1, 0);
        p3.open(2, 0);
        p3.open(3, 0);
        p3.open(4, 0);
        p3.check();
    }
}
