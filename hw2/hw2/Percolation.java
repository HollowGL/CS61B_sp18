package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    /**如果直接将底部所有open site联系在一起，会导致backwash的问题出现
     * 所以考虑使用两个WQU，一个联系底部open site用于检测是否percolate；
     * 另一个不联系底部，专门用于检测是否isfull。避免backwash
     * @source:https://www.cnblogs.com/anne-vista/p/4841732.html
     */
    private WeightedQuickUnionUF uf, ufNoButton;
    private final int N;
    private int topFull = -1;
    private int buttonOpen = -1;
    private int[] open;
    private int openSites = 0;
    private boolean percolate = false;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        this.uf = new WeightedQuickUnionUF(N * N);
        this.ufNoButton = new WeightedQuickUnionUF(N * N);
        this.open = new int[N * N];
        for (int i = 0; i < N * N; i++) {
            open[i] = 0;
        }
    }

    public void open(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        open[convert(row, col)] = 1;
        openSites++;

        if (row == 0) {
            if (topFull == -1) {
                topFull = col;
            } else {
                uf.union(col, topFull);
                ufNoButton.union(col, topFull);
            }
        } else if (row == N - 1) {
            if (buttonOpen == -1) {
                buttonOpen = N * (N - 1) + col;
            } else {
                uf.union(convert(N - 1, col), buttonOpen);
            }
        }

        ufUnion(row, col);
        if (row == N - 1 && topFull != -1) {
            if (uf.connected(convert(row, col), topFull)) {
                percolate = true;
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException();
        }
        return open[convert(row, col)] == 1;
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (topFull == -1 || !isOpen(row, col)) {
            return false;
        }
        if (row == 0) {
            return true;
        }
        return ufNoButton.connected(convert(row, col), topFull);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        if (percolate) {
            return true;
        }
        if (topFull == -1 || buttonOpen == -1) {
            return false;
        }
        if (ufNoButton.connected(topFull, buttonOpen)) {
            percolate = true;
        }
        return percolate;
    }

    private int convert(int row, int col) { // 二维变一维
        return row * N + col;
    }

    private void ufUnion(int row, int col) {
        if (row > 0 && isOpen(row - 1, col)) {
            uf.union(convert(row - 1, col), convert(row, col));
            ufNoButton.union(convert(row - 1, col), convert(row, col));
        }
        if (row < N - 1 && isOpen(row + 1, col)) {
            uf.union(convert(row + 1, col), convert(row, col));
            ufNoButton.union(convert(row + 1, col), convert(row, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            uf.union(convert(row, col - 1), convert(row, col));
            ufNoButton.union(convert(row, col - 1), convert(row, col));
        }
        if (col < N - 1 && isOpen(row, col + 1)) {
            uf.union(convert(row, col + 1), convert(row, col));
            ufNoButton.union(convert(row, col + 1), convert(row, col));
        }
    }

    public static void main(String[] args) {
    }
}
