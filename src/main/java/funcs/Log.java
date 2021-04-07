package funcs;

public class Log implements FuncInterface {
    private double eps;
    private Ln ln;
    private double base;
    public Log(double base, double eps){
        this.eps = eps;
        this.ln = new Ln(eps);
        this.base = base;
    }

    public double calc(double x){
        if (this.base <= 0 || this.base == 1) return Double.NaN;
        return ln.calc(x) / ln.calc(base);
    }

    public double stubCalc(double x){
        return Math.log(x) / Math.log(base);
    }


    public void setBase(double base) {
        this.base = base;
    }

    public void setEps(double eps) {
        this.eps = eps;
        this.ln.setEps(eps);
    }
}
