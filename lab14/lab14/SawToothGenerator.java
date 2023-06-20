package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private int period;
    private int state;

    public SawToothGenerator(int period) {
        this.period = period;
        state = 0;
    }
    private double helper() {
        state += 1;
        return (double) (state % period) / period - 1;
    }
    public double next() {
        return (helper() + 0.5) * 2;
    }
}
