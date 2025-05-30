import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TrianglePerimeter {

  public static double readDouble(String prompt, BufferedReader reader) throws IOException {
    while (true) {
      System.out.print(prompt);
      String input = reader.readLine();
      try {
        return Double.parseDouble(input);
      } catch (NumberFormatException e) {
        System.out.println("Couldn't parse a number. Please, try again");
      }
    }
  }

  public static double dist(double x1, double y1, double x2, double y2) {
    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
  }

  public static boolean isTriangle(double a, double b, double c) {
    return a + b > c && a + c > b && b + c > a;
  }

  public static double perimeter(double a, double b, double c) {
    return a + b + c;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    double x1 = readDouble("Enter x1: ", reader);
    double y1 = readDouble("Enter y1: ", reader);
    double x2 = readDouble("Enter x2: ", reader);
    double y2 = readDouble("Enter y2: ", reader);
    double x3 = readDouble("Enter x3: ", reader);
    double y3 = readDouble("Enter y3: ", reader);

    double sideAB = dist(x1, y1, x2, y2);
    double sideBC = dist(x2, y2, x3, y3);
    double sideCA = dist(x3, y3, x1, y1);

    if (isTriangle(sideAB, sideBC, sideCA)) {
      System.out.printf("Perimeter: %.3f%n", perimeter(sideAB, sideBC, sideCA));
    } else {
      System.out.println("It's not a triangle");
    }
  }
}
