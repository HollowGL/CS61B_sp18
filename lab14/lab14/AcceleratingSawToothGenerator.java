package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private double acc;
    private int state;

    public AcceleratingSawToothGenerator(int period, double acc) {
        this.period = period;
        this.acc = acc;
        state = 0;
    }
    public double next() {
        state += 1;
        if (state % period == 0) {
            state -= period;
            period = (int) (period * acc);
        }
        return (double) (state % period) / period * 2 - 1;
    }

}
