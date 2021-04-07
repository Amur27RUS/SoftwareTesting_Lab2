import funcs.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrigonometryTest {
    final double eps = 0.000001;

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI/6 - 2*Math.PI, -Math.PI, -Math.PI/2, -Math.PI/6, 0, Math.PI/6, Math.PI/2, Math.PI, Math.PI/6+2*Math.PI})
    void sinTest(double x) {
        Sin sin = new Sin(eps);
        assertEquals(sin.stubCalculate(x), sin.calc(x), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI/6 - 2*Math.PI, -Math.PI, -Math.PI/2, -Math.PI/6, 0, Math.PI/6, Math.PI/2, Math.PI, Math.PI/6+2*Math.PI})
    void cosTest(double x) {
        Cos cos = new Cos(eps);
        assertEquals(cos.stubCalculate(x), cos.calc(x), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI/6 - 2*Math.PI, -Math.PI, -Math.PI/2, -Math.PI/6, 0, Math.PI/6, Math.PI/2, Math.PI, Math.PI/6+2*Math.PI})
    void secTest(double x) {
        Sec sec = new Sec(eps);
        assertEquals(sec.stubCalculate(x), sec.calc(x), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI/6 - 2*Math.PI, -Math.PI, -Math.PI/2, -Math.PI/6, 0, Math.PI/6, Math.PI/2, Math.PI, Math.PI/6+2*Math.PI})
    void tanTest(double x) {
        Tan tan = new Tan(eps);
        assertEquals(tan.stubCalculate(x), tan.calc(x), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI/6 - 2*Math.PI, -Math.PI, -Math.PI/2, -Math.PI/6, 0, Math.PI/6, Math.PI/2, Math.PI, Math.PI/6+2*Math.PI})
    void cotTest(double x) {
        Cot cot = new Cot(eps);
        assertEquals(cot.stubCalculate(x), cot.calc(x), eps);
    }
}
