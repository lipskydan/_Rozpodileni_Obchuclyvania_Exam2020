package program.calc;

import java.io.Serializable;

public class CalculatorParams implements Serializable {
    public double a,xFom,xTo,step;

    public CalculatorParams(double a, double xFom, double xTo, double step) {
        this.a = a;
        this.xFom = xFom;
        this.xTo = xTo;
        this.step = step;
    }
}
