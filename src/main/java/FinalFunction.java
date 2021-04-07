import funcs.*;

public class FinalFunction implements FuncInterface {
    private double eps;
    private Sin sin;
    private Cos cos;
    private Sec sec;
    private Tan tan;
    private Cot cot;
    private Log log3;
    private Log log10;
    private Log log5;

    public FinalFunction(Sin sin, Cos cos, Sec sec, Tan tan, Cot cot, Log log3, Log log5, Log log10) {
        this.sin = sin;
        this.cos = cos;
        this.sec = sec;
        this.tan = tan;
        this.cot = cot;
        this.log3 = log3;
        this.log10 = log10;
        this.log5 = log5;
    }

    public double calc(double x){
        if (x<=0){
            return (((((cos.calc(x) - sec.calc(x)) - sin.calc(x)) - tan.calc(x)) + (Math.pow(cos.calc(x), 2))) * cot.calc(x));
        }
        else {
            return (((Math.pow(log3.calc(x), 3)) + log5.calc(x)) * Math.pow(log10.calc(x), 3)) + (Math.pow(log5.calc(x), 2));
        }
    }

    @Override
    public void setEps(double eps) {
        this.eps = eps;
    }
}
