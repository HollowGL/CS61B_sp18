package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class Board implements WorldState {

    private final int[][] tiles;
    private final int N;
    private final int BLANK = 0;
    private int estimatedDist = -1;

    public Board(int[][] tiles) {
        this.N = tiles.length;
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= N) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    public int size() {
        return N;
    }

    public int hamming() {
        int distance = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == BLANK) {
                    continue;
                }
                if (tileAt(i, j) - 1 != N * i + j) {
                    distance++;
                }
            }
        }
        return distance;
    }

    public int manhattan() {
        int distance = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == BLANK) {
                    continue;
                }
                distance += Math.abs((tileAt(i, j) - 1) / N - i);
                distance += Math.abs((tileAt(i, j) - 1) % N - j);
            }
        }
        return distance;
    }

    @Override
    public int estimatedDistanceToGoal() {
        if (estimatedDist == -1) {
            estimatedDist = manhattan();
        }
        return estimatedDist;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Board that = (Board) o;
        if (this.N != that.N) {
            return false;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.tileAt(i, j) != that.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
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

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int[][] neibor = new int[N][N];
        int xBlank = -1;
        int yBlank = -1;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; j++) {
                neibor[i][j] = tileAt(i, j);
                if (tileAt(i, j) == BLANK) {
                    xBlank = i;
                    yBlank = j;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Math.abs(i - xBlank) + Math.abs(j - yBlank) == 1) {
                    neibor[xBlank][yBlank] = neibor[i][j];
                    neibor[i][j] = BLANK;
                    neighbors.enqueue(new Board(neibor));
                    neibor[i][j] = neibor[xBlank][yBlank];
                    neibor[xBlank][yBlank] = BLANK;
                }
            }
        }
        return neighbors;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
