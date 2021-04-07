import funcs.FuncInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CsvWriter {
    public static void write(ArrayList<FuncInterface> functions, double start, double end, double step) throws IOException {
        double res;
        File file = new File("src/main/resources/finalResult.csv");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        for (FuncInterface function: functions){
            for (double x = start; x <= end; x+=step){
                res = function.calc(x);
                writer.write(String.format("%f || %f || %s;\n", x, res, function.getClass()));
            }
        }
        writer.close();
    }
}
