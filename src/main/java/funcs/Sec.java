package funcs;

public class Sec implements FuncInterface {
    private double eps;
    private Cos cos;
    public Sec(double eps){
        this.eps = eps;
        this.cos = new Cos(eps);
    }
    public Sec(double eps, Cos cos){
        this.eps = eps;
        this.cos = cos;
    }
    public double calc(double x){
        if ((x-Math.PI/2) % Math.PI != 0){
            return 1 / cos.calc(x);
        }
        else {
            return Double.NaN;
        }
    }

    public double stubCalculate(double x){
        if ((x-Math.PI/2) % Math.PI != 0){
            return 1 / Math.cos(x);
        }
        else {
            return Double.NaN;
        }
    }

    public void setEps(double eps) {
        this.eps = eps;
        this.cos.setEps(eps);
    }
}
