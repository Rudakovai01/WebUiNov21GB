package HomeWork4;

import java.util.List;

public class TriangleSquare {
    int a;
    int b;
    int c;

    public TriangleSquare(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public int getA() {
        return a;
    }
    public int getB() {
        return b;
    }
    public int getC() {
        return c;
    }



    public static double getTriangleSquare(int a, int b, int c) throws Exception {
        if ((a | b | c) <= 0) throw new Exception("Сторона треугольника не может быть меньше, либо равно 0");
        else {
            double s = ((a + b + c) / 2.0) * ((a + b + c) / 2.0 - a) * ((a + b + c) / 2.0 - b) * ((a + b + c) / 2.0 - c);
            return Math.sqrt(s);
       }
    }
}
