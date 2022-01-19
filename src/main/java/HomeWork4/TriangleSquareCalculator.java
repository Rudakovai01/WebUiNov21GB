package HomeWork4;

public class TriangleSquareCalculator {
    public TriangleSquareCalculator() {}

    public double getTriangleSquare(int a, int b, int c) throws Exception {
        if ((a | b | c) <= 0) throw new Exception("Сторона треугольника не может быть меньше, либо равно 0");
        else {
            double s = ((a + b + c) / 2.0) * ((a + b + c) / 2.0 - a) * ((a + b + c) / 2.0 - b) * ((a + b + c) / 2.0 - c);
            return Math.sqrt(s);
       }
    }
}
