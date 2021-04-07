import funcs.Ln;
import funcs.Log;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogarithmicTest {
    final double eps = 0.00001;

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.3, 1.0, 1.5, 3.0})
    void lnTest(double x) {
        Ln ln = new Ln(eps);
        assertEquals(ln.stubCalculate(x), ln.calc(x), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.3, 1.0, 1.5, 3.0})
    void log3Test(double x) {
        Log log3 = new Log(3, eps);
        assertEquals(log3.stubCalc(x), log3.calc(x), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.3, 1.0, 1.5, 3.0})
    void log5Test(double x) {
        Log log5 = new Log(5, eps);
        assertEquals(log5.stubCalc(x), log5.calc(x), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.3, 1.0, 1.5, 3.0})
    void log10Test(double x) {
        Log log10 = new Log(10, eps);
        assertEquals(log10.stubCalc(x), log10.calc(x), eps);
    }
}
