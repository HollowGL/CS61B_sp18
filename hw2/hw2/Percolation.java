package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private final int N;
    private int topFull = -1;
    private int[] open;
    private int openSites = 0;
    private boolean percolate = false;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        this.uf = new WeightedQuickUnionUF(N * N);
        this.open = new int[N * N]; // 默认是0
    }

    public void open(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        if (row == 0) {
            if (topFull == -1) {
                topFull = col;
            } else {
                uf.union(col, topFull);
            }
        }
        open[convert(row, col)] = 1;
        ufUnion(row, col);
        if (row == N - 1 && topFull != -1) {
            if (uf.connected(convert(row, col), topFull)) {
                percolate = true;
            }
        }
        openSites++;
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException();
        }
        return open[convert(row, col)] == 1;
    }

    public boolean isFull(int row, int col) { // 检查方法有问题
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (topFull == -1 || !isOpen(row, col)) {
            return false;
        }
        if (row == 0) {
            return true;
        }
        return uf.connected(convert(row, col), topFull);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        if (percolate) {
            return true;
        }
        for (int i = 0; i < N; i++) {
            if (isOpen(N - 1, i)) {
                if (uf.connected(convert(N - 1, i), topFull)) {
                    percolate = true;
                }
            }
        }
        return percolate;
    }

    private int convert(int row, int col) { // 二维变一维
        return row * N + col;
    }

    private void ufUnion(int row, int col) {
        if (row > 0 && isOpen(row - 1, col)) {
            uf.union(convert(row - 1, col), convert(row, col));
        }
        if (row < N - 1 && isOpen(row + 1, col)) {
            uf.union(convert(row + 1, col), convert(row, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            uf.union(convert(row, col - 1), convert(row, col));
        }
        if (col < N - 1 && isOpen(row, col + 1)) {
            uf.union(convert(row, col + 1), convert(row, col));
        }
    }

    public void check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (open[convert(i, j)] == 0) {
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

        System.out.println("count: " + this.uf.count());
        System.out.println(this.percolates());
        System.out.println(this.numberOfOpenSites() + "\n===========\n");
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
        System.out.println(p3.isFull(0, 0));
        System.out.println(p3.isOpen(0, 0));
    }
}
