package funcs;

public class Tan implements FuncInterface {
    private double eps;
    private Cos cos;
    private Sin sin;
    public Tan(double eps){
        this.eps = eps;
        this.cos = new Cos(eps);
        this.sin = new Sin(eps);
    }
    public Tan(double eps, Sin sin){
        this.eps = eps;
        this.cos = new Cos(eps);
        this.sin = sin;
    }
    public Tan(double eps, Cos cos){
        this.eps = eps;
        this.cos = cos;
        this.sin = new Sin(eps);
    }
    public Tan(double eps,Sin sin, Cos cos){
        this.eps = eps;
        this.cos = cos;
        this.sin = sin;
    }
    public double calc(double x){
        if ((x-Math.PI/2) % Math.PI != 0){
            return sin.calc(x) / cos.calc(x);
        }
        else {
            return Double.NaN;
        }

    }
    public double stubCalculate(double x){
        if ((x-Math.PI/2) % Math.PI != 0){
            return Math.tan(x);
        }
        else {
            return Double.NaN;
        }
    }

    public void setEps(double eps) {
        this.eps = eps;
        this.sin.setEps(eps);
        this.cos.setEps(eps);
    }
}
