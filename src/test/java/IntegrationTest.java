import funcs.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {
    final double eps = 0.0001;

    static Sin sinStub = Mockito.mock(Sin.class);
    static Cos cosStub = Mockito.mock(Cos.class);
    static Sec secStub = Mockito.mock(Sec.class);
    static Tan tanStub = Mockito.mock(Tan.class);
    static Cot cotStub = Mockito.mock(Cot.class);
    static Ln lnStub = Mockito.mock(Ln.class);
    static Log log3Stub = Mockito.mock(Log.class);
    static Log log10Stub = Mockito.mock(Log.class);
    static Log log5Stub = Mockito.mock(Log.class);

    @BeforeClass
    public static void setupStubs(){
        log3Stub.setBase(3);
        log5Stub.setBase(5);
        log10Stub.setBase(10);
        Mockito.when(sinStub.calc(Mockito.anyDouble())).thenAnswer(i -> Math.sin((Double) i.getArguments()[0]));
        Mockito.when(cosStub.calc(Mockito.anyDouble())).thenAnswer(i -> Math.cos((Double) i.getArguments()[0]));
        Mockito.when(secStub.calc(Mockito.anyDouble())).thenAnswer(i -> {
            if (((Double) i.getArguments()[0]-Math.PI/2) % Math.PI != 0){
                return 1/Math.cos((Double) i.getArguments()[0]);
            }
            else {
                return Double.NaN;
            }
        });
        Mockito.when(tanStub.calc(Mockito.anyDouble())).thenAnswer(i -> {
            if (((Double) i.getArguments()[0]-Math.PI/2) % Math.PI != 0){
                return Math.tan((Double) i.getArguments()[0]);
            }
            else {
                return Double.NaN;
            }
        });
        Mockito.when(cotStub.calc(Mockito.anyDouble())).thenAnswer(i -> {
            if ((Double) i.getArguments()[0] % Math.PI != 0){
                return Math.cos((Double) i.getArguments()[0])/Math.sin((Double) i.getArguments()[0]);
            }
            else {
                return Double.NaN;
            }
        });
        Mockito.when(lnStub.calc(Mockito.anyDouble())).thenAnswer(i -> Math.log((Double) i.getArguments()[0]));
        Mockito.when(log3Stub.calc(Mockito.anyDouble())).thenAnswer(i -> Math.log((Double) i.getArguments()[0]) / Math.log(3));
        Mockito.when(log5Stub.calc(Mockito.anyDouble())).thenAnswer(i -> Math.log((Double) i.getArguments()[0]) / Math.log(5));
        Mockito.when(log10Stub.calc(Mockito.anyDouble())).thenAnswer(i -> Math.log((Double) i.getArguments()[0]) / Math.log(10));
    }

    @Test
    public void logWithLnTest(){
        Log log3 = new Log(3,eps);
        Log log5 = new Log(5, eps);
        Log log10 = new Log(10,eps);

        assertEquals(log3.stubCalc(Math.PI), log3.calc(Math.PI),eps);
        assertEquals(log3.stubCalc(Math.PI/2), log3.calc(Math.PI/2),eps);
        assertEquals(log3.stubCalc(Math.PI/4), log3.calc(Math.PI/4),eps);

        assertEquals(log5.stubCalc(Math.PI), log5.calc(Math.PI),eps);
        assertEquals(log5.stubCalc(Math.PI/2), log5.calc(Math.PI/2),eps);
        assertEquals(log5.stubCalc(Math.PI/4), log5.calc(Math.PI/4),eps);

        assertEquals(log10.stubCalc(Math.PI), log10.calc(Math.PI),eps);
        assertEquals(log10.stubCalc(Math.PI/2), log10.calc(Math.PI/2),eps);
        assertEquals(log10.stubCalc(Math.PI/4), log10.calc(Math.PI/4),eps);
    }

    @Test
    public void stubsFuncTestOnlyStubs(){
        FinalFunction finalFunction = new FinalFunction(sinStub, cosStub, secStub, tanStub, cotStub, log3Stub, log5Stub, log10Stub);
        assertEquals(-0.8862752235817333, finalFunction.calc(-1), eps);
        assertEquals(-0.07359705533636725, finalFunction.calc(-1.5), eps);
        assertEquals(0.7248269118966191, finalFunction.calc(-2.25), eps);
        assertEquals(0.396566938502173, finalFunction.calc(2.5), eps);
        assertEquals(0.64870600944735, finalFunction.calc(3), eps);
        assertEquals(1.368486932782430, finalFunction.calc(4), eps);
    }

    @Test
    public void stubsFuncTestWithSecTan(){
        Sec sec = new Sec(eps, cosStub);
        Tan tan = new Tan(eps, sinStub, cosStub);
        Cot cot = new Cot(eps, sinStub, cosStub);
        FinalFunction finalFunction = new FinalFunction(sinStub, cosStub, sec, tan, cotStub, log3Stub, log5Stub, log10Stub);
        assertEquals(-0.8862752235817333, finalFunction.calc(-1), eps);
        assertEquals(-0.07359705533636725, finalFunction.calc(-1.5), eps);
        assertEquals(0.7248269118966191, finalFunction.calc(-2.25), eps);
        assertEquals(0.396566938502173, finalFunction.calc(2.5), eps);
        assertEquals(0.64870600944735, finalFunction.calc(3), eps);
        assertEquals(1.368486932782430, finalFunction.calc(4), eps);
    }

    @Test
    public void stubsFuncTestWithSecTanCot(){
        Sec sec = new Sec(eps, cosStub);
        Tan tan = new Tan(eps, sinStub, cosStub);
        Cot cot = new Cot(eps, sinStub, cosStub);
        FinalFunction finalFunction = new FinalFunction(sinStub, cosStub, sec, tan, cot, log3Stub, log5Stub, log10Stub);
        assertEquals(-0.8862752235817333, finalFunction.calc(-1), eps);
        assertEquals(-0.07359705533636725, finalFunction.calc(-1.5), eps);
        assertEquals(0.7248269118966191, finalFunction.calc(-2.25), eps);
        assertEquals(0.396566938502173, finalFunction.calc(2.5), eps);
        assertEquals(0.64870600944735, finalFunction.calc(3), eps);
        assertEquals(1.368486932782430, finalFunction.calc(4), eps);
    }

    @Test
    public void stubsFuncTestWithCosSecTanCot(){
        Cos cos = new Cos(eps, sinStub);
        Sec sec = new Sec(eps);
        Tan tan = new Tan(eps, sinStub);
        Cot cot = new Cot(eps, sinStub);
        FinalFunction finalFunction = new FinalFunction(sinStub, cos, sec, tan, cot, log3Stub, log5Stub, log10Stub);
        assertEquals(-0.8862752235817333, finalFunction.calc(-1), eps);
        assertEquals(-0.07359705533636725, finalFunction.calc(-1.5), eps);
        assertEquals(0.7248269118966191, finalFunction.calc(-2.25), eps);
        assertEquals(0.396566938502173, finalFunction.calc(2.5), eps);
        assertEquals(0.64870600944735, finalFunction.calc(3), eps);
        assertEquals(1.368486932782430, finalFunction.calc(4), eps);
    }

    @Test
    public void stubsFuncTestSinCosSecTanCot(){
        Sin sin = new Sin(eps);
        Cos cos = new Cos(eps);
        Sec sec = new Sec(eps);
        Tan tan = new Tan(eps);
        Cot cot = new Cot(eps);
        FinalFunction finalFunction = new FinalFunction(sin, cos, sec, tan, cot, log3Stub, log5Stub, log10Stub);
        assertEquals(-0.8862752235817333, finalFunction.calc(-1), eps);
        assertEquals(-0.07359705533636725, finalFunction.calc(-1.5), eps);
        assertEquals(0.7248269118966191, finalFunction.calc(-2.25), eps);
        assertEquals(0.396566938502173, finalFunction.calc(2.5), eps);
        assertEquals(0.64870600944735, finalFunction.calc(3), eps);
        assertEquals(1.368486932782430, finalFunction.calc(4), eps);
    }

    @Test
    public void finalFuncTest(){
        Sin sin = new Sin(eps);
        Cos cos = new Cos(eps);
        Sec sec = new Sec(eps);
        Tan tan = new Tan(eps);
        Cot cot = new Cot(eps);
        Log log3 = new Log(3,eps);
        Log log10 = new Log(10,eps);
        Log log5 = new Log(5, eps);
        FinalFunction finalFunction = new FinalFunction(sin, cos, sec, tan, cot, log3, log5, log10);
        assertEquals(-0.8862752235817333, finalFunction.calc(-1), eps);
        assertEquals(-0.07359705533636725, finalFunction.calc(-1.5), eps);
        assertEquals(0.7248269118966191, finalFunction.calc(-2.25), eps);
        assertEquals(0.396566938502173, finalFunction.calc(2.5), eps);
        assertEquals(0.64870600944735, finalFunction.calc(3), eps);
        assertEquals(1.368486932782430, finalFunction.calc(4), eps);
    }

    @Test
    public void cosTest(){
        Cos cos = new Cos(eps, sinStub);

        assertEquals(cos.stubCalculate(Math.PI), cos.calc(Math.PI),eps);
        assertEquals(cos.stubCalculate(Math.PI/2), cos.calc(Math.PI/2),eps);
        assertEquals(cos.stubCalculate(Math.PI/4), cos.calc(Math.PI/4),eps);
    }

    @Test
    public void secTest(){
        Sec sec = new Sec(eps, cosStub);

        assertEquals(sec.stubCalculate(Math.PI), sec.calc(Math.PI),eps);
        assertEquals(sec.stubCalculate(Math.PI/2), sec.calc(Math.PI/2),eps);
        assertEquals(sec.stubCalculate(Math.PI/4), sec.calc(Math.PI/4),eps);
    }

    @Test
    public void tanWithSinTest(){
        Tan tan = new Tan(eps, sinStub);

        assertEquals(tan.stubCalculate(Math.PI), tan.calc(Math.PI),eps);
        assertEquals(tan.stubCalculate(Math.PI/2), tan.calc(Math.PI/2),eps);
        assertEquals(tan.stubCalculate(Math.PI/4), tan.calc(Math.PI/4),eps);
    }

    @Test
    public void tanWithCosTest(){
        Tan tan = new Tan(eps, cosStub);

        assertEquals(tan.stubCalculate(Math.PI), tan.calc(Math.PI),eps);
        assertEquals(tan.stubCalculate(Math.PI/2), tan.calc(Math.PI/2),eps);
        assertEquals(tan.stubCalculate(Math.PI/4), tan.calc(Math.PI/4),eps);
    }

    @Test
    public void cotWithSinTest(){
        Cot cot = new Cot(eps, sinStub);

        assertEquals(cot.stubCalculate(Math.PI), cot.calc(Math.PI),eps);
        assertEquals(cot.stubCalculate(Math.PI/2), cot.calc(Math.PI/2),eps);
        assertEquals(cot.stubCalculate(Math.PI/4), cot.calc(Math.PI/4),eps);
    }

    @Test
    public void cotWithCosTest(){
        Cot cot = new Cot(eps, sinStub);

        assertEquals(cot.stubCalculate(Math.PI), cot.calc(Math.PI),eps);
        assertEquals(cot.stubCalculate(Math.PI/2), cot.calc(Math.PI/2),eps);
        assertEquals(cot.stubCalculate(Math.PI/4), cot.calc(Math.PI/4),eps);
    }
}
