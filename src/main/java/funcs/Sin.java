package funcs;


import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Sin implements FuncInterface {
    private double eps;
    public Sin(double eps){
        this.eps = eps;
    }

    public double calc(double x){
        double result= Double.MAX_VALUE;
        double n = 1.0;
        double numerator = 1.0;
        double denominator = 1.0;
        double newResult = x;

        while (abs(result - newResult) > eps) {
            result = newResult;
            numerator = Math.pow(-1.0, n) * pow(x, 2*n + 1);
            denominator *= (2 * n) * (2 * n + 1);
            newResult += numerator / denominator;
            n++;
        }
        return newResult;
    }

    public double stubCalculate(double x){
        return Math.sin(x);
    }

    public double getEps() {
        return eps;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

}
