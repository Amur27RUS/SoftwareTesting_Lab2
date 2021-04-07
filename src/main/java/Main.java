import funcs.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        double eps = 0.00001;
        boolean loop = true;
        boolean isFunc;
        String inputFunc;
        double base;
        double start;
        double end;
        double step;
        double epsInput = Double.NaN;
        String parameters;
        Sin sin = new Sin(eps);
        Cos cos = new Cos(eps);
        Sec sec = new Sec(eps);
        Tan tan = new Tan(eps);
        Cot cot = new Cot(eps);
        Ln ln = new Ln(eps);
        Log log3 = new Log(3, eps);
        Log log5 = new Log(5, eps);
        Log log10 = new Log(10, eps);
        FinalFunction finalFunction = new FinalFunction(sin, cos, sec, tan,cot,log3,log5,log10);
        Scanner in = new Scanner(System.in);
        ArrayList<FuncInterface> functions = new ArrayList<>();


        while (loop){
            isFunc = true;
            while (isFunc){
                System.out.println("Добавьте функции (stop, если выбрали): sin || cos || sec || tan || cot || ln || log || finalFunc");
                inputFunc = in.nextLine();
                switch (inputFunc){
                    case ("sin"):
                        functions.add(sin);
                        break;
                    case ("cos"):
                        functions.add(cos);
                        break;
                    case ("sec"):
                        functions.add(sec);
                        break;
                    case ("tan"):
                        functions.add(tan);
                        break;
                    case ("cot"):
                        functions.add(cot);
                        break;
                    case ("ln"):
                        functions.add(ln);
                        break;
                    case ("log"):
                        System.out.println("Какое основание хотите задать логарифму?");
                        base = Double.parseDouble(in.nextLine());
                        if (base <= 0 || base == 1){
                            System.out.println("Неверное основание");
                            break;
                        }if (base != (int) base) {
                        System.out.println("Основание должно быть числом(int)");
                        break;
                    }
                        Log logBase = new Log(base, eps);
                        functions.add(logBase);
                        break;
                    case ("finalFunc"):
                        functions.add(finalFunction);
                        break;
                    case ("stop"):
                        System.out.println("ок, завершаю ввод функций");
                        isFunc = false;
                        break;
                    default:
                        System.out.println("Неверная комманда...");
                        break;
                }
            }
                System.out.println("Введите точность:");
                try{epsInput = Double.parseDouble(in.nextLine());}
                catch (Exception e){
                    System.out.println("Точность должна быть числом! Или это я что-то не понимаю...");
                }

                for (FuncInterface func: functions){
                    func.setEps(epsInput);
                }

                System.out.println("Введите значения в одну строку через пробел для: НАЧАЛО КОНЕЦ ШАГ");
                parameters = in.nextLine();
                String[] params = parameters.split(" ");
                start = Double.parseDouble(params[0]);
                end = Double.parseDouble(params[1]);
                step = Double.parseDouble(params[2]);
                CsvWriter.write(functions, start, end, step);
                System.out.println("Закрыть программу? (Y/N)");
                if (in.nextLine().equals("Y")) loop = false;
        }
    }
}
