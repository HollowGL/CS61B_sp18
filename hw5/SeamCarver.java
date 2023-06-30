import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

public class SeamCarver {
    private Picture picture;
    private class Node {
        private double val;
        private int pre;
        public Node(double val, int pre) {
            this.val = val;
            this.pre = pre;
        }
        public void add(double v, int p) {
            this.val += v;
            this.pre = p;
        }
    }
    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture);
    }
    public Picture picture() {
        return new Picture(this.picture);
    }
    public int width() {
        return picture.width();
    }
    public int height() {
        return picture.height();
    }
    public double energy(int x, int y) {
        validData(x, y);

        Color left, right, up, down;
        if (width() == 1) {
            left = picture.get(x, y);
            right = left;
        } else if (x == 0) {
            left = picture.get(width() - 1, y);
            right = picture.get(x + 1, y);
        } else if (x == width() - 1) {
            left = picture.get(x - 1, y);
            right = picture.get(0, y);
        } else {
            left = picture.get(x - 1, y);
            right = picture.get(x + 1, y);
        }
        if (height() == 1) {
            up = picture.get(x, y);
            down = up;
        } else if (y == 0) {
            up = picture.get(x, height() - 1);
            down = picture.get(x, y + 1);
        } else if (y == height() - 1) {
            up = picture.get(x, y - 1);
            down = picture.get(x, 0);
        } else {
            up = picture.get(x, y - 1);
            down = picture.get(x, y + 1);
        }

        return delta(up, down) + delta(left, right);
    }
    private int delta(Color c1, Color c2) {
        int r = c1.getRed() - c2.getRed();
        int g = c1.getGreen() - c2.getGreen();
        int b = c1.getBlue() - c2.getBlue();
        return r * r + g * g + b * b;
    }
    public int[] findHorizontalSeam() {
        Picture copy = new Picture(height(), width());
        for (int i = 0; i < width(); ++i) {
            for (int j = 0; j < height(); ++j) {
                copy.set(j, i, picture.get(i, j));
            }
        }
        SeamCarver sc = new SeamCarver(copy);
        return sc.findVerticalSeam();
    }
    public int[] findVerticalSeam() {
        if (width() == 1) {
            int[] seam = new int[height()];
            for (int i = 0; i < height(); ++i) {
                seam[i] = 0;
            }
            return seam;
        }

        Node[][] dp = new Node[height()][width()];
        for (int i = 0; i < height(); ++i) {
            for (int j = 0; j < width(); ++j) {
                dp[i][j] = new Node(energy(j, i), 0);
            }
        }
        for (int i = 1; i < height(); ++i) {
            for (int j = 0; j < width(); ++j) {
                if (j == 0) {
                    if (dp[i - 1][j].val < dp[i - 1][j + 1].val) {
                        dp[i][j].add(dp[i - 1][j].val, j);
                    } else {
                        dp[i][j].add(dp[i - 1][j + 1].val, j + 1);
                    }
                } else if (j == width() - 1) {
                    if (dp[i - 1][j - 1].val < dp[i - 1][j].val) {
                        dp[i][j].add(dp[i - 1][j - 1].val, j - 1);
                    } else {
                        dp[i][j].add(dp[i - 1][j].val, j);
                    }
                } else {
                    int min = j - 1;
                    if (dp[i - 1][min].val > dp[i - 1][j].val) {
                        min = j;
                    }
                    if (dp[i - 1][min].val > dp[i - 1][j + 1].val) {
                        min = j + 1;
                    }
                    dp[i][j].add(dp[i - 1][min].val, min);
                }
            }
        }

        int[] seam = new int[height()];
        int min = 0;
        for (int i = 1; i < width(); ++i) {
            if (dp[height() - 1][i].val < dp[height() - 1][min].val) {
                min = i;
            }
        }
        seam[height() - 1] = min;
        for (int i = height() - 2; i >= 0; --i) {
            seam[i] = dp[i + 1][seam[i + 1]].pre;
        }
        return seam;
    }
    public void removeHorizontalSeam(int[] seam) {
        picture = new Picture(SeamRemover.removeHorizontalSeam(picture, seam));
    }
    public void removeVerticalSeam(int[] seam) {
        if (seam.length != height()) {
            throw new IllegalArgumentException();
        } else {
            for (int i = 1; i < height(); ++i) {
                int diff = seam[i] - seam[i - 1];
                if (diff != 1 && diff != -1 && diff != 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
        picture = new Picture(SeamRemover.removeVerticalSeam(picture, seam));
    }
    private void validData(int x, int y) {
        if (x < 0 || x >= picture.width()) {
            throw new IndexOutOfBoundsException();
        }
        if (y < 0 || y >= picture.height()) {
            throw new IndexOutOfBoundsException();
        }
    }
}
