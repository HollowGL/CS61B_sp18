package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public static void addHexagon(int s, int px, int py) {
        int[][] h = genHexagon(s);
        int width = 3 * s - 2;
        int height = 2 * s;
        // ...
    }

    private static int[][] genHexagon(int s) {
        int width = 3 * s - 2;
        int height = 2 * s;
        int[][] hex = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int flag = i + j;
                if (flag < s - 1 || flag > 4 * s - 3) {
                    hex[i][j] = 0;
                    continue;
                }
                flag = i - j;
                if ((i > s && flag > s) || (i < s - 1 && flag < 2 - 2 * s)) {
                    hex[i][j] = 0;
                    continue;
                }
                hex[i][j] = 1;
            }
        }
        return hex;
    }

    public static void main(String[] args) {
        int s = 5;
        int[][] h = genHexagon(s);
        int width = 3 * s - 2;
        int height = 2 * s;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j< width; j++) {
                if (h[i][j] == 1) {
                    System.out.print('a');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}
