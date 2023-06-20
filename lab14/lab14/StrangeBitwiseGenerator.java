package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int period) {
        this.period = period;
        state = 0;
    }
    private double helper() {
        state += 1;
        return (double) (state & (state >> 7) % period) / period - 1;
    }
    public double next() {
        return (helper() + 0.5) * 2;
    }
}
